package com.acme.dataset;

import com.acme.model.MarvelCharacter;
import com.acme.model.ResourceLink;
import com.acme.repository.MarvelCharacterRepository;
import com.acme.repository.ResourceLinkRepository;
import com.acme.repository.ThumbnailRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * @author nickk
 */

@Component
public class CharacterDataset {

    public static final String UTF_8 = "UTF-8";
    @Autowired
    private ThumbnailRepository thumbnailRepository;

    @Autowired
    private MarvelCharacterRepository marvelCharacterRepository;

    @Autowired
    private ResourceLinkRepository resourceLinkRepository;

    private final OkHttpClient client = new OkHttpClient();

    @PostConstruct
    @SuppressFBWarnings("SIC_INNER_SHOULD_BE_STATIC_ANON")
    public void createData() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("characters.json").getFile());

        String jsonString = IOUtils.toString(new FileInputStream(file), UTF_8);

        Type listType = new TypeToken<ArrayList<CharacterDTO>>() {
        }.getType();
        List<CharacterDTO> dtoList = new Gson().fromJson(jsonString, listType);

        for (CharacterDTO dto : dtoList) {
            MarvelCharacter character = new MarvelCharacter();
            character.setId(dto.getId());
            character.setName(dto.getName());
            character.setDescription(dto.getDescription());

            fetchThumbnail(dto, character);

            fetchUrls(dto, character);
            marvelCharacterRepository.save(character);

            character.setPowers(dto.getPowers());
        }
    }

    private void fetchThumbnail(CharacterDTO dto, MarvelCharacter character) {
        Thumbnail thumbnailDTO = dto.getThumbnail();
        if (thumbnailDTO != null) {
            com.acme.model.Thumbnail thumbnail =
                    new com.acme.model.Thumbnail();
            thumbnail.setPath(thumbnailDTO.getPath());
            thumbnail.setExtension(thumbnailDTO.getExtension());

            thumbnailRepository.save(thumbnail);
            character.setThumbnail(thumbnail);
        }
    }

    private void fetchUrls(CharacterDTO dto, MarvelCharacter character) throws Exception{
        List<Urls> urls = dto.getUrls();
        //Whitelist whitelist = new Whitelist().none();

        for (Urls url : urls) {
            ResourceLink resourceLink = new ResourceLink();
            resourceLink.setType(url.getType());
            resourceLink.setUrl(url.getUrl());
            resourceLinkRepository.save(resourceLink);
            character.getResourceLinks().add(resourceLink);

            fetchLocalPowers(character);

            //fetchPowers(character, whitelist, url);
        }
    }

    @SuppressFBWarnings({"REC_CATCH_EXCEPTION"})
    private void fetchLocalPowers(MarvelCharacter character)  {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("data" + File.separator + character.getId() + ".powers").getFile());
            if (file.exists() && file.length() > 0) {
                String powers = IOUtils.toString(new FileInputStream(file), UTF_8);
                character.setPowers(powers);
            }
        }catch(Exception e){
            //do nothing!!
        }
    }

    //only used to fetch my initial data, so safe to use /tmp
    @SuppressFBWarnings({"UPM_UNCALLED_PRIVATE_METHOD","REC_CATCH_EXCEPTION"})
    private void fetchRemotePowers(MarvelCharacter character, Whitelist whitelist, Urls url) {
        if ("wiki".equalsIgnoreCase(url.getType()) && StringUtils.isNotBlank(url.getUrl())) {
            Request request = new Request.Builder()
                    .url(url.getUrl())
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    LOG.error("Error: url = {}, response= {}", url.getUrl(), response);
                    return;
                }

                Document doc = Jsoup.parse(response.body().string());
                Element powersElement = doc.getElementById("char-powers");
                if (powersElement != null) {
                    String cleanText = Jsoup.clean(powersElement.html(), whitelist);
                    character.setPowers(cleanText);

                    File powersFile = new File("/tmp/" + character.getId() + ".powers");
                    IOUtils.write(cleanText.getBytes(UTF_8), new FileOutputStream(powersFile));
                }
            } catch (Exception e) {
                LOG.error("Error", e);
            }
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(CharacterDataset.class);
}

package com.acme.repository;

import com.acme.config.SpringAppConfig;
import com.acme.model.MarvelCharacter;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author nickk
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {SpringAppConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@TestPropertySource("/database.properties")
public class MarvelCharacterRepositoryTest {

    @Resource
    private ThumbnailRepository thumbnailRepository;

    @Resource
    private MarvelCharacterRepository marvelCharacterRepository;

    @Test
    public void showThumbnailPersistence() {
        long count = thumbnailRepository.count();
        org.junit.Assert.assertEquals(20L, count);
    }

    @Test
    public void showPowersArePersisted() throws Exception{
        Iterable<MarvelCharacter> iterable = marvelCharacterRepository.findAll();
        List<Long> list =  Arrays.asList(IDS);

        Iterator<MarvelCharacter> iterator =iterable.iterator();

        while(iterator.hasNext()){
            MarvelCharacter character = iterator.next();
            if(list.contains(character.getId())){
                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource("data" + File.separator + character.getId() + ".powers").getFile());
                if(file != null && file.exists() && file.length() > 0){
                    String powers = IOUtils.toString(new FileInputStream(file), "UTF-8");
                    org.junit.Assert.assertEquals(character.getPowers(), powers);
                }
            }
        }
    }


    static final Long[] IDS = {1009146L, 1009149L, 1010354L, 1010870L, 1011031L, 1011175L, 1011266L,
            1009148L, 1009150L, 1010846L, 1010903L, 1011136L, 1011176L, 1011334L};

}
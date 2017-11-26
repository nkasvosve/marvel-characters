package com.acme.service;


import com.acme.model.MarvelCharacter;
import com.acme.repository.MarvelCharacterRepository;
import com.acme.repository.ThumbnailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TestSpringConfiguration.class)
@Ignore //getting nasty Spring-test dependency issues ...
public class MarvelCharacterResourceControllerTestIT {

    public static String API_HOST = "localhost";
    static final String UTF_8 = "UTF-8";
    public static String API_URI_PREFIX = "/characters";

    public String getFullAPIURL(boolean bTrailingSlash) {
        return "http://" + API_HOST + ":" + this.port + API_URI_PREFIX + (bTrailingSlash ? "/" : "");
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MarvelCharacterRepository marvelCharacterRepository;

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    @Before
    public void setUp() {
        marvelCharacterRepository.deleteAll();
            thumbnailRepository.deleteAll();
    }

    @After
    public void cleanUp() {
            marvelCharacterRepository.deleteAll();
                thumbnailRepository.deleteAll();
    }

    private static String readFile(String fileName) throws Exception {

        try {
            return IOUtils.toString(MarvelCharacterResourceControllerTestIT.class.getResourceAsStream(fileName), UTF_8);
        } catch (Exception e) {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    @Test
    public void shouldReturn200WhenGetting_A_Driver() throws Exception {

        String driverJson = readFile("src/test/resources/characters/1011334.json");

        ObjectMapper mapper = new ObjectMapper();

        MarvelCharacter character = mapper.readValue(driverJson, MarvelCharacter.class);

        marvelCharacterRepository.save(character);
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                this.getFullAPIURL(true) + character.getId(), Map.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(entity.getBody().get("name")).isEqualTo(character.getName());
        then(entity.getBody().get("description")).isEqualTo(character.getDescription());
    }

    private static final <T> List<T> getList(
            final Gson gson, final Class<T> clazz,
            final JsonArray json) {
        return gson.fromJson(json, new ListOfStuff(clazz));
    }
}

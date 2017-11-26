package com.acme.service;

import com.acme.model.MarvelCharacter;
import com.acme.repository.MarvelCharacterRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@ContextConfiguration(classes = TestSpringConfiguration.class)
@Ignore //getting nasty Spring-test dependency issues ...
public class MarvelCharacterResourceControllerTest {

    private MockMvc mockMvc;
    
    public static final String API_URI_PREFIX = "/v1/drivers/";

    @InjectMocks
    private MarvelCharacterResourceController controller;

    @Autowired
    private MarvelCharacterRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void showGetNoDrivers() throws Exception {
        Long driverId = 20L;
        stub(repository.findOne(driverId)).toReturn(null);
        this.mockMvc.perform(get(API_URI_PREFIX + driverId)).andDo(print())
                .andExpect(status().isNoContent());
        verify(repository, atLeastOnce()).findOne(driverId);
    }

    @Test
    public void showGet_A_Drivers() throws Exception {

        MarvelCharacter character = new MarvelCharacter();
        character.setName("Mel Gibson");
        character.setDescription("A Movie Director");
        character.setId(2009383L);

        stub(repository.findOne(character.getId())).toReturn(character);

        this.mockMvc.perform(get(API_URI_PREFIX + character.getId())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2009383L")))
                .andExpect(content().string(containsString("Mel Gibson")))
                .andExpect(content().string(containsString("A Movie Director")));

        verify(repository, atLeastOnce()).findOne(character.getId());
    }

    @Test
    public void showThatDeleteDriverWithNoInputReturnsNotAllowed() throws Exception {

        mockMvc.perform(delete(API_URI_PREFIX).contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void showThatUpdateDriverWithNoInputReturnsBadRequest() throws Exception {

        mockMvc.perform(put(API_URI_PREFIX).contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content("")).andDo(print())
                .andExpect(status().isBadRequest());
    }
}

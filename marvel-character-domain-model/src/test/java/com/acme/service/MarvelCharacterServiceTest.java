package com.acme.service;

import com.acme.config.SpringAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

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
public class MarvelCharacterServiceTest {

    @Autowired
    private MarvelCharacterService marvelCharacterService;

    @Test
    public void showQueryForCharacterIds() {

        List<Long>ids =  marvelCharacterService.findAllCharacterIds();
        org.junit.Assert.assertEquals(20L, ids.size());
    }
}



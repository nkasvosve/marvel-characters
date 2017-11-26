package com.acme.service;

import com.acme.repository.MarvelCharacterRepository;
import com.acme.repository.ThumbnailRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration(value = "testConfig")
public class TestSpringConfiguration {

    @Bean
    public MarvelCharacterRepository marvelCharacterRepository() {
        return Mockito.mock(MarvelCharacterRepository.class);
    }

    @Bean
    public ThumbnailRepository thumbnailRepository() {
        return Mockito.mock(ThumbnailRepository.class);
    }
}

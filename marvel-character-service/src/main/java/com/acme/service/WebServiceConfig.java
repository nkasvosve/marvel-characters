package com.acme.service;

/**
 * @author nickk
 */

import com.acme.ws.characters.services.IMarvelCharacterService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private IMarvelCharacterService marvelCharacterService;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, marvelCharacterService);
        endpoint.publish("/characters");
        return endpoint;
    }
}

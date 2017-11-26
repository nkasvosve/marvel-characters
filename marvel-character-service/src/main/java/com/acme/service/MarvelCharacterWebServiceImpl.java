package com.acme.service;

import com.acme.model.MarvelCharacter;
import com.acme.repository.MarvelCharacterRepository;
import com.acme.resource.model.MarvelCharactersResponseExt;
import com.acme.ws.characters.model.MarvelCharacterRequest;
import com.acme.ws.characters.model.MarvelCharacterResponse;
import com.acme.ws.characters.model.MarvelCharactersRequest;
import com.acme.ws.characters.model.MarvelCharactersResponse;
import com.acme.ws.characters.services.BusinessFault;
import com.acme.ws.characters.services.IMarvelCharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nickk
 */

@Component
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class MarvelCharacterWebServiceImpl implements IMarvelCharacterService {

    @Autowired
    private MarvelCharacterRepository marvelCharacterRepository;

    @Autowired
    private MarvelCharacterService marvelCharacterService;

    /**
     *
     * @param request
     * @return
     * @throws BusinessFault
     */
    @Override
    public MarvelCharactersResponse requestMarvelCharacters(MarvelCharactersRequest request)
            throws BusinessFault {

        List<Long> charactersIds = marvelCharacterService.findAllCharacterIds();

        MarvelCharactersResponseExt response = new MarvelCharactersResponseExt();

        List<Integer> ids = new ArrayList<>();
        for (Long num : charactersIds) {
            ids.add(Integer.valueOf(num.intValue()));
        }
        response.setIds(ids);
        return response;
    }

    /**
     * @param request
     * @return
     * @throws BusinessFault
     */
    @Override
    public MarvelCharacterResponse requestMarvelCharacterDetails(MarvelCharacterRequest request)
            throws BusinessFault {
        MarvelCharacterResponse response = new MarvelCharacterResponse();
        try {
            if (request.getId() > 0) {
                MarvelCharacter marvelCharacter = marvelCharacterRepository.findOne(request.getId());
                if (marvelCharacter != null) {
                    response.setName(marvelCharacter.getName());
                    response.setDescription(marvelCharacter.getDescription());
                    response.setResourceId(marvelCharacter.getId().intValue());
                }
                return response;
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }

        throw new BusinessFault("Invalid request found");
    }

    private static final Logger LOG =
            LoggerFactory.getLogger(MarvelCharacterWebServiceImpl.class);
}

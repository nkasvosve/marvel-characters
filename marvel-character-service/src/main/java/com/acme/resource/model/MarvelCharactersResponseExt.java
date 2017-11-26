package com.acme.resource.model;

import com.acme.ws.characters.model.MarvelCharactersResponse;

import java.util.List;

/**
 * @author nickk
 */
public class MarvelCharactersResponseExt extends MarvelCharactersResponse{

    public void setIds(List<Integer> ids){
        this.id = ids;
    }
}

package com.acme.service;

import com.acme.model.MarvelCharacter;
import com.acme.repository.MarvelCharacterRepository;
import com.acme.resource.model.ResourceTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nickk
 */

@Api(value = "Marvel Caharcters")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The successful retrieval of a Marvel Character", response = MarvelCharacter.class),
        @ApiResponse(code = 204, message = "Marvel Character with given id does not exist"),
        @ApiResponse(code = 500, message = "Internal server error")}
)
@RestController
@RequestMapping(value = "characters")
public class MarvelCharacterResourceController {

    @Autowired
    private MarvelCharacterRepository marvelCharacterRepository;

    @Autowired
    private MarvelCharacterService marvelCharacterService;

    /**
     * Gets a character by its id
     *
     * @param id
     * @return
     */

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Returns a marvel character's details")
    public ResponseEntity<?> getCharacter(
            @PathVariable(required = true, name = "id") Long id) {

        try {
            if (id != null) {
                MarvelCharacter marvelCharacter = marvelCharacterRepository.findOne(id);
                if (marvelCharacter != null) {
                    return ResponseEntity.ok(ResourceTranslator.fromCharacterModel(marvelCharacter).get());
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOG.error("Error", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Returns list of all marvel character ids")
    public ResponseEntity<?> fetchCharacters() {

        List<Long> charactersIds = marvelCharacterService.findAllCharacterIds();
        try {
            return ResponseEntity.ok(charactersIds);
        } catch (Exception e) {
            LOG.error("Error", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "PATCH is not yet implemented")
    @RequestMapping(method = RequestMethod.PATCH)
    public String patchCharacter() {
        throw new IllegalArgumentException(
                "HTTP.PATCH method is not yet implemented in this resource");
    }

    @ApiOperation(value = "DELETE is not yet implemented")
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteCharacter() {
        throw new IllegalArgumentException(
                "HTTP.DELETE method is not yet implemented in this resource");
    }

    @ApiOperation(value = "POST is not yet implemented")
    @RequestMapping(method = RequestMethod.POST)
    public String createCharacter() {
        throw new IllegalArgumentException(
                "HTTP.POST method is not yet implemented in this resource");
    }

    @ApiOperation(value = "PUT is not yet implemented")
    @RequestMapping(method = RequestMethod.PUT)
    public String updateCharacter() {
        throw new IllegalArgumentException(
                "HTTP.PUT method is not yet implemented in this resource");
    }

    private static final Logger LOG =
            LoggerFactory.getLogger(MarvelCharacterResourceController.class);
}

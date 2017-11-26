package com.acme.repository;

import com.acme.model.MarvelCharacter;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author nickk
 */
@Component
public interface MarvelCharacterRepository extends CrudRepository<MarvelCharacter, Long> , QueryDslPredicateExecutor<MarvelCharacter> {
}

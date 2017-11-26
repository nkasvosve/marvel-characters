package com.acme.service;


import com.acme.model.QMarvelCharacter;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author nickk
 */

@Component
public class MarvelCharacterService {

    @Autowired
    private EntityManager entityManager;

    public List<Long> findAllCharacterIds() {

        JPAQuery<?> query = new JPAQuery<Void>(entityManager);

        QMarvelCharacter marvelCharacter = QMarvelCharacter.marvelCharacter;

        return query.from(marvelCharacter)
                .where(marvelCharacter.id.isNotNull())
                .select(Projections.constructor(Long.class, marvelCharacter.id))
                .fetch();
    }
}

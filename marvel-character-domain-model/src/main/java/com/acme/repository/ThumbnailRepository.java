package com.acme.repository;

import com.acme.model.Thumbnail;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author nickk
 */
@Component
public interface ThumbnailRepository extends CrudRepository<Thumbnail, Long>, QueryDslPredicateExecutor<Thumbnail> {
}

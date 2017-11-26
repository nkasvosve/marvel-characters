package com.acme.repository;

import com.acme.model.ResourceLink;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author nickk
 */
@Component
public interface ResourceLinkRepository extends CrudRepository<ResourceLink, Long>, QueryDslPredicateExecutor<ResourceLink> {
}

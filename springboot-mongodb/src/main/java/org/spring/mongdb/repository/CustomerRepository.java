package org.spring.mongdb.repository;

import org.spring.mongdb.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface CustomerRepository extends MongoRepository<Customer, String>,QueryDslPredicateExecutor<Customer>{
	
	Page<Customer> findByFirstNameContainsOrLastNameContains(String keyWordFistName,
			String keyWordLastName, Pageable pageable);
}
	
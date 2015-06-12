package org.spring.mongdb.service;

import org.spring.mongdb.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
	Customer getCustomer(String id);
	Page<Customer> getCustomers(String keyword, Pageable pageable);
	Customer saveCustomer(Customer cus);
	Customer updateCustomer(Customer cus, String id);
	Page<Customer> getCustomersMatchFistNameAndLastName(String fistname,
			String lastname, Pageable pageable);
}

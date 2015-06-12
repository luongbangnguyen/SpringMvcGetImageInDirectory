package org.spring.mongdb.controller;

import org.spring.mongdb.entity.Customer;
import org.spring.mongdb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService cusSv;
	
	/**
	 * add new customer
	 * @param cus
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Customer saveCustomer(@RequestBody Customer cus){
		return cusSv.saveCustomer(cus);
	}
	
	/**
	 * get list customers by keyword
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	@RequestMapping
	public Page<Customer> getCustomers(@RequestParam String keyword, Pageable pageable){
		return cusSv.getCustomers(keyword, pageable);
	}
	
	@RequestMapping(value="/custom")
	public Page<Customer> getCustomersByFistNameAndLastName(@RequestParam(required = false) String fistname,
			@RequestParam(required = false) String lastname, Pageable pageable){
		return cusSv.getCustomersMatchFistNameAndLastName(fistname, lastname, pageable);
	}
}

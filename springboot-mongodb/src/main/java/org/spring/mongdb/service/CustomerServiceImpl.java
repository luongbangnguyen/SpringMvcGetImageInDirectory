package org.spring.mongdb.service;

import org.apache.commons.lang3.StringUtils;
import org.spring.mongdb.entity.Customer;
import org.spring.mongdb.entity.QCustomer;
import org.spring.mongdb.exeption.BusinessException;
import org.spring.mongdb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer getCustomer(String id) {
		Customer cus = customerRepository.findOne(id);
		if(cus == null){
			throw new BusinessException("Customer "+id+" not found");
		}
		return cus;
	}

	
	@Override
	@Transactional
	public Customer saveCustomer(Customer cus) {
		return customerRepository.save(cus);
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer customer, String id) {
		Customer cus = customerRepository.findOne(id);
		if(cus == null){
			throw new BusinessException("Customer "+id+" not found");
		}
		return saveCustomer(cus);
	}


	@Override
	public Page<Customer> getCustomers(String keyword, Pageable pageable) {
		return customerRepository.findByFirstNameContainsOrLastNameContains(keyword, keyword, pageable);
	}


	@Override
	public Page<Customer> getCustomersMatchFistNameAndLastName(String fistname,
			String lastname, Pageable pageable) {
		if (StringUtils.isBlank(fistname) && StringUtils.isBlank(lastname)) {
			return customerRepository.findAll(pageable);
		}
		BooleanBuilder builder = new BooleanBuilder();
		if (StringUtils.isNotBlank(fistname)) {
			builder.and(QCustomer.customer.firstName.eq(fistname));
		}

		if (StringUtils.isNoneBlank(lastname)) {
			builder.and(QCustomer.customer.lastName.eq(lastname));
		}
		return customerRepository.findAll(builder, pageable);
	}
	
}

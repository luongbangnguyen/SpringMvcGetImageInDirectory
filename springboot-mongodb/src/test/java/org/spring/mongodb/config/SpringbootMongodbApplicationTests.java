package org.spring.mongodb.config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.mongdb.Application;
import org.spring.mongdb.entity.Customer;
import org.spring.mongdb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SpringbootMongodbApplicationTests {

	@Autowired
	private CustomerRepository cusRepository;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testSaveCustomer(){
		Customer cus = cusRepository.save(new Customer());
		assertNotNull(cus);
	}
	

}
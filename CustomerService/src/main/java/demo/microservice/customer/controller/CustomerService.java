package demo.microservice.customer.controller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import demo.microservice.customer.exception.CustomerNotFoundException;
import demo.microservice.customer.domain.Customer;
import demo.microservice.customer.repo.CustomerRepo;

@RestController
public class CustomerService {
	private static final Logger log = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepo custRepo;
	
	@RequestMapping("/customer/{customerid}")
	@ResponseBody
    public Customer getCustomer(@PathVariable int customerid) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Customer cust = custRepo.findByCustomerid(customerid);	
		if(cust==null){
			throw new CustomerNotFoundException(customerid);			
		}else{
			log.info("Customer:" +  ow.writeValueAsString(cust)) ;
		return cust;
		}
       
	}
}

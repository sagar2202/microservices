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
/**
 * @author Sagar Jain
 *
 */
@RestController
public class CustomerService {
	private static final Logger log = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepo custRepo;
	
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	
	@RequestMapping("/customer/{customerid}")
	@ResponseBody
    public Customer getCustomer(@PathVariable int customerid) throws JsonProcessingException {
		Customer cust = custRepo.findByCustomerid(customerid);	
		if(cust==null){
			log.error("Customer Not Found : " +  customerid) ;
			//throw new CustomerNotFoundException(customerid);			
		}else{
			log.info("Customer:" +  ow.writeValueAsString(cust)) ;
		
		}
		return cust;
	}
}

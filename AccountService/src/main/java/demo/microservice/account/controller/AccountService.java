package demo.microservice.account.controller;
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

import demo.microservice.account.domain.Account;
import demo.microservice.account.repo.AccountRepo;

/**
 * @author Sagar Jain
 *
 */
@RestController
public class AccountService {
	private static final Logger log = LogManager.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepo accRepo;
	
	@RequestMapping("/account/{customerid}")
	@ResponseBody
    public Account getCustomer(@PathVariable int customerid) throws JsonProcessingException {
		//DateFormat df = new 
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Account acc = accRepo.findByCustomerid(customerid);	
		log.info("Account Details:" +  ow.writeValueAsString(acc));
		return acc;
       
	}
}

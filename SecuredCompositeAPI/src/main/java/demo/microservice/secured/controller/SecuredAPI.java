/**
 * 
 */
package demo.microservice.secured.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Sagar Jain
 *
 */
@RestController
@EnableResourceServer
public class SecuredAPI {
	private RestTemplate restTemplate = new RestTemplate();
	private static final Logger log = LogManager.getLogger(SecuredAPI.class);

	
	@RequestMapping("/customer/{customerid}")
	public String getCustomer(@PathVariable int customerid) throws JsonProcessingException {

		ResponseEntity<String> custResultStr = restTemplate
				.getForEntity("http://localhost:7000/customer/" + customerid, String.class);

		log.debug("GetProduct http-status: {}" + custResultStr.getStatusCode());
		log.debug("GetProduct body: {}" + custResultStr.getBody());
		
		return  custResultStr.getBody();
	}

}

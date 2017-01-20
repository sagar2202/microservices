/**
 * 
 */
package demo.microservice.authserver;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sagar Jain
 *
 */
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
@EnableDiscoveryClient
public class SecureAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAuthServerApplication.class, args);
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user){
		return user;
	}
}

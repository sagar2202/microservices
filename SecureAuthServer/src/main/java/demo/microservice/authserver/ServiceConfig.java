package demo.microservice.authserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

/**
 * @author Sagar Jain
 *
 */

@Configuration
public class ServiceConfig extends GlobalAuthenticationConfigurerAdapter{
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("password").roles("USER");
	}

}

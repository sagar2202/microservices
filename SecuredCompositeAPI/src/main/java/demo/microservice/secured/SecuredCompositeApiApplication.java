package demo.microservice.secured;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SecuredCompositeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuredCompositeApiApplication.class, args);
	}
}

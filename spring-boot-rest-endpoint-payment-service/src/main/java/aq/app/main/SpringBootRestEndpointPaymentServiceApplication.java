package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"aq.app.controllers"})
public class SpringBootRestEndpointPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestEndpointPaymentServiceApplication.class, args);
	}

}

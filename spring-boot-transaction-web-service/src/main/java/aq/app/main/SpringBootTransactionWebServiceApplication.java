package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"aq.app.repositories", "aq.app.services", "aq.app.controllers"})
public class SpringBootTransactionWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTransactionWebServiceApplication.class, args);
	}

}

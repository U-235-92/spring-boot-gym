package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"aq.app.configs", "aq.app.controllers", "aq.app.proxies"})
public class SpringBootRestEndpointWebclientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestEndpointWebclientServiceApplication.class, args);
	}

}

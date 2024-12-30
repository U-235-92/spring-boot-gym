package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"aq.app.controllers", "aq.app.proxies", "aq.app.configs"})
public class SpringBootRestEndpointOpenfeignServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestEndpointOpenfeignServiceApplication.class, args);
	}

}

package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@ComponentScan(basePackages = {"aq.app.controllers", "aq.app.services"})
public class SpringBootWebScopeExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebScopeExampleApplication.class, args);
	}

}

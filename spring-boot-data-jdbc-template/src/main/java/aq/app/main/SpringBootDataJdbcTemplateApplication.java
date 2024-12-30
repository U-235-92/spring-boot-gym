package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"aq.app.repositories", "aq.app.controllers"})
public class SpringBootDataJdbcTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJdbcTemplateApplication.class, args);
	}

}

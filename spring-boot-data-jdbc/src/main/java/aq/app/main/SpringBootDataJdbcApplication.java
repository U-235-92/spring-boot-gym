package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import aq.app.controllers.AccountController;
import aq.app.repositories.AccountRepository;
import aq.app.services.TransferService;

@SpringBootApplication
@ComponentScan(basePackages = {"aq.app.controllers", "aq.app.services"})
@EnableJdbcRepositories(basePackages = "aq.app.repositories")
public class SpringBootDataJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJdbcApplication.class, args);
	}

}

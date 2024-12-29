package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("aq.app.controllers")
public class SpringBootDynamicSimpleWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDynamicSimpleWebAppApplication.class, args);
	}

}

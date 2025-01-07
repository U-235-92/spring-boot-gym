package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import aq.app.contfigs.BootConfig;
import aq.app.contfigs.SecurityConfig;

@SpringBootApplication
@EntityScan(basePackages = "aq.app.models")
@ComponentScan(basePackages = {"aq.app.controllers", "aq.app.converters", "aq.app.repositories"})
@EnableJpaRepositories(basePackages = "aq.app.repositories.jpa_data")
public class SpringBootTacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {
					SpringBootTacoCloudApplication.class,
					SecurityConfig.class,
					BootConfig.class}, args);
	}
}

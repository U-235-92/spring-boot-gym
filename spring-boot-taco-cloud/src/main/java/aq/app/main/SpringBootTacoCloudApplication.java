package aq.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;

import aq.app.configs.BootConfig;
import aq.app.configs.FileWriterIntegrationConfig;
import aq.app.configs.RabbitSenderConfiguration;
import aq.app.configs.SecurityConfig;

@SpringBootApplication
@EntityScan(basePackages = "aq.app.models")
@ComponentScan(basePackages = {"aq.app.controllers", 
		"aq.app.converters", 
		"aq.app.repositories", 
		"aq.app.msg_services.*"})
@EnableJpaRepositories(basePackages = "aq.app.repositories.jpa_data")
@IntegrationComponentScan(basePackages = {"aq.app.integration"})
public class SpringBootTacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {
					SpringBootTacoCloudApplication.class,
					SecurityConfig.class,
					BootConfig.class,
					RabbitSenderConfiguration.class,
					FileWriterIntegrationConfig.class}, args);
	}
}

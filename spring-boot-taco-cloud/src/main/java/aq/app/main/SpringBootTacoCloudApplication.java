package aq.app.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import aq.app.models.Ingredient;
import aq.app.models.Ingredient.Type;
import aq.app.repositories.IngredientRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"aq.app.controllers", "aq.app.converters", "aq.app.repositories"})
public class SpringBootTacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTacoCloudApplication.class, args);
	}
	
	@Bean
	CommandLineRunner dataLoader(IngredientRepository repo) { //ApplicationRunner
		return args -> {
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};
	}
}

package aq.app.contfigs;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import aq.app.models.Ingredient;
import aq.app.models.Ingredient.Type;
import aq.app.models.Taco;
import aq.app.models.User;
import aq.app.repositories.jpa_data.JpaDataIngredientRepository;
import aq.app.repositories.jpa_data.JpaTacoRepository;
import aq.app.repositories.jpa_data.JpaUserRepository;

@Configuration
public class BootConfig {

//	Load data after spring boot ran
	@Bean
	@Order(1)
	CommandLineRunner ingredientLoader(JpaDataIngredientRepository ingredientRepository) { // ApplicationRunner
		return args -> {
			ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};
	}

	@Bean
	@Order(2)
	CommandLineRunner userLoader(JpaUserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			userRepository.save(new User("Alice", passwordEncoder.encode("123"), "ALice K", "Test", "Test", "Test", "Test",
					"1234567890"));
			userRepository.save(new User("Alexander", passwordEncoder.encode("123"), "Alexander K", "Test", "Test", "Test",
					"Test", "0987654321"));
		};
	}

	@Bean 
	@Order(3)
	CommandLineRunner tacoLoader(JpaTacoRepository tacoRepository, JpaDataIngredientRepository ingredientRepository) {
		return args -> {
			Taco taco1 = new Taco(); 
			taco1.setName("Carnivore"); 
			taco1.setIngredients(Arrays.asList(
					ingredientRepository.findById("FLTO").get(),
					ingredientRepository.findById("GRBF").get(),
					ingredientRepository.findById("CARN").get(),
					ingredientRepository.findById("SRCR").get(),
					ingredientRepository.findById("SLSA").get(),
					ingredientRepository.findById("CHED").get())); 
			tacoRepository.save(taco1); 
			Taco taco2 = new Taco(); 
			taco2.setName("Bovine Bounty"); 
			taco2.setIngredients(Arrays.asList( 
					ingredientRepository.findById("COTO").get(), 
					ingredientRepository.findById("GRBF").get(), 
					ingredientRepository.findById("CHED").get(), 
					ingredientRepository.findById("JACK").get(), 
					ingredientRepository.findById("SRCR").get())); 
			tacoRepository.save(taco2); 
			Taco taco3 = new Taco(); 
			taco3.setName("Veg-Out"); 
			taco3.setIngredients(Arrays.asList( 
					ingredientRepository.findById("FLTO").get(), 
					ingredientRepository.findById("COTO").get(), 
					ingredientRepository.findById("TMTO").get(), 
					ingredientRepository.findById("LETC").get(), 
					ingredientRepository.findById("SLSA").get())); 
			tacoRepository.save(taco3); 
		};
	}
}

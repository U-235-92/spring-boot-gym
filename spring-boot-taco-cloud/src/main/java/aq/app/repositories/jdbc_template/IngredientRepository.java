package aq.app.repositories.jdbc_template;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import aq.app.models.Ingredient;

public interface IngredientRepository {

	List<Ingredient> findAll();
	Optional<Ingredient> findById(String id);
	Ingredient save(Ingredient ingredient);
}

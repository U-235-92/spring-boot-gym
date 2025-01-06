package aq.app.repositories;

import java.util.List;
import java.util.Optional;

import aq.app.models.Ingredient;

public interface IngredientRepository {

	List<Ingredient> findAll();
	Optional<Ingredient> findById(String id);
	Ingredient save(Ingredient ingredient);
}

package aq.app.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import aq.app.models.Ingredient;
import aq.app.repositories.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

	@Autowired
	@Qualifier("JdbcIngredientRepositoryImpl")
	private IngredientRepository ingredientRepository;

	@Override
	public Ingredient convert(String ingredientID) {
		return ingredientRepository.findById(ingredientID).orElse(null);
	}
}

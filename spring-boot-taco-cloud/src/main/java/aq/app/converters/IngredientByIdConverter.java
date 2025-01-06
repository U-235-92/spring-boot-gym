package aq.app.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import aq.app.models.Ingredient;
import aq.app.repositories.JdbcDataIngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

	@Autowired
	private JdbcDataIngredientRepository ingredientRepository;

	@Override
	public Ingredient convert(String ingredientID) {
		return ingredientRepository.findById(ingredientID).orElse(null);
	}
}

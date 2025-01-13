package aq.app.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Ingredient;
import aq.app.services.IngredientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class IngredientController {
	
	private final ApplicationContext context;
	
	@GetMapping("/find_all_ingredients")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Ingredient> findAllIngredients() {
		IngredientService ingredientService = context.getBean(IngredientService.class);
		return ingredientService.getAllIngredients();
	}
	
	@PostMapping("/add_ingredient")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
		IngredientService ingredientService = context.getBean(IngredientService.class);
		return ingredientService.addIngredient(ingredient);
	}
}

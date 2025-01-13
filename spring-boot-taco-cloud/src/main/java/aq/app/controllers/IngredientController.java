package aq.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Ingredient;
import aq.app.repositories.jpa_data.JpaIngredientRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:5082")
@RequiredArgsConstructor
public class IngredientController {

	private final JpaIngredientRepository ingredientRepository;
	
	@GetMapping
	public Iterable<Ingredient> allIngredients() {
		return ingredientRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	@DeleteMapping("/{ingredientId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteIngredient(@PathVariable String ingredientId) {
		ingredientRepository.deleteById(ingredientId);
	}
}

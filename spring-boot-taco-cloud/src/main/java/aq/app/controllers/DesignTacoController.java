package aq.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import aq.app.models.Ingredient;
import aq.app.models.Ingredient.Type;
import aq.app.repositories.jpa_data.JpaIngredientRepository;
import aq.app.models.Taco;
import aq.app.models.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

	@Autowired 
	private JpaIngredientRepository ingredientRepository;
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));
		Type[] types = Ingredient.Type.values();
		Arrays.stream(types).forEach(type -> model.addAttribute(type.name().toLowerCase(), filterByType(ingredients, type)));
		log.info("<<<Added ingredients to model>>>");
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(ingredient -> ingredient.getType().equals(type))
				.toList();
	}

	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		log.info("<<<Added Taco Order to model>>>");
		return new TacoOrder();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		log.info("<<<Added Taco to model>>>");
		return new Taco();
	}

	@GetMapping
	public String showDesignFrom() {
		log.info("<<<Called GET method of DesignTacoController>>>");
		return "design";
	}
	
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		if(errors.hasErrors())
			return "design";
		log.info("<<<Called POST method of DesignTacoController>>>");
		tacoOrder.addTaco(taco);
		log.info("Processing taco: " + taco);
		return "redirect:/orders/current";
	}
}

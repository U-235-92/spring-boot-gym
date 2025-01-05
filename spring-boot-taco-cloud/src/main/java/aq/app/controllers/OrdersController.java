package aq.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import aq.app.models.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrdersController {

	@GetMapping("/current")
	public String orderForm() {
		log.info("Called GET method of OrdersController");
		return "order-form";
	}
	
	@PostMapping
	public String processOrder(@ModelAttribute @Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus) {
		if(errors.hasErrors())
			return "order-form";
		log.info("Order submitted: " + tacoOrder);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}

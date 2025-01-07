package aq.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import aq.app.models.TacoOrder;
import aq.app.models.User;
import aq.app.repositories.jpa_data.JpaDataOrderRepository;
import aq.app.repositories.jpa_data.JpaUserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrdersController {

	@Autowired
	private JpaDataOrderRepository orderRepository;
	@Autowired 
	private JpaUserRepository userRepository;
	
	@GetMapping("/current")
	public String orderForm() {
		log.info("Called GET method of OrdersController");
		return "order-form";
	}
	
	@PostMapping
	public String processOrder(@ModelAttribute @Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus, Principal principal) {
		if(errors.hasErrors())
			return "order-form";
		log.info("Order submitted: " + tacoOrder);
//		Alternative way of getting user data from security context.
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		User user = (User) authentication.getPrincipal();
//		@AuthenticationPrincipal - replace <Principal> as method param on <User> and add to this one annotation. It's the second way of getting user data from security context.
		User user = userRepository.findByUsername(principal.getName());
		tacoOrder.setUser(user);
		orderRepository.save(tacoOrder);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}

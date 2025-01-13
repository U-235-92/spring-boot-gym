package aq.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import aq.app.models.TacoOrder;
import aq.app.models.User;
import aq.app.repositories.jpa_data.JpaOrderRepository;
import aq.app.repositories.jpa_data.JpaUserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrdersController {

	@Autowired
	private JpaOrderRepository orderRepository;
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
	
	@ResponseBody
	@PutMapping(path = "/{orderId}", consumes = "application/json")
	public TacoOrder putOrder(@PathVariable Long orderId, @RequestBody TacoOrder order) {
		order.setId(orderId);
		return orderRepository.save(order);
	}
	
	@ResponseBody
	@PatchMapping(path = "/{orderId}", consumes = "application/json")
	public TacoOrder patchOrder(@PathVariable Long orderId, @RequestBody TacoOrder patch) {
		TacoOrder order = orderRepository.findById(orderId).get();
		if (patch.getDeliveryName() != null) {
			order.setDeliveryName(patch.getDeliveryName());
		}
		if (patch.getDeliveryStreet() != null) {
			order.setDeliveryStreet(patch.getDeliveryStreet());
		}
		if (patch.getDeliveryCity() != null) {
			order.setDeliveryCity(patch.getDeliveryCity());
		}
		if (patch.getDeliveryState() != null) {
			order.setDeliveryState(patch.getDeliveryState());
		}
		if (patch.getDeliveryZip() != null) {
			order.setDeliveryZip(patch.getDeliveryZip());
		}
		if (patch.getCcNumber() != null) {
			order.setCcNumber(patch.getCcNumber());
		}
		if (patch.getCcExpiration() != null) {
			order.setCcExpiration(patch.getCcExpiration());
		}
		if (patch.getCcCVV() != null) {
			order.setCcCVV(patch.getCcCVV());
		}
		return orderRepository.save(order);
	}
	
	@ResponseBody
	@DeleteMapping("/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable Long orderId) {
		try {
			orderRepository.deleteById(orderId);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}

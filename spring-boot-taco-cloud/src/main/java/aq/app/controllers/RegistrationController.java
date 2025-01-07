package aq.app.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aq.app.models.RegistrationForm;
import aq.app.models.User;
import aq.app.repositories.jpa_data.JpaUserRepository;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

	private JpaUserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public String registerForm() {
		return "registration";
	}
	
	@PostMapping
	public String postMethodName(RegistrationForm form) {
		User user = form.toUser(passwordEncoder);
		userRepository.save(user);
		return "redirect:/login";
	}
	
}

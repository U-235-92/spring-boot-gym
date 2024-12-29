package aq.app.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aq.app.services.LoginService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {

	private ApplicationContext context;
	
	@GetMapping("/")
	public String loginGet() {
		return "login.html";
	}
	
	@PostMapping("/")
	public String loginPost(@RequestParam String username, @RequestParam String password, Model page) {
		var loginService = context.getBean(LoginService.class);
		loginService.setUsername(username);
		loginService.setPassword(password);
		boolean loggedIn = loginService.login();
		if(loggedIn)
			return "redirect:/main";
		else
			page.addAttribute("message", "Login faild!");
		return "login.html";
	}
}

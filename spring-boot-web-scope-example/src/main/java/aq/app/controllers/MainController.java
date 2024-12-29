package aq.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aq.app.services.LoggedUserManagmentService;
import aq.app.services.LoginCountService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final LoggedUserManagmentService loggedUserManagmentService;
	private final LoginCountService loginCountService;
	
	@GetMapping("/main")
	public String home(@RequestParam(required = false) String logout, Model page) {
		if(logout != null)
			loggedUserManagmentService.setUsername(null);
		String username = loggedUserManagmentService.getUsername();
		int count = loginCountService.getCount();
		if(username == null)
			return "redirect:/";
		page.addAttribute("username", username);
		page.addAttribute("loginCount", count);
		return "main.html";
	}
}

package aq.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

//	Request parameter using example
//	@RequestMapping("/home")
//	public String home(@RequestParam String color, @RequestParam String name, Model page) {
//		page.addAttribute("username", name);
//		page.addAttribute("color", color);
//		return "home.html";
//	}
	
//	Path parameter using example
	@RequestMapping("/home/{color}")
	public String home(@PathVariable String color, Model page) {
		page.addAttribute("username", "Alice");
		page.addAttribute("color", color);
		return "home.html";
	}
}

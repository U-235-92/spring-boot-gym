package aq.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import aq.app.models.Product;
import aq.app.services.ProductService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping(path = "/products")
	public String viewProducts(Model page) {
		var products = productService.findAll();
		page.addAttribute("products", products);
		return "products.html";
	}
	
	@PostMapping(path = "/products")
	public String addProduct(Product product, Model page) {
		productService.addProduct(product);
		var products = productService.findAll();
		page.addAttribute("products", products);
		return "products.html";
	}
}

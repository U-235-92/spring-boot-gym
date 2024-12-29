package aq.app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Country;

@RestController
public class CountryController {

	@GetMapping("/france")
	public ResponseEntity<Country> france() {
		return ResponseEntity.
					status(HttpStatus.ACCEPTED)
					.header("continent", "Europe")
					.header("capital", "Paris")
					.header("favourite_food", "cheese and wine")
					.body(Country.of("France", 67));
	}
	
	@GetMapping("/countries")
	public List<Country> countries() {
		return List.of(Country.of("France", 67), Country.of("Spain", 47));
	}
}

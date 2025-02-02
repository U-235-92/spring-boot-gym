package aq.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Car;
import aq.app.services.CarService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	@GetMapping(path = "/all", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Flux<Car> getCars() {
		return carService.getCars();
	}
	
	@PostMapping(path = "/create", consumes = "application/json",  produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Car> create(@RequestBody Car car) {
		return carService.create(car);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Mono<Void> delete(@PathVariable Long id) {
		return carService.delete(id);
	}
}

package aq.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aq.app.models.Car;
import aq.app.repositories.CarRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;
	
	public Flux<Car> getCars() {
		return carRepository.findAll();
	}
	
	public Mono<Car> create(Car car) {
		return carRepository.save(car);
	}
	
	public Mono<Void> delete(Long id) {
		return carRepository.deleteById(id);
	}
}

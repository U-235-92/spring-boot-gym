package aq.app.repositories;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import aq.app.models.Car;

public interface CarRepository extends ReactiveCrudRepository<Car, Long> {

	@Modifying
	@Query("DELETE FROM cars WHERE cars.id = :id")
	void customDeleteById(@Param("id") Long id);
}

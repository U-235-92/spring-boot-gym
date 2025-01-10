package aq.app.repositories.jpa_data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import aq.app.models.Taco;

public interface JpaTacoRepository extends CrudRepository<Taco, Long> {

	Page<Taco> findAll(Pageable pageable);
}

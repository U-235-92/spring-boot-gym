package aq.app.repositories.jpa_data;

import org.springframework.data.repository.CrudRepository;

import aq.app.models.TacoOrder;

public interface JpaOrderRepository extends CrudRepository<TacoOrder, Long>{

}

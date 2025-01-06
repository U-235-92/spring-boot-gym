package aq.app.repositories;

import org.springframework.data.repository.CrudRepository;

import aq.app.models.TacoOrder;

public interface JdbcDataOrderRepository extends CrudRepository<TacoOrder, Long> {

}

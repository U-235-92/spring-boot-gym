package aq.app.repositories;

import org.springframework.data.repository.CrudRepository;

import aq.app.models.Ingredient;

public interface JdbcDataIngredientRepository extends CrudRepository<Ingredient, String> {

}

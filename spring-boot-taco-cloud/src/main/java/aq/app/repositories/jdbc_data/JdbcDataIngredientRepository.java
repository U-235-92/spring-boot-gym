package aq.app.repositories.jdbc_data;

import org.springframework.data.repository.CrudRepository;

import aq.app.models.Ingredient;

public interface JdbcDataIngredientRepository extends CrudRepository<Ingredient, String> {

}

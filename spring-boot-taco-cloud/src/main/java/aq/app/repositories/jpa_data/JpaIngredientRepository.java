package aq.app.repositories.jpa_data;

import org.springframework.data.repository.CrudRepository;

import aq.app.models.Ingredient;

public interface JpaIngredientRepository extends CrudRepository<Ingredient, String> {

}

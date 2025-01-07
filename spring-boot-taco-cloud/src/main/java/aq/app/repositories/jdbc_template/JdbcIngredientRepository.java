package aq.app.repositories.jdbc_template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import aq.app.models.Ingredient;
import lombok.RequiredArgsConstructor;

@Repository
@Qualifier("JdbcIngredientRepositoryImpl")
@RequiredArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Ingredient> findAll() {
		String sql = "SELECT id, name, type FROM Ingredient";
		return jdbcTemplate.query(sql, this::maptRowToIngredient);
	}

	@Override
	public Optional<Ingredient> findById(String id) {
		String sql = "SELECT id, name, type FROM Ingredient WHERE id=?";
		List<Ingredient> results = jdbcTemplate.query(sql, this::maptRowToIngredient, id);
		return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
	}

	private Ingredient maptRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
		Ingredient ingredient = new Ingredient(
				resultSet.getString("id"), 
				resultSet.getString("name"), 
				Ingredient.Type.valueOf(resultSet.getString("type")));
		return ingredient;
	}
	
	@Override
	public Ingredient save(Ingredient ingredient) {
		String sql = "INSERT INTO Ingredient (id, name, type) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}
}

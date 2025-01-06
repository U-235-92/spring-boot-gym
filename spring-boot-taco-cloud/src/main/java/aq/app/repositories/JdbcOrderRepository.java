package aq.app.repositories;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aq.app.models.Ingredient;
import aq.app.models.Taco;
import aq.app.models.TacoOrder;
import lombok.RequiredArgsConstructor;

@Repository
@Qualifier("JdbcOrderRepositoryImpl")
@RequiredArgsConstructor
public class JdbcOrderRepository implements OrderRepository {

	private final JdbcOperations jdbcOperations;
	
	@Override
	@Transactional
	public TacoOrder save(TacoOrder tacoOrder) {
		String sql = "INSERT INTO Taco_Order " + "(delivery_name, delivery_street, delivery_city, "
				+ "delivery_state, delivery_zip, cc_number, " + "cc_expiration, cc_cvv, placed_at) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				sql, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);
		tacoOrder.setPlacedAt(new Date());
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
				tacoOrder.getDeliveryName(), tacoOrder.getDeliveryStreet(), tacoOrder.getDeliveryCity(),  
				tacoOrder.getDeliveryState(), tacoOrder.getDeliveryZip(), tacoOrder.getCcNumber(), 
				tacoOrder.getCcExpiration(), tacoOrder.getCcCVV(), tacoOrder.getPlacedAt()));
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long orderId = keyHolder.getKey().longValue();
		tacoOrder.setId(orderId);
		List<Taco> tacos = tacoOrder.getTacos();
		int i = 0;
		for (Taco taco : tacos) {
			saveTaco(orderId, i++, taco);
		}
		return tacoOrder; 
	}
	
	private long saveTaco(Long orderId, int orderKey, Taco taco) {
		String sql = "INSERT INTO Taco " + "(name, created_at, taco_order, taco_order_key) VALUES (?, ?, ?, ?)";
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				sql, Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG);
		pscf.setReturnGeneratedKeys(true);
		PreparedStatementCreator psc = pscf
				.newPreparedStatementCreator(Arrays.asList(taco.getName(), taco.getCreatedAt(), orderId, orderKey));
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long tacoId = keyHolder.getKey().longValue();
		taco.setId(tacoId);
		saveIngredientRefs(tacoId, taco.getIngredients());
		return tacoId;
	}
	
	private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
		int key = 0;
		String sql = "INSERT INTO Ingredient_Ref (ingredient, taco, taco_key) VALUES (?, ?, ?)";
		for(Ingredient ingredient : ingredients) {
			jdbcOperations.update(sql, ingredient, tacoId, key++);
		}
	}
}

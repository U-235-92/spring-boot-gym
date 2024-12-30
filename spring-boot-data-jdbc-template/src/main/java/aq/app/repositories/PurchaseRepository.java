package aq.app.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import aq.app.models.Purchase;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public void storePurchase(Purchase purchase) {
		String sql = "INSERT INTO purchase(product, price) VALUES(?, ?)";
		jdbcTemplate.update(sql, purchase.getProduct(), purchase.getPrice());
	}
	
	public List<Purchase> findAllPurchases() {
		String sql = "SELECT * FROM purchase";
		RowMapper<Purchase> purchaseRowMapper = (resultSet, rowNum) -> {
			Purchase purchase = new Purchase();
			purchase.setId(resultSet.getInt("id"));
			purchase.setProduct(resultSet.getString("product"));
			purchase.setPrice(resultSet.getBigDecimal("price"));
			return purchase;
		};
		return jdbcTemplate.query(sql, purchaseRowMapper);
	}
}

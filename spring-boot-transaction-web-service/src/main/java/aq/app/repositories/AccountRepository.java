package aq.app.repositories;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import aq.app.models.Account;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public Account findAccountById(int id) {
		String sql = "SELECT * FROM accounts WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), id);
	}
	
	public void changeAmount(int id, BigDecimal amount) {
		String sql = "UPDATE accounts SET amount = ? WHERE id = ?";
		jdbcTemplate.update(sql, amount, id);
	}
	
	public List<Account> findAllAccounts() {
		String sql = "SELECT * FROM accounts";
		return jdbcTemplate.query(sql, new AccountRowMapper());
	}
	
	private static class AccountRowMapper implements RowMapper<Account> {

		@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			BigDecimal amount = rs.getBigDecimal("amount");
			Account account = new Account(name, amount);
			account.setId(id);
			return account;
		}
	}
}

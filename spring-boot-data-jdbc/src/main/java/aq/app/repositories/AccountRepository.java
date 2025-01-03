package aq.app.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import aq.app.models.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
	
	@Query("SELECT * FROM accounts")
	public Iterable<Account> findAll();
	
	@Query("SELECT * FROM accounts WHERE name = :name")
	public List<Account> findAccountsByName(String name);
	
	@Modifying
	@Query("UPDATE amount SET amount = :amount WHERE id = :id")
	public void changeAmount(int id, BigDecimal amount);
}

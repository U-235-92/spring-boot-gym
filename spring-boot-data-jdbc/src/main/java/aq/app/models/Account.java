package aq.app.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Account {

	@Id
	private int id;
	private String name;
	private BigDecimal amount;
}

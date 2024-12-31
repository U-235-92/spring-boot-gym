package aq.app.models;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Account {

	private int id;
	@NonNull
	private String name;
	@NonNull
	private BigDecimal amount;
}

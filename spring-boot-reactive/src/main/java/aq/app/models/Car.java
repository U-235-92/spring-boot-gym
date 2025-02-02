package aq.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Table(name = "cars")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Car {

	@Id
	private Long id;
	@NonNull
	private String name;
}

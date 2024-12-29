package aq.app.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class Country {

	private String name;
	private int population;
}

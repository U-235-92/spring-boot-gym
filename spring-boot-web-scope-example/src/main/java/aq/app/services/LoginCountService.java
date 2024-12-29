package aq.app.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Getter;

@Service
@ApplicationScope
public class LoginCountService {

	@Getter
	private int count;
	
	public void increment() {
		count++;
	}
}

package aq.app.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Service
@RequestScope
@RequiredArgsConstructor
public class LoginService {

	private String username;
	private String password;
	private final LoggedUserManagmentService loggedUserManagmentService;
	private final LoginCountService loginCountService;
	
	public boolean login() {
		loginCountService.increment();
		if("alice".equals(username) && "123".equals(password)) {
			loggedUserManagmentService.setUsername(username);
			return true;
		}
		return false;
	}

}

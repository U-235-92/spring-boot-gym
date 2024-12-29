package aq.app.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
@SessionScope
public class LoggedUserManagmentService {

	private String username;
}

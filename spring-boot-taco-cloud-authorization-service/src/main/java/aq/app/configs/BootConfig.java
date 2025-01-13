package aq.app.configs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import aq.app.entities.User;
import aq.app.repositories.UserRepository;

@Configuration
public class BootConfig {

	@Bean
	ApplicationRunner usersLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			User alice = new User("Alice", passwordEncoder.encode("123"), "ROLE_ADMIN");
			User alexander = new User("Alexander", passwordEncoder.encode("123"), "ROLE_ADMIN");
			userRepository.save(alice);
			userRepository.save(alexander);
		};
	}
}

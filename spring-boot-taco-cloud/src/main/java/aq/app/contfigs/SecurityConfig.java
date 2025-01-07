package aq.app.contfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import aq.app.models.User;
import aq.app.repositories.jpa_data.JpaUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	UserDetailsService inMemoryuserDetailsService(PasswordEncoder passwordEncoder) {
//		List<UserDetails> userDetails = new ArrayList<UserDetails>();
//		userDetails.add(new User("Alice", passwordEncoder.encode("123"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//		userDetails.add(new User("Alexander", passwordEncoder.encode("123"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//		return new InMemoryUserDetailsManager(userDetails);
//	}
	
	@Bean
	UserDetailsService databaseUserDetailsService(JpaUserRepository userRepository) {
		return username -> {
			User user = userRepository.findByUsername(username);
			if(user != null)
				return user;
			throw new UsernameNotFoundException("User " + username + " not found"); 
		};
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(authorization -> 
						authorization.requestMatchers(HttpMethod.POST, "/design", "/orders").hasAnyRole("USER"))
				.authorizeHttpRequests(authorization -> 
						authorization.requestMatchers(HttpMethod.GET, "/design", "/orders", "/orders/**").hasAnyRole("USER"))
				.authorizeHttpRequests(authorization -> 
						authorization.requestMatchers("/", "/**").permitAll())
				.formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/design"))
				.logout(logout -> logout.clearAuthentication(true).invalidateHttpSession(true).logoutSuccessUrl("/"))
//				.csrf(csrf -> csrf.disable()) //disable csrf to able post/put etc. methods work (as alternative you could add th:action=@{/path} to enable auto generated csrf. It recommendation works if Thymeleaf enabled
				.build();
	}
}

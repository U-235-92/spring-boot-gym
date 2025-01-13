package aq.app.entities;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	@NonNull
	@Column(name = "user_name")
	private String username;
	@NonNull
	@Column(name = "user_password")
	private String password;
	@NonNull
	@Column(name = "user_role")
	private String role;
	
	public static UserDetails userToUserDetails(User user) {
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole())); 
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
}

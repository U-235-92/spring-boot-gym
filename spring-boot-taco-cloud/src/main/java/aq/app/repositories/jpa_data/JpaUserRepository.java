package aq.app.repositories.jpa_data;

import org.springframework.data.repository.CrudRepository;

import aq.app.models.User;

public interface JpaUserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
}

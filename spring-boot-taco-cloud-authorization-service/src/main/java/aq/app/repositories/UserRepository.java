package aq.app.repositories;

import org.springframework.data.repository.CrudRepository;

import aq.app.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
}

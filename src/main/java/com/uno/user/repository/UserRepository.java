package com.uno.user.repository;

import java.util.Optional;


import com.uno.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	 Optional<User> findById(Integer id);
}
package com.phobod.test.task.repository;

import org.springframework.data.repository.CrudRepository;

import com.phobod.test.task.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByName(String name);
}

package com.phobod.test.task.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.phobod.test.task.domain.Item;

public interface ObjectRepository extends CrudRepository<Item, Long> {
	List<Item> findAll();

	Item findByTitle(String title);
}

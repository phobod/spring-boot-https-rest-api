package com.phobod.test.task.service;

import java.util.List;

import javax.annotation.Nonnull;

import com.phobod.test.task.domain.Item;

public interface ObjectService {
	@Nonnull
	List<Item> findAllItems();

	@Nonnull
	Item findById(@Nonnull Long id);

	@Nonnull
	Item save(@Nonnull Item item);

	void update(@Nonnull Item updatedItem, @Nonnull Long id);

	void delete(@Nonnull Long id);

}

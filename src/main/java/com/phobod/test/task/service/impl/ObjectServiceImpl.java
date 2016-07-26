package com.phobod.test.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phobod.test.task.domain.Item;
import com.phobod.test.task.exception.CantProcessItemException;
import com.phobod.test.task.repository.ObjectRepository;
import com.phobod.test.task.service.ObjectService;

@Service
public class ObjectServiceImpl implements ObjectService {
	@Autowired
	private ObjectRepository objectRepository;

	@Override
	public List<Item> findAllItems() {
		return objectRepository.findAll();
	}

	@Override
	public Item findById(Long id) {
		return objectRepository.findOne(id);
	}

	@Override
	public Item save(Item item) {
		return objectRepository.save(item);
	}

	@Override
	public void update(Item updatedItem, Long id) {
		if (!objectRepository.exists(id)) {
			throw new CantProcessItemException("Can't update object. There is no object with id = " + id);
		}
		objectRepository.save(updatedItem);
	}

	@Override
	public void delete(Long id) {
		if (!objectRepository.exists(id)) {
			throw new CantProcessItemException("Can't delete object. There is no object with id = " + id);
		}
		objectRepository.delete(id);
	}

}

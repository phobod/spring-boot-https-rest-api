package com.phobod.test.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phobod.test.task.domain.Item;
import com.phobod.test.task.service.ObjectService;

@RestController
@RequestMapping("/object")
public class ObjectController {
	@Autowired
	private ObjectService objectService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Item> findItems() {
		return objectService.findAllItems();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Item findItemById(@PathVariable Long id) {
		return objectService.findById(id);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public Item addItem(@RequestBody Item item) {
		return objectService.save(item);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
	public void updateItem(@RequestBody Item updatedItem, @PathVariable Long id) {
		objectService.update(updatedItem, id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Long id) {
		objectService.delete(id);
	}

}

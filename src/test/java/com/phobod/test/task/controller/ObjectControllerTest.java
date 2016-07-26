package com.phobod.test.task.controller;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.phobod.test.task.domain.Item;
import com.phobod.test.task.service.ObjectService;

@RunWith(MockitoJUnitRunner.class)
public class ObjectControllerTest {

	@InjectMocks
	private ObjectController objectController = new ObjectController();

	@Mock
	private ObjectService objectService;

	private long objictId;
	private Item testItem;

	@Before
	public void setUp() throws Exception {
		objictId = 123;
		testItem = new Item();
		testItem.setTitle("title");
		testItem.setValue(123);
	}

	@Test
	public void testFindItems() {
		objectController.findItems();
		verify(objectService).findAllItems();
	}

	@Test
	public void testFindItemById() {
		objectController.findItemById(objictId);
		verify(objectService).findById(objictId);
	}

	@Test
	public void testAddItem() {
		objectController.addItem(testItem);
		verify(objectService).save(testItem);
	}

	@Test
	public void testUpdateItem() {
		objectController.updateItem(testItem, objictId);
		verify(objectService).update(testItem, objictId);
	}

	@Test
	public void testDeleteItem() {
		objectController.deleteItem(objictId);
		verify(objectService).delete(objictId);
	}

}

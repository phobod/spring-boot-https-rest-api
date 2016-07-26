package com.phobod.test.task.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.phobod.test.task.domain.Item;
import com.phobod.test.task.exception.CantProcessItemException;
import com.phobod.test.task.repository.ObjectRepository;
import com.phobod.test.task.service.ObjectService;

@RunWith(MockitoJUnitRunner.class)
public class ObjectServiceImplTest {

	@InjectMocks
	private ObjectService objectService = new ObjectServiceImpl();

	@Mock
	private ObjectRepository objectRepository;

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
	public void testFindAllItems() {
		objectService.findAllItems();
		verify(objectRepository).findAll();
	}

	@Test
	public void testFindById() {
		objectService.findById(objictId);
		verify(objectRepository).findOne(objictId);
	}

	@Test
	public void testSave() {
		objectService.save(testItem);
		verify(objectRepository).save(testItem);
	}

	@Test(expected = CantProcessItemException.class)
	public void testUpdateWithException() {
		when(objectRepository.exists(objictId)).thenReturn(false);
		objectService.update(testItem, objictId);
	}

	@Test
	public void testUpdateSuccess() {
		when(objectRepository.exists(objictId)).thenReturn(true);
		objectService.update(testItem, objictId);
		verify(objectRepository).save(testItem);
	}

	@Test(expected = CantProcessItemException.class)
	public void testDeleteWithException() {
		when(objectRepository.exists(objictId)).thenReturn(false);
		objectService.delete(objictId);
	}

	@Test
	public void testDeleteSuccess() {
		when(objectRepository.exists(objictId)).thenReturn(true);
		objectService.delete(objictId);
		verify(objectRepository).delete(objictId);
	}

}

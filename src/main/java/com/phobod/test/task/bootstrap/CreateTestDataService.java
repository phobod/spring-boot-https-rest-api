package com.phobod.test.task.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phobod.test.task.domain.Item;
import com.phobod.test.task.domain.User;
import com.phobod.test.task.repository.ObjectRepository;
import com.phobod.test.task.repository.UserRepository;

@Service
public class CreateTestDataService implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ObjectRepository objectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (objectRepository.findByTitle("testTitle_1") == null) {
			Item testItem = new Item();
			testItem.setTitle("testTitle_1");
			testItem.setValue(1);
			objectRepository.save(testItem);
		}
		if (userRepository.findByName("user") == null) {
			User testUser = new User();
			testUser.setName("user");
			testUser.setPassword(passwordEncoder.encode("user"));
			userRepository.save(testUser);
		}
	}
}

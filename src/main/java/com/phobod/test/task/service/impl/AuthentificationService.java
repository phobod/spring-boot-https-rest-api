package com.phobod.test.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phobod.test.task.domain.User;
import com.phobod.test.task.repository.UserRepository;
import com.phobod.test.task.security.CurrentUser;

@Service
public class AuthentificationService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found by name: " + username);
		}
		return new CurrentUser(user);
	}

}

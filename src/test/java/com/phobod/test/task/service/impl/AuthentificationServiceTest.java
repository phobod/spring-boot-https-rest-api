package com.phobod.test.task.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.phobod.test.task.domain.User;
import com.phobod.test.task.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class AuthentificationServiceTest {
	@InjectMocks
	private UserDetailsService userDetailsService = new AuthentificationService();

	@Mock
	private UserRepository userRepository;

	private User testUser;

	@Before
	public void setUp() throws Exception {
		testUser = new User();
		testUser.setName("testName");
		testUser.setPassword("password");
	}

	@Test(expected = UsernameNotFoundException.class)
	public final void testLoadUserByUsernameWithException() {
		when(userRepository.findByName("testName")).thenReturn(null);
		userDetailsService.loadUserByUsername("testName");
	}

	@Test
	public final void testLoadUserByUsernameSuccess() {
		when(userRepository.findByName(testUser.getName())).thenReturn(testUser);
		UserDetails userDetails = userDetailsService.loadUserByUsername(testUser.getName());
		verify(userRepository).findByName(testUser.getName());
		assertEquals(testUser.getName(), userDetails.getUsername());
	}

}

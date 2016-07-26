package com.phobod.test.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.phobod.test.task.security.AddPrincipalHeadersFilter;
import com.phobod.test.task.security.RestAuthenticationFailureHandler;
import com.phobod.test.task.security.RestAuthenticationSuccessHandler;
import com.phobod.test.task.service.impl.AuthentificationService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthentificationService authentificationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/webjars/**", "/angular/**", "/html/**").permitAll()
				.anyRequest().authenticated();
		http.formLogin().successHandler(new RestAuthenticationSuccessHandler())
				.failureHandler(new RestAuthenticationFailureHandler()).loginPage("/login")
				.usernameParameter("username").passwordParameter("password").permitAll();
		http.logout().logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
		http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
		http.rememberMe().disable();
		http.csrf().disable();
		http.addFilterAfter(new AddPrincipalHeadersFilter(), LogoutFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authentificationService).passwordEncoder(passwordEncoder());
	}

}

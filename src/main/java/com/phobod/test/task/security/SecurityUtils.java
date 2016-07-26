package com.phobod.test.task.security;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.phobod.test.task.domain.User;

public class SecurityUtils {

	public static void addPrincipalHeaders(HttpServletResponse resp) {
		User user = SecurityUtils.getCurrentUser();
		if (user != null) {
			resp.setHeader("PrimcipalName", user.getName());
		}
	}

	public static @Nullable User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof CurrentUser) {
			return ((CurrentUser) principal).getUser();
		} else {
			return null;
		}
	}
}

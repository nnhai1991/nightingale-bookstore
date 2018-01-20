package com.nightingale.web.security.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.nightingale.app.service.UserService;

/**
 * @author hai
 *
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		userService.loginSucceeded(((CustomUserDetails) authentication.getPrincipal()).getUsername());
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

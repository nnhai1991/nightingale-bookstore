package com.nightingale.web.security.user;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.nightingale.app.service.UserService;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private String errorMessage = "";

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String email = request.getParameter("email");
		
		userService.loginFailed(email);

		errorMessage = exception.getMessage();

		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			errorMessage = "bad_credentials";
		} else if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
			errorMessage = "bad_credentials";
		} else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
			errorMessage = "account_disabled";
		} else if (exception.getClass().isAssignableFrom(LockedException.class)){
			errorMessage="account_locked";
		} else {
			errorMessage = "bad_credentials";
		}

		getRedirectStrategy().sendRedirect(request, response, "/login?error="+errorMessage);

	}
}
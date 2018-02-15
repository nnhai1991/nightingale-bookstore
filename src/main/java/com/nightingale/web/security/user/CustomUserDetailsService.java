package com.nightingale.web.security.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nightingale.app.entity.Role;
import com.nightingale.app.entity.User;

import com.nightingale.app.repository.RoleRepository;
import com.nightingale.app.service.UserService;
import com.nightingale.web.util.UtilValidation;

public class CustomUserDetailsService implements UserDetailsService {

	private static final String ROLE_PREFIX = "ROLE_";

	private static Pattern pattern = Pattern.compile(UtilValidation.LOGIN_VALID_EMAIL);

	public static boolean isValidEmail(String username) {
		return pattern.matcher(username).matches();
	}

	private UserService userService;
	private RoleRepository roleRepository;

	@Autowired
	public CustomUserDetailsService(UserService userService, RoleRepository roleRepository) {
		this.userService = userService;
		this.roleRepository = roleRepository;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username.matches(UtilValidation.EMAIL_CHARACTERS)) {
			com.nightingale.app.entity.User user = null;

			user = userService.readByUsername(username);

			if (user == null)
				throw new UsernameNotFoundException(username);

			CustomUserDetails customUserDetails = new CustomUserDetails(user, getAuthorities(user));
			return customUserDetails;
		} else

			throw new UsernameNotFoundException(username);
	}

	public Collection<? extends GrantedAuthority> getAuthorities(User user) {

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Role role;
			role = roleRepository.findOne(user.getRoleId());
			authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getCode()));
		return authorities;
	}

}

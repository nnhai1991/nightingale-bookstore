package com.nightingale.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nightingale.Constants;
import com.nightingale.entity.EmailToken;
import com.nightingale.entity.Role;
import com.nightingale.entity.User;
import com.nightingale.exception.NightingaleException;
import com.nightingale.model.dto.UserForUpdate;
import com.nightingale.model.dto.UserForUpdatePassword;
import com.nightingale.repository.EmailTokenRepository;
import com.nightingale.repository.UserRepository;
import com.nightingale.service.RoleService;
import com.nightingale.service.UserService;
import com.nightingale.util.HashingUtil;
import com.nightingale.util.web.UtilValidation;

@DependsOn("roleServiceImpl")
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleService roleService;
	@Autowired
	private EmailTokenRepository emailTokenRepository;

	@Override
	public User readFromToken(String token) {
		EmailToken emailToken;
		emailToken = emailTokenRepository.findByToken(token);
		if (emailToken == null || emailToken.getExpiryDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
			return null;
		}
		return userRepository.findByEmail(emailToken.getEmail());
	}

	@Transactional
	@Override
	public String createPasswordToken(Integer userId) {
		if (userId == null)
			return null;
		User user = this.read(userId);
		if (user == null)
			return null;
		LocalDateTime expiryDate = LocalDateTime.now();
		expiryDate = expiryDate.plusHours(8);
		EmailToken emailToken = new EmailToken();
		emailToken.setEmail(user.getEmail());
		emailToken.setExpiryDate(Timestamp.valueOf(expiryDate));
		emailToken.setToken(HashingUtil.hashMD5Hex(user.getEmail() + new Date().toString()));
		emailTokenRepository.save(emailToken);
		return emailToken.getToken();
	}

	@Transactional
	@Override
	public Boolean create(UserForUpdate user) {
		if (user != null) {
			User newUser = new User();
			try {
				newUser.setEmail(user.getEmail());
				newUser.setEnabled(user.getEnabled());
				newUser.setFirstName(user.getFirstName());
				newUser.setLastName(user.getLastName());
				newUser.setRole(roleService.read(user.getRoleId()));
				newUser.setCreatedBy(user.getCreatedBy());
				newUser.setPassword(passwordEncoder.encode(new Date().toString()));
				newUser.setFailedLoginAttempt(0);
				newUser.setTimezone(user.getTimezone());
				boolean valid = userRepository.save(newUser) != null;
				if (valid) {
					LocalDateTime expiryDate = LocalDateTime.now();
					expiryDate = expiryDate.plusHours(8);
					EmailToken emailToken = new EmailToken();
					emailToken.setEmail(user.getEmail());
					emailToken.setExpiryDate(Timestamp.valueOf(expiryDate));
					emailToken.setToken(HashingUtil.hashMD5Hex(user.getEmail() + new Date().toString()));
					emailTokenRepository.save(emailToken);
				}
				return valid;
			} catch (DataIntegrityViolationException exception) {
				exception.printStackTrace();
				throw new NightingaleException(exception.getStackTrace(), "create User", "", "Failed to create User",
						user);
			}
		}
		return false;
	}

	@Override
	public User create(User user) {
		if (user != null)
			try {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setFailedLoginAttempt(0);

				return userRepository.save(user);
			} catch (DataIntegrityViolationException exception) {
				exception.printStackTrace();
				throw new NightingaleException(exception.getStackTrace(), "create User", "", "Failed to create User",
						user);
			}
		return null;
	}

	@Override
	public User read(Integer userId) {

		if (UtilValidation.isValidId(userId))
			return userRepository.findOne(userId);
		return null;
	}

	@Override
	public void delete(Integer userId) {

		if (UtilValidation.isValidId(userId)) {
			userRepository.delete(userId);
		}
	}

	@Override
	public List<User> getListAll() {

		return userRepository.findAll();
	}

	@Override
	public User readByUsername(String username) {
		if (UtilValidation.isValidString(username))
			return userRepository.findByEmail(username);
		return null;
	}

	@Transactional
	@Override
	public Boolean loginFailed(String username) {

		if (UtilValidation.isValidString(username)) {
			User user = readByUsername(username);

			if (user != null) {

				if (incrementFailedLoginAttempt(user)) {

					Integer maxFailed;
					try {
						maxFailed = Constants.MAX_FAILED_LOGIN_ATTEMPTS;

						if (user.getFailedLoginAttempt() >= maxFailed) {
							user.setEnabled(false);
							return userRepository.save(user) != null;
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}

					return true;

				}

			}
		}

		return false;
	}

	@Override
	public Boolean loginSucceeded(String username) {

		if (UtilValidation.isValidString(username)) {
			User user = readByUsername(username);

			if (user != null)
				return resetFailedLoginAttempt(user);
		}

		return false;
	}

	@Override
	public Boolean incrementFailedLoginAttempt(User user) {

		if (user != null) {
			user.setFailedLoginAttempt(user.getFailedLoginAttempt() + 1);
			return userRepository.save(user) != null;
		}

		return false;

	}

	@Override
	public Boolean resetFailedLoginAttempt(User user) {

		if (user != null) {
			user.setFailedLoginAttempt(0);
			return userRepository.save(user) != null;
		}

		return false;
	}

	@Override
	public Boolean activateUser(String username) {

		if (UtilValidation.isValidString(username)) {

			User user = readByUsername(username);

			if (user != null && !user.isEnabled()) {
				user.setEnabled(true);
				return userRepository.save(user) != null;
			}
		}

		return false;
	}

	@Override
	public Boolean deactivateUser(String username) {

		if (UtilValidation.isValidString(username)) {
			User user = readByUsername(username);

			if (user != null && user.isEnabled()) {
				user.setEnabled(false);
				return userRepository.save(user) != null;
			}
		}

		return false;
	}

	@Override
	public Boolean update(UserForUpdate userForUpdate) {

		if (userForUpdate != null) {

			boolean isRoleValid = false;

			for (Role role : roleService.getAssignableRoles()) {
				if (userForUpdate.getRoleId().equals(role.getId())) {
					isRoleValid = true;
					break;
				}
			}

			if (isRoleValid) {

				User user = userRepository.findOne(userForUpdate.getId());
				user.setEmail(userForUpdate.getEmail());
				user.setRole(roleService.read(userForUpdate.getRoleId()));
				user.setFirstName(userForUpdate.getFirstName());
				user.setLastName(userForUpdate.getLastName());
				user.setTimezone(userForUpdate.getTimezone());
				user.setEnabled(userForUpdate.getEnabled());

				user.setUpdatedBy(userForUpdate.getUpdatedBy());

				return userRepository.save(user) != null;

			}
		}

		return false;
	}

	@Override
	public Boolean updatePassword(UserForUpdatePassword userForUpdatePassword) {

		if (userForUpdatePassword != null) {

			String newPassword = passwordEncoder.encode(userForUpdatePassword.getPassword());

			User user = userRepository.findOne(userForUpdatePassword.getId());
			user.setPassword(newPassword);
			user.setUpdatedBy(userForUpdatePassword.getUpdatedBy());

			return userRepository.save(user) != null;

		}

		return false;
	}

	@Override
	public Pair<List<User>, Integer> getDTOListWithPaginationBySearch(String keyword, Integer pageNo,
			Integer pageSize) {
		if (pageNo > 0) {
			Page<User> result;
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			if (keyword == null)
				keyword = "";
			result = userRepository.findBySearchWithPage(keyword, pageRequest);
			return Pair.of(result.getContent(), (int) result.getTotalElements());

		}
		return Pair.of(new ArrayList<>(), 0);
	}

	@Override
	@Transactional
	public Boolean createAdmin() {
		Role role = roleService.getRoleByCode(Constants.Roles.SA);
		if (role == null) {
			role = new Role();
			role.setCode("SA");
			role.setName("System Admin");
			try {
				role = roleService.create(role);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		User user = new User();
		user.setCreatedBy("system");
		user.setEmail("nnhai1991@gmail.com");
		user.setEnabled(true);
		user.setFailedLoginAttempt(0);
		user.setFirstName("System");
		user.setLastName("Admin");
		user.setPassword(passwordEncoder.encode("1"));
		user.setRole(role);
		user.setTimezone(Constants.TimeZone.SINGAPORE);
		userRepository.save(user);
		return true;
	}

}

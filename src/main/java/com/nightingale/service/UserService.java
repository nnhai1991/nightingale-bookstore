package com.nightingale.service;


import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.nightingale.entity.User;
import com.nightingale.model.dto.UserForUpdate;
import com.nightingale.model.dto.UserForUpdatePassword;

public interface UserService{

	User readByUsername(String username);
	
	Boolean loginFailed(String username);
	Boolean loginSucceeded(String username);
	
	Boolean incrementFailedLoginAttempt(User user);
	Boolean resetFailedLoginAttempt(User user);
	
	Boolean activateUser(String username);
	Boolean deactivateUser(String username);

	Pair<List<User>, Integer> getDTOListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize) ;

	Boolean update(UserForUpdate userForUpdate);
	Boolean updatePassword(UserForUpdatePassword userForUpdatePassword) ;

	Boolean create(UserForUpdate user);

	String createPasswordToken(Integer userId);

	User readFromToken(String token);
	
	Boolean createAdmin();

	List<User> getListAll();

	User create(User user);

	User read(Integer userId);

	void delete(Integer id);
	
}

package com.nightingale.app.service;


import com.nightingale.app.entity.User;
import com.nightingale.app.model.dto.UserForUpdate;
import com.nightingale.app.model.dto.UserDTO;
import com.nightingale.app.model.dto.UserForUpdatePassword;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface UserService extends BaseService<User>{

	User readByUsername(String username);
	
	Boolean loginFailed(String username);
	Boolean loginSucceeded(String username);
	
	Boolean incrementFailedLoginAttempt(User user);
	Boolean resetFailedLoginAttempt(User user);
	
	Boolean activateUser(String username);
	Boolean deactivateUser(String username);

	UserDTO readDTO(Integer userId);

	Pair<List<UserDTO>, Integer> getDTOListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize) ;

	Boolean update(UserForUpdate userForUpdate);
	Boolean updatePassword(UserForUpdatePassword userForUpdatePassword) ;

	Boolean create(UserForUpdate user);

	String createPasswordToken(Integer userId);

	User readFromToken(String token);
	
	Boolean createAdmin();
	
}

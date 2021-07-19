package com.insurancepolicy.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.insurancepolicy.model.User;
/**
 * @author priypawa
 * This interface contains abstract methods of {@link User}
 *
 */
public interface IUserService {

	List<User> getAllUsers(String token);

	User addUser(User user);

	User getUser(int userId,String token);

	User updateUser(User user,String token);

	List<User> deleteUser(int userId,String token);
	
	String loginUser(User loginDetails,HttpServletResponse response);


	User getUserByEmail(String email,String token);


}

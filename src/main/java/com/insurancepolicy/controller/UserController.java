package com.insurancepolicy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurancepolicy.model.User;
import com.insurancepolicy.service.IUserService;
/**
 * @author priypawa
 *
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	private static final String TOKEN = "token";

	@Autowired
	IUserService userService;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	/**
	 * This method returns list of available users.
	 * 
	 * @return {@link List} of {@link User}
	 */
	@GetMapping("/allUsers")
	public List<User> getAllUsers(HttpServletRequest request){
		String token = request.getHeader(TOKEN);
		logger.info("All Users returned from User Controller");
		return userService.getAllUsers(token);
	}
	/**
	 * This method accepts and saves user details and return an object of
	 * {@link User} containing all arguments which has been saved.
	 * 
	 * @param user : {@link User} Object.
	 * @return {@link User}
	 */
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		logger.info("Added user from User Controller");
		return userService.addUser(user);
	}
	/**
	 * This method accepts email Id and password of user.
	 * Check details of user.
	 * Return status of login.
	 * @param email: {@link String}
	 * @param password : {@link String}
	 * @return status : {@link Boolean}
	 */

	@PostMapping("/login")	
	public String loginUser(@RequestBody User loginDetails,HttpServletRequest request,HttpServletResponse response) {
		logger.info("Login Returned from User Controller");
		return userService.loginUser(loginDetails,response);
	}
	/**
	 * This method accepts email id of user and returns details of {@link User}
	 * based on email id
	 * @param email : {@link String}
	 * @return : {@link User}
	 */
	@GetMapping("/getUserByEmail/{email}")
	public User getUserByEmail(@PathVariable("email") String email,HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Returned User by email from User Controller");
		return userService.getUserByEmail(email,token);
	}
	/**
	 * This method accepts user Id and returns user details based on Id.
	 * 
	 * @param userId : {@link Integer}
	 * @return : {@link User}
	 */
	@GetMapping("/getUser/{id}")
	public User getUser(@PathVariable("id") int userId,HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Returned  user from User Controller");
		return userService.getUser(userId,token);
	}

	/**
	 * This method accepts and updates user details. Return updated user details
	 * 
	 * @param user : {@link User}
	 * @return : {@link User}
	 */
	@PutMapping("/update")
	public User updateUser(@RequestBody User user,HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Updated user from User Controller");
		return userService.updateUser(user,token);
	}
	
	/**
	 * This method accepts user Id and deletes particular record based on user Id
	 * @param userId : {@link Integer}
	 * @return {@link List} of {@link User}
	 */
	@DeleteMapping("/delete/{id}")
	public List<User> deleteUser(@PathVariable("id") int userId,HttpServletRequest request){
		String token = request.getHeader(TOKEN);
		logger.info("Deleted user from User Controller");
		return userService.deleteUser(userId,token);
	}

}

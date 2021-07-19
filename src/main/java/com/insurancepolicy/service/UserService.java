package com.insurancepolicy.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancepolicy.util.JwtUtil;
import com.insurancepolicy.exception.NotFoundException;
import com.insurancepolicy.model.User;
import com.insurancepolicy.repository.UserRepository;
import com.insurancepolicy.repository.UserSave;
import com.insurancepolicy.util.Encryption;

/**
 * @author priypawa This interface contains abstract methods of {@link User}
 *
 */
@Service
public class UserService implements IUserService {

	private static final String USERSTATUS = "USER";

	private static final String INACTIVE = "INACTIVE";

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSave userSave;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * This method returns list of available users.
	 * 
	 * @throws NotFoundException
	 * @return {@link List} of {@link User}
	 */
	@Override
	public List<User> getAllUsers(String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		List<User> allUsers = userRepository.findAll();
		if (allUsers.isEmpty()) {
			logger.warn("User list is empty");
			throw new NotFoundException("User list is empty");
		}
		logger.info("All Users returned from User Service");
		return allUsers;

	}

	/**
	 * This method accepts and saves user details and return an object of
	 * {@link User} containing all arguments which has been saved.
	 * 
	 * @throws NotFoundException
	 * @param user : {@link User} Object.
	 * @return {@link User}
	 */
	@Override
	public User addUser(User user) throws NotFoundException {
		if ((userRepository.findByEmail(user.getEmail())) != null) {
			throw new NotFoundException("Account already exist");
		}
		user.setUserId(userSave.saveUserId());
		user.setStatus(INACTIVE);
		user.setRole(USERSTATUS);
		user.setPassword(Encryption.encryptedPassword(user.getPassword()));
		logger.info("Added user from User Service");
		return userRepository.save(user);
	}

	/**
	 * This method accepts user Id and returns user details based on Id.
	 * 
	 * @param userId : {@link Integer}
	 * @return : {@link User}
	 */
	@Override
	public User getUser(int userId, String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new NotFoundException("User not found");
		}
		logger.info("Returned  user from User Service");
		return user;
	}

	/**
	 * This method accepts and updates user details. Return updated user details
	 * 
	 * @throws NotFoundException
	 * @param user : {@link User}
	 * @return : {@link User}
	 */
	@Override
	public User updateUser(User user, String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		User userDetails = userRepository.findById(user.getUserId()).orElse(null);
		if (userDetails == null) {
			throw new NotFoundException("User details not found");
		}
		userRepository.save(user);
		logger.info("Updated user from User Service");
		return user;
	}

	/**
	 * This method accepts user Id and deletes particular record based on user Id
	 * 
	 * @throws NotFoundException
	 * @param userId : {@link Integer}
	 * @return {@link List} of {@link User}
	 */
	@Override
	public List<User> deleteUser(int userId, String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		User userDetails = userRepository.findById(userId).orElse(null);
		if (userDetails == null) {
			throw new NotFoundException("User Details not found");
		}
		userRepository.delete(userDetails);
		logger.info("Deleted user from User Service");
		return userRepository.findAll();
	}


	/**
	 * This method accepts email id of user and returns details of {@link User}
	 * based on email id
	 * 
	 * @param email : {@link String}
	 * @return : {@link User}
	 */
	@Override
	public User getUserByEmail(String email, String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new NotFoundException("User not found");
		}
		logger.info("Returned User by email from User Service");
		return user;
	}

	/**
	 * This method accepts email Id and password of user. Check details of user.
	 * Return status of login.
	 * 
	 * @throws NotFoundException
	 * @param email:   {@link String}
	 * @param password : {@link String}
	 * @return status : {@link Boolean}
	 */

	@Override
	public String loginUser(User loginDetails, HttpServletResponse response) throws NotFoundException {
		User user = userRepository.findByEmailAndPassword(loginDetails.getEmail(), loginDetails.getPassword());
		if (user != null) {
			String token = JwtUtil.jwtTokenGenerator(Encryption.encryptedPassword(loginDetails.getPassword()),
					user.getUserId());
			if (token != null) {
				response.addHeader("token", token);
				response.addHeader("Access-control-Allow-Headers", "*");
				response.addHeader("Access-control-Expose-Headers", "*");
				logger.info("Login Returned from User Service");
				return token;

			} else {
				logger.info("Login Returned from User Service");
				return null;
			}
		} else
			throw new NotFoundException("Invalid User");

	}

}

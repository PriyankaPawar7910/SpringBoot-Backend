package com.insurancepolicy.repository;

import java.util.List;

import org.hibernate.mapping.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.insurancepolicy.model.User;
/**
 * @author priypawa
 *
 */
@Repository
public class UserSave {
	private static final int COUNT = 1;
	@Autowired
	UserRepository userRepository;
	
	/**
	 * This method sets user Id which is {@link PrimaryKey} If {@link List} of
	 * {@link User} is empty, it returns 1. Otherwise it returns
	 * incrementing by 1.
	 * 
	 * @return {@link Integer} : user Id
	 */
	public int saveUserId() {
		int userId;
		List<User> userList = userRepository.findAll();
		if(userList.isEmpty()) {
			userId = COUNT;
		}
		else {
			User user = userList.get(userList.size()-1);
			userId =  user.getUserId() + COUNT;
		}
	
		return userId;
	}

}

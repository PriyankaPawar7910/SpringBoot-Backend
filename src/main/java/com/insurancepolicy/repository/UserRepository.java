package com.insurancepolicy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.insurancepolicy.model.User;
/**
 * @author priypawa This interface extends {@link JpaRepository} which provides
 *         JPA functionalities for the entity class {@link User} that
 *         is being managed.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmailAndPassword(String email,String password);
	public User findByEmail(String email);
	@Query("SELECT u FROM User u WHERE u.role!='SUPERADMIN'")
	public List<User> findAll();
}

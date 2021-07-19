package com.insurancepolicy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.insurancepolicy.model.Policy;
/**
 * @author priypawa This interface extends {@link JpaRepository} which provides
 *         JPA functionalities for the entity class {@link Policy} that
 *         is being managed.
 */
@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{
	public Policy findByPolicyName(String policyName);
	@Query("SELECT p FROM Policy p WHERE p.status!=true")
	public List<Policy> findAll();
	
}

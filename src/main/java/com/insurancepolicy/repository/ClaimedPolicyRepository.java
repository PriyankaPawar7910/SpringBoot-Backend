package com.insurancepolicy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurancepolicy.model.ClaimedPolicy;

/**
 * @author priypawa This interface extends {@link JpaRepository} which provides
 *         JPA functionalities for the entity class {@link ClaimedPolicy} that
 *         is being managed.
 */
@Repository
public interface ClaimedPolicyRepository extends JpaRepository<ClaimedPolicy, Integer> {
	public List<ClaimedPolicy> findAllByUserId(int userId);

	public ClaimedPolicy findByPolicyIdAndUserId(int policyId, int userId);

	public List<ClaimedPolicy> findAllByPolicyId(int policyId);
}

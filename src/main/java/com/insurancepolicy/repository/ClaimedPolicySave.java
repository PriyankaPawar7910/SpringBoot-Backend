package com.insurancepolicy.repository;

import java.util.List;

import org.hibernate.mapping.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insurancepolicy.model.ClaimedPolicy;

/**
 * @author priypawa
 *
 */
@Repository
public class ClaimedPolicySave {

	private static final int COUNT = 1;
	@Autowired
	ClaimedPolicyRepository claimRepository;

	/**
	 * This method sets claim plan Id which is {@link PrimaryKey} If {@link List} of
	 * {@link ClaimedPolicy} is empty, it returns 1. Otherwise it returns
	 * incrementing by 1.
	 * 
	 * @return {@link Integer} : claim plan Id
	 */
	public int savePlanId() {
		int planId;
		List<ClaimedPolicy> claimPolicyList = claimRepository.findAll();
		if (claimPolicyList.isEmpty()) {
			planId = COUNT;
		} else {
			ClaimedPolicy policy = claimPolicyList.get(claimPolicyList.size() - 1);
			planId = policy.getPlanId() + COUNT;
		}
		return planId;

	}
}

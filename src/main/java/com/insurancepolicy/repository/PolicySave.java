package com.insurancepolicy.repository;

import java.util.List;

import org.hibernate.mapping.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.insurancepolicy.model.Policy;
/**
 * @author priypawa
 *
 */
@Repository
public class PolicySave {
	
	private static final int COUNT = 1;
	@Autowired
	PolicyRepository policyRepository;
	

	/**
	 * This method sets policy Id which is {@link PrimaryKey} If {@link List} of
	 * {@link Policy} is empty, it returns 1. Otherwise it returns
	 * incrementing by 1.
	 * 
	 * @return {@link Integer} : policy Id
	 */
	public int savePolicyId() {
		int policyId;
		List<Policy> policyList = policyRepository.findAll();
		if(policyList.isEmpty()) {
			policyId = COUNT;
		}
		else {
			Policy user = policyList.get(policyList.size()-1);
		   policyId = user.getPolicyId() + COUNT;
		}
		return policyId;
	
	}
}

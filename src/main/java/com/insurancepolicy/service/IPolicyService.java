package com.insurancepolicy.service;

import java.util.List;

import com.insurancepolicy.model.Policy;
/**
 * @author priypawa
 * This interface contains abstract methods of {@link Policy}
 *
 */
public interface IPolicyService {

	List<Policy> getAllPolicies(String token);

	Policy addPolicy(Policy policy,String token);

	Policy getPolicy(int policyId,String token);

	Policy updatePolicy(Policy policy,String token);

	Policy deletePolicy(Policy policy,String token);

}

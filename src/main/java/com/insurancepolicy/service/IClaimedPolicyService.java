package com.insurancepolicy.service;

import java.util.List;

import com.insurancepolicy.model.ClaimedPolicy;
import com.insurancepolicy.model.ResponseTemplate;
import com.insurancepolicy.model.User;

/**
 * @author priypawa
 * This interface contains abstract methods of {@link ClaimedPolicyService}
 *
 */
public interface IClaimedPolicyService {

	List<ResponseTemplate> getAllClaimPolicies(String token);

	ClaimedPolicy addClaimPolicy(ClaimedPolicy claimPolicy,String token);

	ClaimedPolicy getClaimPolicy(int id,String token);

	ClaimedPolicy updateClaimPolicy(ClaimedPolicy claimPolicy,String token);

	List<ClaimedPolicy> deleteClaimPolicy(int planId,String token);

	List<ResponseTemplate>  getClaimPolicyByUser(int userId,String token);

	List<User> getPolicyHoldersByPolicy(int policyId,String token);

}

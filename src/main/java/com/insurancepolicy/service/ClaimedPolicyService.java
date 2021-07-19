package com.insurancepolicy.service;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.insurancepolicy.exception.NotFoundException;
import com.insurancepolicy.model.ClaimedPolicy;
import com.insurancepolicy.model.Policy;
import com.insurancepolicy.model.ResponseTemplate;
import com.insurancepolicy.model.User;
import com.insurancepolicy.repository.ClaimedPolicyRepository;
import com.insurancepolicy.repository.ClaimedPolicySave;
import com.insurancepolicy.util.JwtUtil;

/**
 * @author priypawa This class contains all the implemented methods and its
 *         business logic of its interface
 */
@Service
public class ClaimedPolicyService implements IClaimedPolicyService {

	private static final String HTTP_LOCALHOST_9494_POLICY_SERVICE_POLICY_GET_POLICY = "http://localhost:9494/Insurance-backend/policy/getPolicy/";

	private static final String HTTP_LOCALHOST_9494_USER_SERVICE_USER_GET_USER = "http://localhost:9494/Insurance-backend/user/getUser/";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	ClaimedPolicyRepository claimPolicyRepository;

	@Autowired
	ClaimedPolicySave saveClaim;

	Logger logger = LoggerFactory.getLogger(ClaimedPolicyService.class);

	/**
	 * This method returns policy holders list with respective policies Use of
	 * {@link RestTemplate}
	 * 
	 * @throws NotFoundException
	 * @return {@link List} of {@link ResponseTemplate}
	 */
	@Override
	public List<ResponseTemplate> getAllClaimPolicies(String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		List<ResponseTemplate> responseTemplates = new ArrayList<>();
		ResponseTemplate responseTemplate = null;

		List<ClaimedPolicy> policyList = claimPolicyRepository.findAll();
		if (policyList.isEmpty()) {
			throw new NotFoundException("Claimed Policy list is empty");
		}

		for(ClaimedPolicy claimPolicyDetails : policyList)
		{
			User user = restTemplate.getForObject(
					HTTP_LOCALHOST_9494_USER_SERVICE_USER_GET_USER + claimPolicyDetails.getUserId(), User.class);
			Policy policy = restTemplate.getForObject(
					HTTP_LOCALHOST_9494_POLICY_SERVICE_POLICY_GET_POLICY + claimPolicyDetails.getPolicyId(),
					Policy.class);

			responseTemplate = new ResponseTemplate(user, policy, claimPolicyDetails);
			responseTemplates.add(responseTemplate);
		}
		logger.info("All claim policies returned from ClaimPolicy Service");
		return responseTemplates;

	}

	/**
	 * This method accepts and saves claimed policy details and return an object of
	 * {@link ClaimedPolicy} containing all arguments which has been saved.
	 * 
	 * @throws NotFoundException
	 * @param claimPolicy : {@link ClaimedPolicy} Object.
	 * @return {@link ClaimedPolicy}
	 */
	@Override
	public ClaimedPolicy addClaimPolicy(ClaimedPolicy claimPolicy,String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		ClaimedPolicy claimedPolicies = claimPolicyRepository.findByPolicyIdAndUserId(claimPolicy.getPolicyId(),
				claimPolicy.getUserId());
		if (claimedPolicies != null) {
			throw new NotFoundException("Only taken ");

		}
		claimPolicy.setPlanId(saveClaim.savePlanId());
		logger.info("Added claim policy from ClaimPolicy Service");
		return claimPolicyRepository.save(claimPolicy);
	}

	/**
	 * This method accepts claim Id and returns claimed policy details based on Id.
	 * 
	 * @param id : claimId
	 * @return : {@link ClaimedPolicy}
	 */
	@Override
	public ClaimedPolicy getClaimPolicy(int claimId,String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		ClaimedPolicy claimPolicy = claimPolicyRepository.findById(claimId).orElse(null);
		if (claimPolicy == null)
			throw new NotFoundException("Claimed Policy details not found");
		logger.info("Returned claim policy from ClaimPolicy Service");
		return claimPolicy;
	}

	/**
	 * This method accepts and updates claim policy details. Return updated claimed
	 * policy details
	 * 
	 * @throws NotFoundException
	 * @param claimPolicy : {@link ClaimedPolicy}
	 * @return : {@link ClaimedPolicy}
	 */
	@Override
	public ClaimedPolicy updateClaimPolicy(ClaimedPolicy claimPolicy,String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		ClaimedPolicy policyDetails = claimPolicyRepository.findById(claimPolicy.getPlanId()).orElse(null);
		if (policyDetails == null) {
			throw new NotFoundException("Claimed Policy record not found");
		}
		claimPolicyRepository.save(claimPolicy);
		logger.info("Updated claim policy from ClaimPolicy Service");
		return claimPolicy;
	}

	/**
	 * This method accepts claim plan Id and deletes particular claim plan based on
	 * Id
	 * 
	 * @throws NotFoundException
	 * @param planId
	 * @return {@link List} of {@link ClaimedPolicy}
	 */
	@Override
	public List<ClaimedPolicy> deleteClaimPolicy(int planId,String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		ClaimedPolicy policyDetails = claimPolicyRepository.findById(planId).orElse(null);
		if (policyDetails == null) {
			logger.warn("Claimed Policy not found");
			throw new NotFoundException("Claimed Policy record not found");
		}
		claimPolicyRepository.delete(policyDetails);
		logger.info("Deleted claim policy from ClaimPolicy Service");
		return claimPolicyRepository.findAll();
	}

	/**
	 * This method accepts user Id and returns list of policies holed by particular
	 * user. Use of {@link RestTemplate}
	 * 
	 * @throws NotFoundException
	 * @param userId
	 * @return {@link List} of {@link ResponseTemplate}
	 */
	@Override
	public List<ResponseTemplate> getClaimPolicyByUser(int userId,String token) {
		//JwtUtil.tokenVerification(token);
		List<ResponseTemplate> responseTemplates = new ArrayList<>();
		ResponseTemplate responseTemplate = null;

		List<ClaimedPolicy> policyList = claimPolicyRepository.findAllByUserId(userId);
		if (policyList.isEmpty()) {
			throw new NotFoundException("You don't have policies");
		}

		for(ClaimedPolicy policyDetails : policyList)		{
			User user = restTemplate.getForObject(HTTP_LOCALHOST_9494_USER_SERVICE_USER_GET_USER + userId,
					User.class);
			Policy policy = restTemplate.getForObject(
					HTTP_LOCALHOST_9494_POLICY_SERVICE_POLICY_GET_POLICY + policyDetails.getPolicyId(),
					Policy.class);

			responseTemplate = new ResponseTemplate(user, policy, policyDetails);

			responseTemplates.add(responseTemplate);
		}
		logger.info("Returned claim policy by user from ClaimPolicy Service");
		return responseTemplates;
	}

	/**
	 * This method accepts policy Id and return {@link List} of {@link User} with
	 * their respective {@link Policy} Use of {@link RestTemplate}
	 * 
	 * @throws NotFoundException
	 * @param id : policyId
	 * @return {@link List} of {@link User}
	 */

	@Override
	public List<User> getPolicyHoldersByPolicy(int policyId,String token) {
		//JwtUtil.tokenVerification(token);
		List<ClaimedPolicy> policyList = claimPolicyRepository.findAllByPolicyId(policyId);
		if (policyList.isEmpty()) {
			throw new NotFoundException("You don't have policies");
		}
		List<User> allUsers = new ArrayList<>();
		for(ClaimedPolicy claimUserDetails : policyList)		{
			User user = restTemplate.getForObject(
					HTTP_LOCALHOST_9494_USER_SERVICE_USER_GET_USER + claimUserDetails.getUserId(), User.class);
			allUsers.add(user);
		}
		logger.info("Returned policy holders by policy from ClaimPolicy Service");
		return allUsers;

	}

}

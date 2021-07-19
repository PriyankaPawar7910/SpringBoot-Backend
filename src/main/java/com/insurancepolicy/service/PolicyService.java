package com.insurancepolicy.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurancepolicy.exception.NotFoundException;
import com.insurancepolicy.model.Policy;
import com.insurancepolicy.repository.PolicyRepository;
import com.insurancepolicy.repository.PolicySave;
import com.insurancepolicy.util.JwtUtil;
/**
 * @author priypawa This class contains all the implemented methods and its
 *         business logic of its interface
 */
@Service
public class PolicyService implements IPolicyService {

	@Autowired
	PolicyRepository policyRepository;

	@Autowired
	PolicySave policySave;

	Logger logger = LoggerFactory.getLogger(PolicyService.class);


	/**
	 * This method returns list of available policies.
	 * 
	 * @throws NotFoundException
	 * @return {@link List} of {@link Policy}
	 */
	@Override
	public List<Policy> getAllPolicies(String token) throws NotFoundException{
		//JwtUtil.tokenVerification(token);
		List<Policy> policies = policyRepository.findAll();
		if(policies.isEmpty()) {
			throw new NotFoundException("Policy list is empty");
		}
		logger.info("All  policies returned from Policy Service");
		return policies;

	}

	/**
	 * This method accepts and saves policy details and return an object of
	 * {@link Policy} containing all arguments which has been saved.
	 * 
	 * @throws NotFoundException
	 * @param policy : {@link Policy} Object.
	 * @return {@link Policy}
	 */
	@Override
	public Policy addPolicy(Policy policy,String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		if ((policyRepository.findByPolicyName(policy.getPolicyName()) != null)) {
			throw new NotFoundException("Policy already existed!");
		}
		policy.setPolicyId(policySave.savePolicyId());
		policy.setStatus(false);
		logger.info("Added policy from Policy Service");
		return policyRepository.save(policy);
	}

	/**
	 * This method accepts policy Id and returns policy details based on Id.
	 * 
	 * @param policyId : {@link Integer}
	 * @return : {@link Policy}
	 */
	@Override
	public Policy getPolicy(int policyId,String token) {
		//JwtUtil.tokenVerification(token);
		Policy policy =  policyRepository.findById(policyId).orElse(null);
		if(policy==null)
			throw new NotFoundException("Policy Details not found");
		logger.info("Returned  policy from Policy Service");
		return policy;
	}

	/**
	 * This method accepts and updates policy details. Return updated policy details
	 * 
	 * @throws NotFoundException
	 * @param policy : {@link Policy}
	 * @return : {@link Policy}
	 */
	@Override
	public Policy updatePolicy(Policy policy,String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		Policy policyDetails = policyRepository.findById(policy.getPolicyId()).orElse(null);
		if (policyDetails == null) {
			throw new NotFoundException("Policy Details not found");
		}
		policyRepository.save(policy);
		logger.info("Updated policy from Policy Service");
		return policy;
	}

	/**
	 * This method accepts policy details.
	 * Soft delete of particular policy based on Id
	 * 
	 * @throws NotFoundException
	 * @param policy : {@link Policy}
	 * @return {@link Policy}
	 */
	@Override
	public Policy deletePolicy(Policy policy,String token) throws NotFoundException {
		//JwtUtil.tokenVerification(token);
		Policy policyDetails = policyRepository.findById(policy.getPolicyId()).orElse(null);
		if (policyDetails == null) {
			throw new NotFoundException("Policy details not found");
		}
		policyDetails.setStatus(true);
		policyRepository.save(policyDetails);
		logger.info("Deleted policy from Policy Service");
		return policyDetails;
	}

}

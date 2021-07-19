package com.insurancepolicy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurancepolicy.model.ClaimedPolicy;
import com.insurancepolicy.model.Policy;
import com.insurancepolicy.model.ResponseTemplate;
import com.insurancepolicy.model.User;
import com.insurancepolicy.service.IClaimedPolicyService;

/**
 * @author priypawa
 *
 */
@RestController
@RequestMapping("/claimpolicy")
@CrossOrigin
public class ClaimedPolicyController {

	@Autowired
	IClaimedPolicyService claimedPolicyService;

	Logger logger = LoggerFactory.getLogger(ClaimedPolicyController.class);
	
	private static final String TOKEN = "token";


	/**
	 * This method returns policy holders list with respective policies
	 * 
	 * @return {@link List} of {@link ResponseTemplate}
	 */
	@GetMapping("/getAll")
	public List<ResponseTemplate> getAllClaimPolicies(HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("All claim polies returned from ClaimPolicy Controller");
		return claimedPolicyService.getAllClaimPolicies(token);
	}

	/**
	 * This method accepts policy Id and return {@link List} of {@link User} with
	 * their respective {@link Policy}
	 * 
	 * @param id : policyId
	 * @return {@link List} of {@link User}
	 */
	@GetMapping("/getUsersByPolicy/{id}")
	public List<User> getPolicyHolderByPolicy(@PathVariable("id") int policyId, HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Return policy holders list based on policy from ClaimPolicy Controller");
		return claimedPolicyService.getPolicyHoldersByPolicy(policyId,token);
	}

	/**
	 * This method accepts and saves claimed policy details and return an object of
	 * {@link ClaimedPolicy} containing all arguments which has been saved.
	 * 
	 * @param claimPolicy : {@link ClaimedPolicy} Object.
	 * @return {@link ClaimedPolicy}
	 */
	@PostMapping("/add")
	public ClaimedPolicy addClaimPolicy(@RequestBody ClaimedPolicy claimPolicy, HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Added claim policy from ClaimPolicy Controller");
		return claimedPolicyService.addClaimPolicy(claimPolicy,token);
	}

	/**
	 * This method accepts claim Id and returns claimed policy details based on Id.
	 * 
	 * @param id : claimId
	 * @return : {@link ClaimedPolicy}
	 */
	@GetMapping("/getClaimPolicy/{id}")
	public ClaimedPolicy getClaimPolicy(@PathVariable("id") int claimId, HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Returned claim policy from ClaimPolicy Controller");
		return claimedPolicyService.getClaimPolicy(claimId,token);
	}

	/**
	 * This method accepts user Id and returns policies holed by particular user.
	 * 
	 * @param userId
	 * @return {@link List} of {@link ResponseTemplate}
	 */
	@GetMapping("/getUserClaimPolicy/{id}")
	public List<ResponseTemplate> getClaimPolicyByUser(@PathVariable("id") int userId, HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Returned claim policy by user from ClaimPolicy Controller");
		return claimedPolicyService.getClaimPolicyByUser(userId,token);
	}

	/**
	 * This method accepts and updates claim policy details. Return updated claimed
	 * policy details
	 * 
	 * @param claimPolicy : {@link ClaimedPolicy}
	 * @return : {@link ClaimedPolicy}
	 */
	@PutMapping("/update")
	public ClaimedPolicy updateClaimPolicy(@RequestBody ClaimedPolicy claimPolicy, HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Updated claim policy from ClaimPolicy Controller");
		return claimedPolicyService.updateClaimPolicy(claimPolicy,token);
	}

	/**
	 * This method accepts claim plan Id and deletes particular claim plan based on
	 * Id
	 * 
	 * @param planId
	 * @return {@link List} of {@link ClaimedPolicy}
	 */
	@DeleteMapping("/delete/{id}")
	public List<ClaimedPolicy> deleteClaimPolicy(@PathVariable("id") int planId, HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Deleted claim policy from ClaimPolicy Controller");
		return claimedPolicyService.deleteClaimPolicy(planId,token);
	}

}

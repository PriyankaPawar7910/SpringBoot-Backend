package com.insurancepolicy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.insurancepolicy.model.Policy;
import com.insurancepolicy.service.IPolicyService;

/**
 * @author priypawa
 *
 */
@RestController
@RequestMapping("/policy")
@CrossOrigin
public class PolicyController {

	@Autowired
	IPolicyService policyService;

	Logger logger = LoggerFactory.getLogger(PolicyController.class);
	
	private static final String TOKEN = "token";


	/**
	 * This method returns list of available policies.
	 * 
	 * @return {@link List} of {@link Policy}
	 */
	@GetMapping("/allPolicies")
	public List<Policy> getAllPolicies(HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("All  policies returned from Policy Controller");
		return policyService.getAllPolicies(token);
	}

	/**
	 * This method accepts and saves policy details and return an object of
	 * {@link Policy} containing all arguments which has been saved.
	 * 
	 * @param policy : {@link Policy} Object.
	 * @return {@link Policy}
	 */
	@PostMapping("/add")
	public Policy addPolicy(@RequestBody Policy policy,HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Added policy from Policy Controller");
		return policyService.addPolicy(policy,token);
	}

	/**
	 * This method accepts policy Id and returns policy details based on Id.
	 * 
	 * @param policyId : {@link Integer}
	 * @return : {@link Policy}
	 */
	@GetMapping("/getPolicy/{id}")
	public Policy getPolicy(@PathVariable("id") int policyId,HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Returned  policy from Policy Controller");
		return policyService.getPolicy(policyId,token);
	}

	/**
	 * This method accepts and updates policy details. Return updated policy details
	 * 
	 * @param policy : {@link Policy}
	 * @return : {@link Policy}
	 */
	@PutMapping("/update")
	public Policy updatePolicy(@RequestBody Policy policy,HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Updated policy from Policy Controller");
		return policyService.updatePolicy(policy,token);
	}

	/**
	 * This method accepts policy details.
	 * Soft delete of particular policy based on Id
	 * 
	 * @param policy : {@link Policy}
	 * @return {@link Policy}
	 */

	@PutMapping("/delete")
	public Policy deletePolicy(@RequestBody Policy policy,HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		logger.info("Deleted policy from Policy Controller");
		return policyService.deletePolicy(policy,token);
	}

}

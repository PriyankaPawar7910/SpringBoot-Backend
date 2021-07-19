package com.insurancepolicy.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
/**
 * @author priypawa This class includes declaration of parameters of 
 *         policy class, default constructor, parameterized constructors, getter
 *         and setter methods of parameters and toString method to display.
 */
@Entity
@Table(name = "policies")
public class Policy {
	@Id
	@Column(name = "policy_id")
	private int policyId;
	@Column(name = "policy_name")
	private String policyName;
	@Column(name = "policy_desc")
	private String policyDescription;
	@Column
	private int duration;
	@Column(name = "premium_amt")
	private float premiumAmount;
	@Column(name = "policy_type")
	private String policyType;
	@Column(name = "status")
	private boolean status;

	public Policy() {
	}

	public Policy(int policyId, String policyName, String policyDescription, int duration, float premiumAmount,
			String policyType, boolean status) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyDescription = policyDescription;
		this.duration = duration;
		this.premiumAmount = premiumAmount;
		this.policyType = policyType;
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(float premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getPolicyDescription() {
		return policyDescription;
	}

	public void setPolicyDescription(String policyDescription) {
		this.policyDescription = policyDescription;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyDescription="
				+ policyDescription + ", duration=" + duration + ", premiumAmount=" + premiumAmount + ", policyType="
				+ policyType + ", status=" + status + "]";
	}

}

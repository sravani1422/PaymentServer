package com.pp.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	String cust_id; 
	
	String cust_name;
	double clear_balance;
	boolean overdraft;
	public String getCustomerId() {
		return cust_id;
	}
	public void setCustomerId(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getName() {
		return cust_name;
	}
	public void setName(String cust_name) {
		this.cust_name = cust_name;
	}
	public boolean isOverDraft() {
		return overdraft;
	}
	public void setOverDraft(boolean overdraft) {
		this.overdraft = overdraft;
	}
	public double getClearBalance() {
		return clear_balance;
	}
	public void setClearBalance(double clear_balance) {
		this.clear_balance = clear_balance;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", overdraft=" + overdraft + ", clear_balance="
				+ clear_balance + "]";
	}
	
	
	
	
	
	

}

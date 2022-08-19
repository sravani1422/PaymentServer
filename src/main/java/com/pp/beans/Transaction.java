package com.pp.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.pp.models.TransferType;

@Entity
public class Transaction {
	
	@Id
	@Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	long transaction_id;
	
	String receiver_name;
	String receiver_id;
	double amount;
	
	TransferType transferType;
	Date timestamp;
	
	
	@ManyToOne
	Customer customer;
	@ManyToOne
	Message messageCode;
	@ManyToOne
	BankBic receiverBic;
	
	public Long getTransactionId() {
		return transaction_id;
	}
	public void setTransactionId(Long transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public String getReceiverName() {
		return receiver_name;
	}
	public void setReceiverName(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiverId() {
		return receiver_id;
	}
	public void setReceiverId(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public TransferType getTransferType() {
		return transferType;
	}
	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + " receiver_name="
				+ receiver_name + ", receiver_id=" + receiver_id + ", amount=" + amount + ", transferType="
				+ transferType + ", timestamp=" + timestamp + ", customer=" + customer + ", messageCode=" + messageCode
				+ ", receiverBic=" + receiverBic + "]";
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

	public void setMessageCode(Message messageCode) {
		this.messageCode = messageCode;
	}

	public BankBic getReceiverBic() {
		return receiverBic;
	}

	public void setReceiverBic(BankBic receiverBic) {
		this.receiverBic = receiverBic;
	}

	


	
	

}

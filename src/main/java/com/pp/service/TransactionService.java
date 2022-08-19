package com.pp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.pp.beans.Customer;
import com.pp.beans.Transaction;
import com.pp.dto.TransactionRequestDto;
import com.pp.exceptions.TransactionNotValidException;
import com.pp.models.TransferType;
import com.pp.repo.CustomerRepo;
import com.pp.repo.TransactionRepo;

@Service
public class TransactionService implements ITransactionService {
	
	@Autowired
	TransactionRepo transactionRepo;

	@Autowired
	BankBicService bankBicService;

	@Autowired
	CustomerService custService;

	@Autowired
	CustomerRepo custRepo;

	@Autowired
	MessageService msgService;

	
	@Override
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepo.save(transaction);
	}
	
	@Override
	public List<Transaction> findAll(){
		return (List<Transaction>) transactionRepo.findAll();
	}
	
	@Override
	public Transaction updateTransaction(Transaction transaction) {
		Optional<Transaction> optTransaction = transactionRepo.findById(transaction.getTransactionId());
		if(optTransaction.isPresent()) {
			return transactionRepo.save(transaction);
		}
		else {
			return null;
		}
	}
	
	@Override
	public Transaction findById(Long id) {
		return transactionRepo.findById(id).get();
	}
	
	public Transaction saveTransaction(TransactionRequestDto trans) {
		String transferType = trans.getTransferType();
		String receiverName = trans.getReceiverName().strip().toUpperCase();
		Customer cust = custService.findById(trans.getSenderAcctNumber());
		String custName = cust.getName();

		System.out.println(trans);
		
		// Validating transferType field
		if(!transferType.equals("BANK") && !transferType.equals("CUSTOMER")) {
			throw new TransactionNotValidException("The transfer type is not recognizable");
		}
		if ((transferType.equals("BANK")
				&& (!receiverName.startsWith("HDFC BANK") || !custName.startsWith("HDFC BANK")))
				|| (transferType.equals("CUSTOMER")
						&& (receiverName.startsWith("HDFC BANK") || custName.startsWith("HDFC BANK")))) {
			throw new TransactionNotValidException("Transfer type is entered incorrectly");
		}

		if (trans.getAmount().equals(0)) {
			throw new TransactionNotValidException("Amount should be greater than zero");
		}

		Double amount = trans.getAmount();
		Double transFee = amount * 0.0025;

		cust = custService.findById(trans.getSenderAcctNumber());
		Double custAcctBalance = cust.getClearBalance();
		
		//if ((amount + transFee) > custAcctBalance && cust.getOverdraft().equals("NO")) {
		if ((amount + transFee) > custAcctBalance && cust.isOverDraft()) {
			throw new TransactionNotValidException(
					"SORRY!! The sender doesn't have enough amount to complete the transaction :(.");
		}

		if (isInSdnList(trans.getReceiverName())) {
			throw new TransactionNotValidException(
					"ALERT!! The receiver is in SDN list, the transaction cannot be processed");
		}

		Transaction transaction = new Transaction();
		transaction.setMessageCode(msgService.findById(trans.getMessageCode().strip()));
		transaction.setReceiverId(trans.getReceiverAcctNumber().strip());
		transaction.setReceiverBic(bankBicService.getBankDetailsByBic(trans.getReceiverBic().strip()));
		transaction.setReceiverName(trans.getReceiverName().strip());
		transaction.setTimestamp(new Date());
		transaction.setAmount(amount);
		transaction.setTransferType(trans.getTransferType().equals("BANK") ? TransferType.BANK : TransferType.CUSTOMER);
		cust.setClearBalance(custAcctBalance - (amount + transFee));
		custRepo.save(cust);

		transaction.setCustomer(custService.findById(cust.getCustomerId()));
		transaction = transactionRepo.save(transaction);

		return transaction;

	}
	
	public boolean isInSdnList(String name) {
		//Transaction transaction;
		try {
			File f = ResourceUtils.getFile("classpath:datalist.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			if(str.contains(name)) {
				return true;
			}
			if(name.contains(" ") && str.contains(name.split(" ")[1] + name.split(" ")[0])) {
				return true;
			}
			if(name.contains(" ") && str.contains(name.split(" ")[1] + " " + name.split(" ")[0])) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	

}

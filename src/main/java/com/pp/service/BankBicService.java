package com.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pp.beans.BankBic;
import com.pp.exceptions.ResourceNotFoundException;
import com.pp.repo.BankBicRepo;

@Service
public class BankBicService implements IBankBicService {
	
	@Autowired
	BankBicRepo bankBicRepo;
	
	@Override
	public BankBic findById(String id) {
		return bankBicRepo.findById(id).get();
	}
	public BankBic getBankDetailsByBic(String bic) {
		return bankBicRepo.findById(bic)
				.orElseThrow(() -> new ResourceNotFoundException("The bank with given bic does not exists"));
	}
}

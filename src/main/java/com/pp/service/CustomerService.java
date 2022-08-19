package com.pp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.pp.beans.Customer;
import com.pp.beans.Transaction;
import com.pp.repo.CustomerRepo;

@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> optCustomer = customerRepo. findById(customer.getCustomerId());
		if(optCustomer.isPresent()) {
			return customerRepo.save(customer);
		}
		else {
			return null;
		}
	}
	
	@Override
	public List<Customer> findAll(){
		return customerRepo.findAll();
	}
	
	@Override
	public Customer findById(String id) {
		return customerRepo.findById(id).get();
	}
	
}

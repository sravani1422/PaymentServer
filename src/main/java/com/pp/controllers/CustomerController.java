package com.pp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pp.service.ICustomerService;
import com.pp.beans.Customer;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;
	
	@GetMapping(value="customer")
	public ResponseEntity<List<Customer>> getCustomer() {
		List<Customer> customers = customerService.findAll();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping(value="customer/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {
		Customer customer = customerService.findById(id);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PostMapping(value="customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer resEmp = customerService.addCustomer(customer);
		return new ResponseEntity<>(resEmp, HttpStatus.OK);
	}
	
	@PutMapping(value="customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer resEmp = customerService.updateCustomer(customer);
		return new ResponseEntity<Customer>(resEmp, HttpStatus.OK);
	}

}

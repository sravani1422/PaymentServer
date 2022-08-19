package com.pp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pp.beans.BankBic;
import com.pp.service.IBankBicService;

@RestController
@CrossOrigin(origins="*")
public class BankBicController {
	
	@Autowired
	IBankBicService bankBicService;
	
	@GetMapping(value="bankBic/{id}")
	public ResponseEntity<BankBic> getBankBic(@PathVariable("id") String id) {
		BankBic bankBic = bankBicService.findById(id);
		return new ResponseEntity<>(bankBic, HttpStatus.OK);
	}	
}

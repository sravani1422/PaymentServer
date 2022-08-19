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

import com.pp.beans.Transaction;
import com.pp.dto.TransactionRequestDto;
import com.pp.service.ITransactionService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class TransactionController {
	
	@Autowired
	ITransactionService transactionService;
	
	@GetMapping(value="transaction")
	public ResponseEntity<List<Transaction>> getTransaction() {
		List<Transaction> transactions = transactionService.findAll();
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}
	
	@GetMapping(value="transaction/{id}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable("id") Long id) {
		Transaction transaction = transactionService.findById(id);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}
	
	@PostMapping(value="transaction")
	public ResponseEntity<Transaction> saveTransaction(@RequestBody TransactionRequestDto transaction) {
		Transaction resEmp = transactionService.saveTransaction(transaction);
		return new ResponseEntity<>(resEmp, HttpStatus.OK);
	}
	
	
	@PutMapping(value="transaction")
	public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) {
		Transaction resEmp = transactionService.updateTransaction(transaction);
		return new ResponseEntity<Transaction>(resEmp, HttpStatus.OK);
	}
	
}

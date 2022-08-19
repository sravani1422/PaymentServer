package com.pp.service;

import java.util.List;

import com.pp.beans.Transaction;
import com.pp.dto.TransactionRequestDto;

public interface ITransactionService {

	Transaction addTransaction(Transaction transaction);

	Transaction updateTransaction(Transaction transaction);

	List<Transaction> findAll();

	Transaction findById(Long id);

	Transaction saveTransaction(TransactionRequestDto transaction);

}
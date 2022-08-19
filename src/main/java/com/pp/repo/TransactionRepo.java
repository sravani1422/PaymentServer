package com.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.beans.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

}

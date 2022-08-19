package com.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.beans.BankBic;

public interface BankBicRepo extends JpaRepository<BankBic, String> {

}

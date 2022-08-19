package com.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.beans.Customer;

public interface CustomerRepo extends JpaRepository<Customer, String> {
	

}

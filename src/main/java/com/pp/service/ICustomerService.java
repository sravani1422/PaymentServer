package com.pp.service;

import java.util.List;

import com.pp.beans.Customer;

public interface ICustomerService {

	Customer addCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	List<Customer> findAll();

	Customer findById(String id);

}
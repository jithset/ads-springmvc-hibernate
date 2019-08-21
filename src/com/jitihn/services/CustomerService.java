package com.jitihn.services;

import java.util.List;

import com.jitihn.domains.Customer;

public interface CustomerService {

		public List<Customer> getCustomers();

		public void saveCustomer(Customer theCustomer);

		public Customer getCustomer(int theId);
		
	}

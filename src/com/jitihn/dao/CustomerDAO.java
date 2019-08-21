package com.jitihn.dao;

import java.util.List;

import com.jitihn.domains.Customer;

public interface CustomerDAO {
	List<Customer> getCustomer();
	
	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);
}

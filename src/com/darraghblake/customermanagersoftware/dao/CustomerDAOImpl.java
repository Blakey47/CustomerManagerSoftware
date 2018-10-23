package com.darraghblake.customermanagersoftware.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.darraghblake.customermanagersoftware.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// Get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Create a query - Sorted by lastName
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// Execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// Return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// Get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save the customer to the DB
		currentSession.save(theCustomer);
		
	}

	@Override
	public Customer getCustomers(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}

}

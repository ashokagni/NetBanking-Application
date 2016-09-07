package com.i2i.netbankingApplication.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.hibernateConnection.HibernateConnection;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.model.Customer;

public class CustomerDao {
	HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();

	public void insertUser(Customer user) {
		Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
    }
	
	public Customer retrieveCustomerById(String customerId) throws DataBaseException {
	    Customer customer = null ;
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        customer = (Customer)session.get(Customer.class, customerId); 
	        transaction.commit();
	    } catch (HibernateException e) {
	    	throw new DataBaseException("CHECK YOUR " + customerId + "PLEASE INSERT VALID CUSTOMER ID..");
	    } finally {
	        session.close(); 
	    } 
	    return customer; 
	}
	
	public List<Customer> retriveAllCustomer() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("FROM Customer").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
	
	public void addAddress(String customerId, Address address) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        Customer customer = (Customer)session.get(Customer.class, customerId);
	        session.save(address); 
		    customer.setAddress(address);
	        session.update(customer);
	        transaction.commit();   
		} catch (HibernateException e) {
			throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close(); 
	    }
	}
	 
	public List<Address> retriveAllAddresses() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("from Address").list();
	    } catch(HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
	
	public Address retrieveAddressById(int addressId) throws DataBaseException {
		Address address;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
		    address = (Address)session.get(Address.class, addressId);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DataBaseException("Oops Some Problem occured.. please try again later");
		} finally {
			session.close();
		}
		return address;
	}
}

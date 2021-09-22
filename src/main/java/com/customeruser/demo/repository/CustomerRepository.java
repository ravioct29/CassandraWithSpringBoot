package com.customeruser.demo.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.customeruser.demo.model.Customer;

@Repository
public interface CustomerRepository extends CassandraRepository<Customer, Long> {

	@AllowFiltering
    Optional<Customer> findByPhoneNumber(Long phoneNumber);

//@AllowFiltering
//Optional<Customer> findByBillingNumber(Long billingNumber);

}

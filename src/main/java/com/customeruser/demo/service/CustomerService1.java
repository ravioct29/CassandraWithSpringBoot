package com.customeruser.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customeruser.demo.model.Address;
import com.customeruser.demo.model.Customer;
import com.customeruser.demo.repository.CustomerRepository;

@Service
@ConfigurationProperties
public class CustomerService1 {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	Environment env;

	public Long createCustomer(Customer customer) {

		List<Address> addresses = new ArrayList<>();

		if (customer.getPhoneNumber().toString().length() == 10) {

			for (Address address : customer.getAddress()) {
				Address address1 = addAddress(address);
				if (address.getZip().toString().length() < 5)

					return (long) HttpStatus.BAD_REQUEST.value();
				else
					addresses.add(address1);
				customer.setAddress(addresses);
			}
			customer.setBillingAccountNumber((long) ThreadLocalRandom.current().nextInt(999999999));
			customerRepository.save(customer);
			return customer.getBillingAccountNumber();

		} else {
			return (long) HttpStatus.BAD_REQUEST.value();
		}
	}

	public Address addAddress(Address addr) {

		String state = env.getProperty(addr.getState());
		if (null != state)
			addr.setState(state);
		return addr;

	}

	public Object updateCustomer(Customer customer, Long billingNumber) {

		Optional<Customer> findByBillingNumber = customerRepository.findById(billingNumber);

		if (findByBillingNumber.isPresent()) {
			if (customer.getEmail() != null) {

				findByBillingNumber.get().setEmail(customer.getEmail());
			}

			if (customer.getAddress().size() > 0) {

				for (Address address : customer.getAddress()) {
					Address address2 = addAddress(address);

					if (address.getZip().toString().length() < 5)
						return (long) HttpStatus.BAD_REQUEST.value();
					else
						findByBillingNumber.get().getAddress().add(address2);
				}

			}

			customerRepository.save(findByBillingNumber.get());
			return HttpStatus.OK.value();

		} else
			return HttpStatus.BAD_REQUEST.value();

	}

	/*
	 * public Object updateCustomer1(Customer customer, Long billingNumber) {
	 * 
	 * Customer customer2 =
	 * customerRepository.findById(customer.getBillingAccountNumber()).get();
	 * 
	 * if(customer.getEmail() != null && !customer.getEmail().isEmpty()) {
	 * customer2.setEmail(customer.getEmail()); } if(customer.getAddress()!=null &&
	 * customer.getAddress().size()>0 && !customer.getAddress().isEmpty()) {
	 * 
	 * for(Address address : customer.getAddress()) {
	 * 
	 * Address addAddress = addAddress(address);
	 * 
	 * if(address.getZip().toString().length()<5) return
	 * HttpStatus.BAD_REQUEST.value(); else customer2.getAddress().add(addAddress);
	 * }
	 * 
	 * }
	 * 
	 * customerRepository.save(customer2);
	 * 
	 * return HttpStatus.CREATED.value();
	 * 
	 * }
	 * 
	 */

	public Object getCustomer(Long billNumber) {

		Optional<Customer> customer = customerRepository.findById(billNumber);
		if (customer.isPresent()) {
			return customer;

		} else
			return HttpStatus.NOT_FOUND;
	}

	public HttpStatus deleteByBillNumber(Long billNumber) {
		Optional<Customer> findById = customerRepository.findById(billNumber);
		Customer cutomer = new Customer();

		if (findById.isPresent()) {

			if (cutomer.getPhoneNumber().toString().length() == 10) {

				customerRepository.deleteById(billNumber);
				return HttpStatus.NO_CONTENT;
			}

		}
		return HttpStatus.NOT_FOUND;

	}

	public HttpStatus deleteByphoneNumber(Long phoneNumber) {

		Optional<Customer> findByPhone = customerRepository.findByPhoneNumber(phoneNumber);
		if (findByPhone.isPresent()) {
			customerRepository.deleteById(findByPhone.get().getBillingAccountNumber());
			return HttpStatus.NO_CONTENT;
		} else
			return HttpStatus.NOT_FOUND;
	}

	public Object findByPhoneNumber(Long phoneNumber) {

		Optional<Customer> findByPhoneNumber = customerRepository.findByPhoneNumber(phoneNumber);
		if (findByPhoneNumber.isPresent()) {
			return findByPhoneNumber;
		} else
			return HttpStatus.NOT_FOUND;

	}

}

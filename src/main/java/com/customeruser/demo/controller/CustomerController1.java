package com.customeruser.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customeruser.demo.model.Customer;
import com.customeruser.demo.service.CustomerService1;

@RestController
@RequestMapping("/api")
public class CustomerController1 {

	@Autowired
	CustomerService1 customerService1;

	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer,  @RequestHeader( value = "conversionID") int conversionID) {
		
		customer.setConversionId(conversionID);
		
		Long createCustomer = customerService1.createCustomer(customer);
		if(createCustomer!=400) {
			
			return new ResponseEntity<>(createCustomer,HttpStatus.CREATED);
		}else
			return new ResponseEntity<>("Please provide valid details", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("getAllCustomers/{billNumber}")
	public ResponseEntity<?> getCustomer(@Valid @PathVariable(value="billNumber") Long billNumber){
		
		Object customer = customerService1.getCustomer(billNumber);
		if(!customer.equals(400)) {

		return new ResponseEntity<>(customer, HttpStatus.OK);
	}else 
		return new ResponseEntity<>("Customer Not found", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PutMapping("update/{billNumber}")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer,
			@PathVariable(value = "billNumber") Long billAccountNumber) {
		
		Object updateCustomer = customerService1.updateCustomer(customer, billAccountNumber);
		if(!updateCustomer.equals(400)) {
			return new ResponseEntity<>(customerService1.updateCustomer(customer, billAccountNumber), HttpStatus.NO_CONTENT);
		}else
			return new ResponseEntity<>("Enter correct value", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("deleteByBillNumber/{billNumber}")
	public ResponseEntity<?> deleteByBillNumber(@Valid @PathVariable(value = "billNumber") Long billNumber){
		customerService1.deleteByBillNumber(billNumber);
		return new ResponseEntity<> ( HttpStatus.OK);

	}	
	@DeleteMapping("deleteByPhoneNumber/{phoneNumber}")
	public ResponseEntity<?> deleteByPhoneNumber(@Valid @PathVariable(value = "phoneNumber") Long phoneNumber){
	
		customerService1.deleteByphoneNumber(phoneNumber);
		return new ResponseEntity<>(HttpStatus.OK);
	
	}
	
	
	@GetMapping("findByPhoneNumber/{phoneNumber}")
	public ResponseEntity<?> findByPhoneNumber(@Valid @PathVariable(value ="phoneNumber") Long phoneNumber){
		Object findByPhoneNumber = customerService1.findByPhoneNumber(phoneNumber);
		if(!findByPhoneNumber.equals(400)) {

			return new ResponseEntity<>(findByPhoneNumber, HttpStatus.OK);
		}else 
			return new ResponseEntity<>("Customer Not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
}

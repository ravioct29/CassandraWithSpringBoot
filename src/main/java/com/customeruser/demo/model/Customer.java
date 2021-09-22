package com.customeruser.demo.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("customer")
public class Customer {
	
	private String fname;
	private String lname;
	@Email
	private String email;
	
	@PrimaryKey
	@Column
	private Long billingAccountNumber;
	//@Pattern(regexp = "(^$|[0-9]{10})")
	//@Size(min=10, max=10)
	private Long phoneNumber;
	
	private int conversionId;
	@Column("address")
	private List<Address> address;
	
	

	public Customer() {
		super();
	}

	public Customer(String fname, String lname, @Email String email,
		 Long billingAccountNumber, Long phoneNumber,
			List<Address> address, int conversionId) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.billingAccountNumber = billingAccountNumber;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.conversionId = conversionId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getBillingAccountNumber() {
		return billingAccountNumber;
	}

	public void setBillingAccountNumber(Long billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> addressList) {
		this.address = addressList;
	}
	public int getConversionId() {
		return conversionId;
	}
	public void setConversionId(int conversionId) {
		this.conversionId = conversionId;
	}
	
	

}

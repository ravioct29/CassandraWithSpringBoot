package com.customeruser.demo.model;

import java.util.Objects;

import javax.validation.constraints.Size;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("address")
@UserDefinedType("address")
@Validated
public class Address {

	private String address1;
	
	@Column("city")
	private String city;

	@Column("state")
	private String state;
	
	@Size(max =5, min =5)
	private Long zip;
	
	public Address() {
		super();
	}

	public Address(String address1, String city, Long zip,String state) {
		super();
		this.address1 = address1;
		this.city = city;
		this.zip = zip;
		this.state = state;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getZip() {
		return zip;
	}

	public void setZip(Long zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address1, city, state, zip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(address1, other.address1) && Objects.equals(city, other.city)
				&& Objects.equals(state, other.state) && Objects.equals(zip, other.zip);
	}
	
	
	
	
	
}

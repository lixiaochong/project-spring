package com.coco.springdata;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "JPA_PERSONS")
@Entity
public class Person {

	private Integer id;
	private String lastName;

	private String address;

	private String email;
	private Date birth;

	private Address addresses;

	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@JoinColumn(name="ADDRESS_ID")
	@ManyToOne
	public Address getAddresses() {
		return addresses;
	}

	public void setAddresses(Address addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", address="
				+ address + ", email=" + email + ", birth=" + birth + "]";
	}

}

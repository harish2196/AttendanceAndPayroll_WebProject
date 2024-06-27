package com.chainsys.springdemo.model;

import org.springframework.stereotype.Component;

@Component
public class ObjectModel {
	
	int id;
	String name;
	String designation;
	String email;
	String phoneNumber;
	String passWord;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "ObjectModel [id=" + id + ", name=" + name + ", designation=" + designation + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", passWord=" + passWord + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}

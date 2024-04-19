package com.tus.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
public class TestDetails {
	private String details;
	private String engineer;
	private String department;
	private EngineerContact engineerContact;

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getEngineer() {
		return engineer;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public EngineerContact getEngineerContact() {
		return engineerContact;
	}

	public void setEngineerContact(EngineerContact engineerContact) {
		this.engineerContact = engineerContact;
	}

	public TestDetails(String details, String engineer, String department, EngineerContact engineerContact) {
		super();
		this.details = details;
		this.engineer = engineer;
		this.department = department;
		this.engineerContact = engineerContact;
	}

	public TestDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
}

class EngineerContact {
    private String email;
    private String phone;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public EngineerContact(String email, String phone) {
		super();
		this.email = email;
		this.phone = phone;
	}
	public EngineerContact() {
		super();
		// TODO Auto-generated constructor stub
	}
}

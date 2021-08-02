package com.fileupload.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Input user id")
	@Pattern (regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", 
	message ="userId with length>=8 must be at least one lowercase letter, one uppercase letter, and one number")
	@Column(name = "user_id", unique = true)
	private String userId;
	
	@NotEmpty(message = "Input password")
	@Pattern (regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$", 
			message ="password with length>=8 must be at least one lowercase letter, one uppercase letter, one special character and one number")
	@Column(name = "password")
	private String password;
	
	@NotBlank(message = "Input username")
	@Column(name = "user_name", unique = true)
	private String username;
	
	@NotEmpty(message = "Input email")
	@Pattern (regexp = "^[a-zA-Z]+@[a-zA-Z]+.com$", message = "email is not suitable")
	@Column(name = "email")
	private String email;
	
	@NotEmpty(message = "Input phone number")
	@Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "phone number format is 010-xxxx-xxxx")
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;
	
	@NotBlank(message = "Input address")
	@Column(name = "address")
	private String address;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(
			@NotEmpty(message = "Input user id") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "userId with length>=8 must be at least one lowercase letter, one uppercase letter, and one number") String userId,
			@NotEmpty(message = "Input password") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password with length>=8 must be at least one lowercase letter, one uppercase letter, one special character and one number") String password,
			@NotBlank(message = "Input username") String username,
			@NotEmpty(message = "Input email") @Pattern(regexp = "^[a-zA-Z]+@[a-zA-Z]+.com$", message = "email is not suitable") String email,
			@NotEmpty(message = "Input phone number") @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "phone number format is 010-xxxx-xxxx") String phoneNumber,
			@NotBlank(message = "Input address") String address) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.userId + ", " + this.username + ", " + this.password + ", " + this.phoneNumber + ", " + this.email + ", " + this.address;
	}
}

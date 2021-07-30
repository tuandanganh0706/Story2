package com.loocreative.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class CUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Input user id")
	@Pattern (regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", 
	message ="userId with length>=8 must be at least one lowercase letter, one uppercase letter, and one number")
	@Column(name = "user_id", unique = true)
	private String userId;
	
	@NotEmpty(message = "Input password")
	@Pattern (regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
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
	
	@Lob
	@Column(name = "image", columnDefinition="BLOB")
	private byte[] image;

	public CUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CUsers(
			@NotNull(message = "Input user id") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$", message = "userId must be at least one lowercase letter, one uppercase letter, and one number") @Size(min = 8, message = "Customer Id must be at least 8 letters.") String userId,
			@NotNull(message = "Input password") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "id must be at least one lowercase letter, one uppercase letter, one special character and one number") @Size(min = 8, message = "Password must be least 8 letters") String password,
			@NotNull(message = "Input username") String username,
			@NotNull(message = "Input email") @Pattern(regexp = "^[a-zA-Z]+@[a-zA-Z]+.com$") String email,
			@NotNull(message = "Input email") String phoneNumber, @NotNull(message = "Input address") String address,
			byte[] image) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.image = image;
	}



	public Long getId() {
		return id;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}

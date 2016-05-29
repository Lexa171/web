package com.epam.spring.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.epam.spring.model.User;

public class UserDto {

	@NotNull
	@Size(min=3, max=20,
			 message="Username must be between 3 and 20 characters long.")
	@Pattern(regexp="^[a-zA-Z0-9]+$",
	 message="Username must be alphanumeric with no spaces")
	private String userName;
	
	@NotNull
	@Size(min=6, max=30,
			 message="The password must be between 3 and 20 characters long.")
	@Pattern(regexp="^[a-zA-Z0-9]+$",
	 message="Password must be alphanumeric with no spaces")
	private String userPassword;
	
	private Integer userId;
	
	@Size(max = 40, message = "Your e-mail is too long")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String userMail;
	
	public UserDto(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	public UserDto(String userName, String userPassword, String userMail) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userMail = userMail;
	}

	public UserDto(User user) {
		this.userName = user.getUserName();
		this.userPassword = user.getUserPassword();
		this.userId = user.getUserId();
	}

	public UserDto() {

	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserMail() {
		return this.userMail;
	}
	
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

}

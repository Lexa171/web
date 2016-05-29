package com.epam.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.epam.spring.dto.UserDto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users", uniqueConstraints = { @UniqueConstraint(columnNames = "USER_NAME") })
public class User {

	private Integer userId;
	private String userName;
	private String userPassword;
	private String userRole;
	private String userMail;

	public User() {
	}

	public User(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	public User(Integer userId, String userName, String userPassword, String userRole) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}
	
	public User(Integer userId, String userName, String userPassword, String userRole, String userMail) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userMail = userMail;
	}
	
	public User(UserDto userDto) {
		this.userName = userDto.getUserName();
		this.userPassword = userDto.getUserPassword();
		this.userMail = userDto.getUserMail();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_PASSWORD", nullable = false, length = 30)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "USER_ROLE", length = 30)
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Column(name = "USER_MAIL", length = 40, unique = true)
	public String getUserMail() {
		return this.userMail;
	}
	
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}


	@Override
	public String toString() {
		return "User INFO [ID: " + userId + ", Name: " + userName + "]";
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "userId",  "userPassword", "userRole", "userMail");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "userId","userPassword", "userRole", "userMail");
	}

}

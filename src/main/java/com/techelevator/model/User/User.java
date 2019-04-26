package com.techelevator.model.User;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	private long userNameId;
	private String userName;

	@Size(min = 10, message = "Password too short, must be at least 10")
	@Pattern.List({ @Pattern(regexp = ".*[a-z].*", message = "Must have a lower case"),
			@Pattern(regexp = ".*[A-Z].*", message = "Must have a capital") })
	private String password;
	private String role;

	private String confirmPassword;

	public String getUserName() {
		return userName;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public long getUserNameId() {
		return userNameId;
	}

	public void setUserNameId(long userNameId) {
		this.userNameId = userNameId;
	}
}

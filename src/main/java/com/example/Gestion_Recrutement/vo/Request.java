package com.example.Gestion_Recrutement.vo;

public class Request {

	private String userEmail;
	private String userPwd;
	private String roles;
	private String newuserEmail;

	public Request() {
	}

	public Request(String roles) {
		this.roles = roles;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}



	public String getNewuserEmail() {
		return newuserEmail;
	}

	public void setNewuserEmail(String newuserEmail) {
		this.newuserEmail = newuserEmail;
	}
}

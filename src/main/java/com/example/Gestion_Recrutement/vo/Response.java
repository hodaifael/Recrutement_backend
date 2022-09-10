package com.example.Gestion_Recrutement.vo;

import java.util.Optional;

public class Response {

	private String token;
	private Optional<String> roles;
	private Long user_id;
	private Long company_id;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Optional<String> getRoles() {
		return roles;
	}

	public void setRoles(Optional<String> roles) {
		this.roles = roles;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}

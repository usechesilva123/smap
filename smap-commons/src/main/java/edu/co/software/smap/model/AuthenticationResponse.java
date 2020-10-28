package edu.co.software.smap.model;

public class AuthenticationResponse {
	
	private final String jwt;
	private final String role;
	private final Integer id;
	
	public AuthenticationResponse(String jwt, String role, Integer id) {
		super();
		this.jwt = jwt;
		this.role = role;
		this.id = id;
	}
	
	public String getJwt() {
		return jwt;
	}
	public String getRole() {
		return role;
	}
	public Integer getId() {
		return id;
	}

}

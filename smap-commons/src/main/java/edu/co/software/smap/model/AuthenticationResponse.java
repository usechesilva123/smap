package edu.co.software.smap.model;

public class AuthenticationResponse {
	
	private final String jwt;
	private final String role;
	private final Usuario usuario;
	
	public AuthenticationResponse(String jwt, String role, Usuario user) {
		super();
		this.jwt = jwt;
		this.role = role;
		this.usuario = user;
	}
	
	public String getJwt() {
		return jwt;
	}
	public String getRole() {
		return role;
	}
	public Usuario getUsuario() {
		return usuario;
	}

}

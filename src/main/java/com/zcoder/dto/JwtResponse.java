package com.zcoder.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String name;
    private Collection<?extends GrantedAuthority> roles;

	public JwtResponse() {
	}

    public JwtResponse(String token, String name, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.name = name;
        this.roles = roles;
    }
	
//	public JwtResponse(String token, String name) {
//		this.token = token;
//		this.name = name;
//	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}
}
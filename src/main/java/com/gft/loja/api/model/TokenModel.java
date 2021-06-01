package com.gft.loja.api.model;

public class TokenModel {

	private String token;
	private String tipo;

	public TokenModel(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	
}

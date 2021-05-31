package com.gft.loja.api.input;

import javax.validation.constraints.NotNull;

public class ClienteIdInput {

	@NotNull
	Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}

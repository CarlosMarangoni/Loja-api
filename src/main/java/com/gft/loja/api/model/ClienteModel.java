package com.gft.loja.api.model;

public class ClienteModel {

	private Long id;

	private String nome;

	private String cpf;

	private String telefone;

	private String email;

	private EnderecoResumoModel endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnderecoResumoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoResumoModel endereco) {
		this.endereco = endereco;
	}

}

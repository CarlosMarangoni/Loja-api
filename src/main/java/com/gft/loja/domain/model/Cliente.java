package com.gft.loja.domain.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente extends Usuario {


	private static final long serialVersionUID = 1L;

	@CPF
	private String cpf;

	@NotNull
	private String telefone;

	@Valid
	@Embedded
	private Endereco endereco;


	public Cliente() {
	}

	public Cliente(@NotNull String email, @NotNull String nome, @NotNull String senha, List<Perfil> perfis,
			@CPF String cpf, @NotNull String telefone, @Valid Endereco endereco) {
		super(email, nome, senha, perfis);
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	

}

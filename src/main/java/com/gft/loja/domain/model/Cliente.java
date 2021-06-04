package com.gft.loja.domain.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente extends Usuario {

	private static final long serialVersionUID = 1L;

	@CPF
	@NotNull
	private String cpf;

	@NotNull
	@Size(min=0, max = 20)
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

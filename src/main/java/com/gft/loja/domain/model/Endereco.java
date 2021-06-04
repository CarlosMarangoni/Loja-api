package com.gft.loja.domain.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {

	@NotNull
	@Size(min=5, max = 60)
	private String logradouro;

	@NotNull
	@Size(min=1, max = 10)
	private String numero;

	private String complemento;

	@NotNull
	@Size(min=3, max = 30)
	private String bairro;

	@NotNull
	@Size(min=3, max = 25)
	private String municipio;

	@NotNull
	@Size(min=2, max = 20)
	private String estado;

	@NotNull
	@Size(min=8, max = 10)
	private String cep;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}

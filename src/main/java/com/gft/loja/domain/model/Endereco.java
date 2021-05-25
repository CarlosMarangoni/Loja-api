package com.gft.loja.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {

	@NotBlank
	@Column(name = "fornecedor_logradouro")
	private String logradouro;

	@NotBlank
	@Column(name = "fornecedor_numero")
	private String numero;

	@NotBlank
	@Column(name = "fornecedor_complemento")
	private String complemento;

	@NotBlank
	@Column(name = "fornecedor_bairro")
	private String bairro;

	@NotBlank
	@Column(name = "fornecedor_municipio")
	private String municipio;

	@NotBlank
	@Column(name = "fornecedor_estado")
	private String estado;

	@NotBlank
	@Column(name = "fornecedor_cep")
	@Size(min = 8, max = 8)
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

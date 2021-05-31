package com.gft.loja.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.gft.loja.domain.model.ItensCompra;

public class CompraModel {

	private Long id;

	private String emailFornecedor;

	private String nomeFornecedor;

	private OffsetDateTime dataCompra;


	private List<ItensCompra> itensCompra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailFornecedor() {
		return emailFornecedor;
	}

	public void setEmailFornecedor(String emailFornecedor) {
		this.emailFornecedor = emailFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public OffsetDateTime getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(OffsetDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}

	public List<ItensCompra> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItensCompra> itensCompra) {
		this.itensCompra = itensCompra;
	}

}

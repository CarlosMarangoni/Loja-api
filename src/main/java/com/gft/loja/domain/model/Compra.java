package com.gft.loja.domain.model;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataCompra;
	
	@ManyToOne
	private Fornecedor fornecedor;	
	
	@NotNull
	private String notaFiscal;
	
	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "itensCompraPK.compra",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ItensCompra> itens = new LinkedList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(OffsetDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public List<ItensCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItensCompra> itens) {
		this.itens = itens;
	}
	
	
}

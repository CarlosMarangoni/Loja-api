package com.gft.loja.domain.model;

import java.time.OffsetDateTime;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private OffsetDateTime dataCompra;
	
	@ManyToOne
	private Fornecedor fornecedor;	
	
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

	public List<ItensCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItensCompra> itens) {
		this.itens = itens;
	}
	
	
}

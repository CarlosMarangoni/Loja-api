package com.gft.loja.domain.model;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gft.loja.domain.model.enumeration.StatusVenda;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private StatusVenda statusVenda;
	
	@ManyToOne
	private Cliente cliente;
	
    private OffsetDateTime dataVenda;
	
	@NotEmpty
	@OneToMany(mappedBy = "itensVendaPK.venda",cascade = CascadeType.ALL)
	private List<ItensVenda> itensVenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusVenda getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(StatusVenda statusVenda) {
		this.statusVenda = statusVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OffsetDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(OffsetDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public List<ItensVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItensVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	
}

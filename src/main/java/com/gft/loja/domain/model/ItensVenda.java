package com.gft.loja.domain.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gft.loja.domain.model.pk.ItensVendaPK;

@Entity
public class ItensVenda {

	@EmbeddedId
	private ItensVendaPK itensVendaPK;
	
	@JsonIgnore
	private Integer item;
	
	private double quantidade;
	
	private BigDecimal valorVenda;

	public ItensVendaPK getItensVendaPK() {
		return itensVendaPK;
	}

	public void setItensVendaPK(ItensVendaPK itensVendaPK) {
		this.itensVendaPK = itensVendaPK;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	
	
	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	
}

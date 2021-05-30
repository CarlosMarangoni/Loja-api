package com.gft.loja.domain.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gft.loja.domain.model.pk.ItensVendaPK;

@Entity
public class ItensVenda {

	@EmbeddedId
	private ItensVendaPK itensVendaPK = new ItensVendaPK();
	
	@JsonIgnore
	private Integer item;
	
	@DecimalMin(value="0.01")
	private double quantidade;
	
	private BigDecimal valorVenda;
	
	

	public ItensVenda(Venda venda, Produto produto, double quantidade, BigDecimal valorVenda) {
		
		itensVendaPK.setVenda(venda);
		itensVendaPK.setProduto(produto);
		this.quantidade = quantidade;
		this.valorVenda = valorVenda;
	}
	
	

	public ItensVenda() {
	}



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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itensVendaPK == null) ? 0 : itensVendaPK.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensVenda other = (ItensVenda) obj;
		if (itensVendaPK == null) {
			if (other.itensVendaPK != null)
				return false;
		} else if (!itensVendaPK.equals(other.itensVendaPK))
			return false;
		return true;
	}
	
	
}

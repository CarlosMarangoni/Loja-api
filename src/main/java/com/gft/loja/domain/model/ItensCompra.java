package com.gft.loja.domain.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gft.loja.domain.model.pk.ItensCompraPK;

@Entity
@Table(name = "itens_compra")
public class ItensCompra {

	@EmbeddedId
	private ItensCompraPK itensCompraPK = new ItensCompraPK();

	@JsonIgnore
	private Integer item;

	@DecimalMin(value="0.01")
	private double quantidade;

	@NotNull
	private BigDecimal valorCompra;
	
	

	public ItensCompra(Compra compra, Produto produto, Integer item,double quantidade,
			BigDecimal valorCompra) {
		itensCompraPK.setCompra(compra);
		itensCompraPK.setProduto(produto);
		this.quantidade = quantidade;
		this.valorCompra = valorCompra;
	}
	
	

	public ItensCompra() {
	}



	public ItensCompraPK getItensCompraPK() {
		return itensCompraPK;
	}

	public void setItensCompraPK(ItensCompraPK itensCompraPK) {
		this.itensCompraPK = itensCompraPK;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itensCompraPK == null) ? 0 : itensCompraPK.hashCode());
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
		ItensCompra other = (ItensCompra) obj;
		if (itensCompraPK == null) {
			if (other.itensCompraPK != null)
				return false;
		} else if (!itensCompraPK.equals(other.itensCompraPK))
			return false;
		return true;
	}

	
}

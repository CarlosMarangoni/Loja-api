package com.gft.loja.domain.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gft.loja.domain.model.pk.ItensCompraPK;

@Entity
@Table(name = "itens_compra")
public class ItensCompra {

	@Valid
	@EmbeddedId
	private ItensCompraPK itensCompraPK;

	@JsonIgnore
	private Integer item;

	@Min(1)
	private double quantidade;

	@NotNull
	private BigDecimal valorCompra;

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

}

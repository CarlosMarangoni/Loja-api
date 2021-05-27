package com.gft.loja.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class Estoque {

	@Id
	private Long id;

	@OneToOne
	private Produto produto;

	private double quantidade;
	
	private BigDecimal valorVenda;

	public Estoque() {
	}

	
	public Estoque(Long id, Produto produto, Integer quantidade, BigDecimal valorVenda) {
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorVenda = valorVenda;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	public void somaQuantidadeProduto(double quantidadeSoma ) {
		this.quantidade = this.quantidade + quantidadeSoma;
	}
	
	public void subtraiQuantidadeProduto(double quantidadeSubtracao) {
		this.quantidade = this.quantidade - quantidadeSubtracao;
	}

}

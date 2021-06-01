package com.gft.loja.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.gft.loja.domain.exception.ItemBodyViolationException;

@Entity
public class Estoque {

	@Id
	private Long id;

	@OneToOne
	@JsonProperty(access = Access.READ_ONLY)
	private Produto produto;

	@Min(0)
	@JsonProperty(access = Access.READ_ONLY)
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

	public void somaQuantidadeProduto(double quantidadeSoma) {
		this.quantidade = this.quantidade + quantidadeSoma;
	}

	public void subtraiQuantidadeProduto(double quantidadeSubtracao) {
		if (this.quantidade < quantidadeSubtracao) {
			throw new ItemBodyViolationException(
					"Estoque do produto " + this.getId() + " - " + this.getProduto().getDescricao()
							+ " não disponível. Faça o preenchimento correto e tente novamente");
		}
		this.quantidade -= quantidadeSubtracao;
	}
	
	public BigDecimal calculaVenda(double quantidade) {
		return this.valorVenda.multiply(new BigDecimal(quantidade));
	}

}

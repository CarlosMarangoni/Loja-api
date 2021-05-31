package com.gft.loja.api.model;

import java.math.BigDecimal;

import com.gft.loja.domain.model.enumeration.UnidadeMedida;

public class EstoqueModel {

	private Long id;

	private String produtoDescricao;

	private UnidadeMedida produtoUnidadeMedida;

	private double quantidade;

	private BigDecimal valorVenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProdutoDescricao() {
		return produtoDescricao;
	}

	public void setProdutoDescricao(String produtoDescricao) {
		this.produtoDescricao = produtoDescricao;
	}

	public UnidadeMedida getProdutoUnidadeMedida() {
		return produtoUnidadeMedida;
	}

	public void setProdutoUnidadeMedida(UnidadeMedida produtoUnidadeMedida) {
		this.produtoUnidadeMedida = produtoUnidadeMedida;
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

}

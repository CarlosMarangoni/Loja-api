package com.gft.loja.api.model;

import java.math.BigDecimal;

import com.gft.loja.domain.model.enumeration.UnidadeMedida;

public class EstoqueResumoModel {

	private String produtoDescricao;

	private UnidadeMedida produtoUnidadeMedida;

	private BigDecimal valorVenda;

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

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

}

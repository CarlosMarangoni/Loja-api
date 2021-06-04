package com.gft.loja.api.model;

import com.gft.loja.domain.model.enumeration.UnidadeMedida;

public class ProdutoModel {

	private Long id;

	private String descricao;

	private UnidadeMedida unidadeMedida;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

}

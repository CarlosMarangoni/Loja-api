package com.gft.loja.domain.model.enumeration;

import java.util.Arrays;

public enum StatusVenda {
	PENDENTE("Pendente"), RECEBIDO("Recebido");

	private String descricao;

	private StatusVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusVenda getEnum(String descricao) {
		return Arrays.asList(StatusVenda.values()).stream().filter(f -> f.descricao.equalsIgnoreCase(descricao))
				.findFirst().get();
	}
}

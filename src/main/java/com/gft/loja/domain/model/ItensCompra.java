package com.gft.loja.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.gft.loja.domain.model.pk.ItensCompraPK;

@Entity
@Table(name = "itens_compra")
public class ItensCompra {
	
	@EmbeddedId
	private ItensCompraPK itensCompraPK ;
	
	@NotNull
	private Integer item;
	
	
}

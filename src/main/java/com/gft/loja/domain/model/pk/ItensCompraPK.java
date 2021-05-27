package com.gft.loja.domain.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.gft.loja.domain.model.Compra;
import com.gft.loja.domain.model.Produto;

@Embeddable
public class ItensCompraPK implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Compra compra;
}

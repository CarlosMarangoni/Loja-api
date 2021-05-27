package com.gft.loja.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
    private Date dataPedido;
	
	@ManyToOne
	private Fornecedor fornecedor;	
	
	@OneToMany(mappedBy = "itensCompraPK.compra",fetch = FetchType.EAGER)
	private Set<ItensCompra> itens = new HashSet<>();
	
}

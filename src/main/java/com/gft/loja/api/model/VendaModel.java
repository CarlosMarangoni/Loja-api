package com.gft.loja.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.gft.loja.domain.model.ItensVenda;
import com.gft.loja.domain.model.enumeration.StatusVenda;

public class VendaModel {

	private Long id;
	
	private StatusVenda statusVenda;
	
	private String emailCliente;
	
	private String nomeCliente;
	
	private OffsetDateTime dataVenda;
	
	private EnderecoResumoModel clienteEndereco;
	
	private List<ItensVenda> itensVenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusVenda getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(StatusVenda statusVenda) {
		this.statusVenda = statusVenda;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public OffsetDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(OffsetDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public EnderecoResumoModel getClienteEndereco() {
		return clienteEndereco;
	}

	public void setClienteEndereco(EnderecoResumoModel clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}

	public List<ItensVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItensVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	
	
	
}

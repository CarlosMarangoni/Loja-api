package com.gft.loja.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Compra entrada;

	@Column(name = "entrada_quantidade")
	private double quantidadeCompra;

	@Column(name = "entrada_valor")
	private BigDecimal valorCompra;

	@Column(name = "entrada_total")
	private BigDecimal totalCompra;

	@ManyToOne
	private Venda saida;

	@Column(name = "saida_quantidade")
	private double quantidadeVenda;

	@Column(name = "saida_valor")
	private BigDecimal valorVenda;

	@Column(name = "saida_total")
	private BigDecimal totalVenda;

	public Movimento(Compra entrada, double quantidadeCompra, BigDecimal valorCompra, BigDecimal totalCompra) {
		this.entrada = entrada;
		this.quantidadeCompra = quantidadeCompra;
		this.valorCompra = valorCompra;
		this.totalCompra = totalCompra;
	}

	public Movimento(Venda saida, double quantidadeVenda, BigDecimal valorVenda, BigDecimal totalVenda) {
		this.saida = saida;
		this.quantidadeVenda = quantidadeVenda;
		this.valorVenda = valorVenda;
		this.totalVenda = totalVenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Compra getEntrada() {
		return entrada;
	}

	public void setEntrada(Compra entrada) {
		this.entrada = entrada;
	}

	public double getQuantidadeCompra() {
		return quantidadeCompra;
	}

	public void setQuantidadeCompra(double quantidadeCompra) {
		this.quantidadeCompra = quantidadeCompra;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public void setTotalVenda(BigDecimal totalVenda) {
		this.totalVenda = totalVenda;
	}

	public Venda getSaida() {
		return saida;
	}

	public void setSaida(Venda saida) {
		this.saida = saida;
	}

	public double getQuantidadeVenda() {
		return quantidadeVenda;
	}

	public void setQuantidadeVenda(double quantidadeVenda) {
		this.quantidadeVenda = quantidadeVenda;
	}

}

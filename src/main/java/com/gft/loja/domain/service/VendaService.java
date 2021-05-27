package com.gft.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeNaoEncontradaException;
import com.gft.loja.domain.model.Venda;
import com.gft.loja.domain.repository.EstoqueRepository;
import com.gft.loja.domain.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	public List<Venda> listar() {
		return vendaRepository.findAll();
	}

	public Venda buscar(Long id) {
		return vendaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado."));

	}

	@Transactional
	public Venda salvar(Venda venda) {
		venda.getItensVenda().forEach(c ->

		estoqueRepository.findById(c.getItensVendaPK().getProduto().getId()).get()
				.subtraiQuantidadeProduto(c.getQuantidade()));

		Venda vendaSalva = vendaRepository.save(venda);
		vendaSalva.getItensVenda().forEach(c -> c.getItensVendaPK().setVenda(vendaSalva));
		return vendaSalva;
	}

}
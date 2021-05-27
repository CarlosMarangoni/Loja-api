package com.gft.loja.domain.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeNaoEncontradaException;
import com.gft.loja.domain.model.Compra;
import com.gft.loja.domain.repository.CompraRepository;
import com.gft.loja.domain.repository.EstoqueRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	public List<Compra> listar() {
		return compraRepository.findAll();
	}

	public Compra buscar(Long id) {
		return compraRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Compra não encontrada."));
	}

	@Transactional
	public Compra salvar(Compra compra) {
		AtomicInteger atomicSum = new AtomicInteger();
		compra.getItens().forEach(c ->

		estoqueRepository.findById(c.getItensCompraPK().getProduto().getId()).get()
				.somaQuantidadeProduto(c.getQuantidade()));

		Compra compraSalva = compraRepository.save(compra);
		compraSalva.getItens().forEach(c -> c.getItensCompraPK().setCompra(compraSalva));
		compraSalva.getItens().forEach(c -> c.setItem(atomicSum.addAndGet(1)));
		return compraSalva;
	}

}

package com.gft.loja.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.ItemBodyViolationException;
import com.gft.loja.domain.model.Compra;
import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.repository.CompraRepository;
import com.gft.loja.domain.repository.EstoqueRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private EstoqueService estoqueService;

	@Autowired
	private FornecedorService fornecedorService;

	public List<Compra> listar() {
		return compraRepository.findAll();
	}

	public Compra buscar(Long id) {
		return compraRepository.findById(id).get();
	}

	@Transactional
	public Compra salvar(Compra compra) {
		AtomicInteger atomicSum = new AtomicInteger(0);

		try {
			compra.getItens().forEach(c ->{
				
				if (c.getItensCompraPK().getProduto() == null) {
					throw new ItemBodyViolationException(
							"Código do produto inválido. Faça o preenchimento correto e tente novamente.");
				}
				
				
				Estoque estoque = estoqueService.buscar(c.getItensCompraPK().getProduto().getId());
				estoque.somaQuantidadeProduto(c.getQuantidade());
				c.getItensCompraPK().setCompra(compra);
				c.setItem(atomicSum.incrementAndGet());
			});
			compra.setFornecedor(fornecedorService.buscar(compra.getFornecedor().getId()));
			compra.setDataCompra(OffsetDateTime.now());

		} catch (NoSuchElementException e) {
			throw new ItemBodyViolationException(e.getMessage());
		}

		return compraRepository.save(compra);
	}

}

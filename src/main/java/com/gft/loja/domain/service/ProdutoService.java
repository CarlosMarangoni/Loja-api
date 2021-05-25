package com.gft.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeEmUsoException;
import com.gft.loja.domain.exception.EntidadeNaoEncontradaException;
import com.gft.loja.domain.model.Produto;
import com.gft.loja.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public Produto buscar(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado."));

	}

	@Transactional
	public Produto salvar(Produto produto) {
		boolean produtoExiste = produtoRepository.findByDescricao(produto.getDescricao()).stream()
				.anyMatch(produtoExistente -> !produtoExistente.equals(produto));
		if (produtoExiste) {
			throw new EntidadeEmUsoException("Já existe um produto cadastrado com esta descrição.");
		}

		return produtoRepository.save(produto);
	}

	public void excluir(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Produto está em uso por outra entidade.");
		}

	}
}

package com.gft.loja.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeEmUsoException;
import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.model.Produto;
import com.gft.loja.domain.repository.EstoqueRepository;
import com.gft.loja.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public Produto buscar(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Produto não encontrado. Faça o preenchimento correto e tente novamente"));

	}

	@Transactional
	public Produto salvar(Produto produto) {
		boolean produtoExiste = produtoRepository.findByDescricao(produto.getDescricao()).stream()
				.anyMatch(produtoExistente -> !produtoExistente.equals(produto));

		if (produtoExiste) {
			throw new EntidadeEmUsoException("Já existe um produto cadastrado com esta descrição.");
		}

		Produto produtoSalvo = produtoRepository.save(produto);
		Estoque estoque = new Estoque(produtoSalvo.getId(), produto, 0, null); // No momento que cria o
																							// produto, cria um estoque
																							// com quantidade e valor 0
		estoqueRepository.save(estoque);

		return produtoSalvo;
	}
	
	

	public void excluir(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Produto está em uso por outra entidade.");
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException();
		}

	}

	public Produto atualizar(Long produtoId,Produto produto) {
		Produto produtoBuscado = buscar(produtoId);
		BeanUtils.copyProperties(produto, produtoBuscado,"id");
		salvar(produtoBuscado);
		return produtoBuscado;
	}
}

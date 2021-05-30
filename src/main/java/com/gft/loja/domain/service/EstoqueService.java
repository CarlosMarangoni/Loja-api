package com.gft.loja.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.repository.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;

	public List<Estoque> listar() {
		return estoqueRepository.findAll();
	}

	public Estoque buscar(Long id) {
		return estoqueRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Produto não encontrado. Faça o preenchimento correto e tente novamente"));

	}

	@Transactional
	public Estoque atualizar(Long id, Estoque estoque) {		
		Estoque estoqueSalvo = estoqueRepository.findById(id).get();
		estoqueSalvo.setValorVenda(estoque.getValorVenda());
		return estoqueRepository.save(estoqueSalvo);
	}

}

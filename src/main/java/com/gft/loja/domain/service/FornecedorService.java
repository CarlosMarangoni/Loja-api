package com.gft.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeEmUsoException;
import com.gft.loja.domain.exception.EntidadeNaoEncontradaException;
import com.gft.loja.domain.model.Fornecedor;
import com.gft.loja.domain.repository.FornecedorRepository;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}
	
	public Fornecedor buscar(Long id) {
		return fornecedorRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado."));

	}
	
	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) {
		boolean fornecedorExiste = fornecedorRepository.findByNome(fornecedor.getNome()).stream()
				.anyMatch(fornecedorExistente -> !fornecedorExistente.equals(fornecedor));
		if (fornecedorExiste) {
			throw new EntidadeEmUsoException("Já existe um fornecedor cadastrado com este nome.");
		}

		return fornecedorRepository.save(fornecedor);
	}

	public void excluir(Long fornecedorId) {
		try {
			fornecedorRepository.deleteById(fornecedorId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Fornecedor está em uso por outra entidade.");
		}

	}
	
}

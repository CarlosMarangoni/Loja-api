package com.gft.loja.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeEmUsoException;
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
		return fornecedorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Fornecedor não encontrado. Faça o preenchimento correto e tente novamente"));

	}
	
	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) {
		if (fornecedorJaExiste(fornecedor)) {
			throw new EntidadeEmUsoException("Já existe um fornecedor cadastrado com este CNPJ.");
		}

		return fornecedorRepository.save(fornecedor);
	}

	

	public void excluir(Long fornecedorId) {
		try {
			fornecedorRepository.deleteById(fornecedorId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Fornecedor está em uso por outra entidade.");
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException();
		}

	}
	
	private boolean fornecedorJaExiste(Fornecedor fornecedor) {
		 return fornecedorRepository.findByCnpj(fornecedor.getCnpj()).stream()
				.anyMatch(fornecedorExistente -> !fornecedorExistente.equals(fornecedor));		
	}
	
}

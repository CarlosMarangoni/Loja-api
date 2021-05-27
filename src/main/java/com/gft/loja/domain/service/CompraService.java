package com.gft.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeEmUsoException;
import com.gft.loja.domain.exception.EntidadeNaoEncontradaException;
import com.gft.loja.domain.model.Compra;
import com.gft.loja.domain.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	public List<Compra> listar() {
		return compraRepository.findAll();
	}

	public Compra buscar(Long id) {
		return compraRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Compra não encontrado."));
	}

	@Transactional
	public Compra salvar(Compra compra) {
		return compraRepository.save(compra);
	}

	public void excluir(Long compraId) {
		try {
			compraRepository.deleteById(compraId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Compra está em uso por outra entidade.");
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Compra não encontrado.");
		}

	}


}

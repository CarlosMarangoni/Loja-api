package com.gft.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeEmUsoException;
import com.gft.loja.domain.exception.EntidadeNaoEncontradaException;
import com.gft.loja.domain.model.Cliente;
import com.gft.loja.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	public Cliente buscar(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado."));

	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		if (clienteJaExiste(cliente)) {
			throw new EntidadeEmUsoException("Já existe um cliente cadastrado com este CPF.");
		}

		return clienteRepository.save(cliente);
	}

	public void excluir(Long clienteId) {
		try {
			clienteRepository.deleteById(clienteId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Cliente está em uso por outra entidade.");
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Cliente não encontrado.");
		}

	}

	private boolean clienteJaExiste(Cliente cliente) {
		return clienteRepository.findByCpf(cliente.getCpf()).stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
	}

}

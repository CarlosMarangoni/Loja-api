package com.gft.loja.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeEmUsoException;
import com.gft.loja.domain.model.Cliente;
import com.gft.loja.domain.model.Usuario;
import com.gft.loja.domain.repository.PerfilRepository;
import com.gft.loja.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public Usuario buscar(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				"Usuario não encontrado. Faça o preenchimento correto e tente novamente"));

	}

	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		if (usuarioJaExiste(usuario)) {
			throw new EntidadeEmUsoException("Já existe um usuario cadastrado com este e-mail.");
		}
		return usuarioRepository.save(usuario);
	}

	@Transactional
	public Cliente salvarCliente(Cliente cliente) {
		if (usuarioJaExiste(cliente)) {
			throw new EntidadeEmUsoException("Já existe um usuario cadastrado com este e-mail.");
		}
		cliente.setPerfis(perfilRepository.findByDescricao("CLIENTE"));
		return usuarioRepository.save(cliente);
	}

	public void excluir(Long usuarioId) {
		try {
			usuarioRepository.deleteById(usuarioId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Usuario está em uso por outra entidade.");
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException();
		}

	}

	private boolean usuarioJaExiste(Usuario usuario) {
		return usuarioRepository.findByEmail(usuario.getEmail()).stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(usuario));
	}

}

package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.domain.model.Usuario;
import com.gft.loja.domain.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@PreAuthorize("hasAuthority('LOJA')")
	public List<Usuario> listar() {
		return usuarioService.listar();
	}

	@GetMapping("/{usuarioId}")
	@PreAuthorize("hasAuthority('LOJA')")
	public Usuario buscar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscar(usuarioId);
		return usuario;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('LOJA')")
	public ResponseEntity<?> adicionar(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalva = usuarioService.salvarUsuario(usuario);

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalva);
	}

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('LOJA')")
	public void excluir(@PathVariable Long usuarioId) {
		usuarioService.excluir(usuarioId);
	}

}

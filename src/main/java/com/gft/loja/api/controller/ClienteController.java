package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.api.mapper.ClienteMapper;
import com.gft.loja.api.model.ClienteModel;
import com.gft.loja.domain.model.Cliente;
import com.gft.loja.domain.service.ClienteService;
import com.gft.loja.domain.service.UsuarioService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteMapper clienteMapper;

	@GetMapping
	@PreAuthorize("hasAuthority('LOJA')")
	public List<ClienteModel> listarClientes() {
		return clienteMapper.toCollectionModel(clienteService.listar());
	}

	
	@GetMapping("/{clienteId}")
	public ClienteModel buscar(@PathVariable Long clienteId) {
		return clienteMapper.toModel(clienteService.buscar(clienteId));
		
	}

	@PostMapping("/cadastrar")
	public ClienteModel adicionarCliente(@Valid @RequestBody Cliente cliente) {
		return clienteMapper.toModel(usuarioService.salvarCliente(cliente));

	}
	
	@DeleteMapping("/{clienteId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long clienteId){
		clienteService.excluir(clienteId);
	}

}

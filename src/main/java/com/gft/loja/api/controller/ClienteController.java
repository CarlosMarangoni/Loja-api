package com.gft.loja.api.controller;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.domain.model.Cliente;
import com.gft.loja.domain.repository.ClienteRepository;
import com.gft.loja.domain.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteService.listar();
	}
	
	@GetMapping("/{clienteId}")
	public Cliente buscar(@PathVariable Long clienteId) {
		Cliente cliente = clienteService.buscar(clienteId);
		return cliente;
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@Valid @RequestBody Cliente cliente){
		Cliente clienteSalvo = clienteService.salvar(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<?> editar (@Valid @RequestBody Cliente cliente,@PathVariable Long clienteId){
		Cliente clienteBuscado = clienteService.buscar(clienteId);
		BeanUtils.copyProperties(cliente, clienteBuscado,"id");
		clienteService.salvar(clienteBuscado);
		return ResponseEntity.ok(clienteBuscado);
	}
	
	@DeleteMapping("/{clienteId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long clienteId){
		clienteService.excluir(clienteId);
	}
}

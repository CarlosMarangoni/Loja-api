package com.gft.loja.api.controller;

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

import com.gft.loja.domain.model.Fornecedor;
import com.gft.loja.domain.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

	;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping
	public List<Fornecedor> listar(){
		return fornecedorService.listar();
	}
	
	@GetMapping("/nome/{fornecedorNome}")
	public List<Fornecedor> listarComFiltroNome(@PathVariable String fornecedorNome){
		return fornecedorService.listarComFiltroNome(fornecedorNome);
	}
	
	@GetMapping("/cnpj/{fornecedorCnpj}")
	public List<Fornecedor> listarComFiltroCnpj(@PathVariable String fornecedorCnpj){
		return fornecedorService.listarComFiltroCnpj(fornecedorCnpj);
	}
	
	@GetMapping("/{fornecedorId}")
	public Fornecedor buscar(@PathVariable Long fornecedorId) {
		Fornecedor fornecedor = fornecedorService.buscar(fornecedorId);
		return fornecedor;
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@Valid @RequestBody Fornecedor fornecedor){
		Fornecedor fornecedorSalvo = fornecedorService.salvar(fornecedor);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}
	
	@PutMapping("/{fornecedorId}")
	public ResponseEntity<?> editar (@Valid @RequestBody Fornecedor fornecedor,@PathVariable Long fornecedorId){
		Fornecedor fornecedorBuscado = fornecedorService.buscar(fornecedorId);
		BeanUtils.copyProperties(fornecedor, fornecedorBuscado,"id");
		fornecedorService.salvar(fornecedorBuscado);
		return ResponseEntity.ok(fornecedorBuscado);
	}
	
	@DeleteMapping("/{fornecedorId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long fornecedorId){
		fornecedorService.excluir(fornecedorId);
	}
}

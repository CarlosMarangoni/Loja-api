package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.repository.EstoqueRepository;
import com.gft.loja.domain.service.EstoqueService;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private EstoqueService estoqueService;
	
	@GetMapping
	public List<Estoque> listar(){
		return estoqueService.listar();
	}
	
	@GetMapping("/{estoqueId}")
	public Estoque buscar(@PathVariable Long estoqueId) {
		Estoque estoque = estoqueService.buscar(estoqueId);
		return estoque;
	}
	

	@PutMapping("/{estoqueId}")
	public Estoque atualizar (@Valid @RequestBody Estoque estoque,@PathVariable Long estoqueId){		
		return estoqueService.atualizar(estoqueId,estoque);
	}
	
	
}

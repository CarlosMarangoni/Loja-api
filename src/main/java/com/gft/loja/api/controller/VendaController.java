package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.domain.model.Venda;
import com.gft.loja.domain.repository.VendaRepository;
import com.gft.loja.domain.service.VendaService;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping
	public List<Venda> listar(){
		return vendaService.listar();
	}
	
	@GetMapping("/{vendaId}")
	public Venda buscar(@PathVariable Long vendaId) {
		Venda venda = vendaService.buscar(vendaId);
		return venda;
	}
	

	@PostMapping
	public ResponseEntity<?> adicionar(@Valid @RequestBody Venda venda){
		Venda vendaSalva = vendaService.salvar(venda);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
	}
	
	
}

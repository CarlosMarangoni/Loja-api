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

import com.gft.loja.domain.model.Compra;
import com.gft.loja.domain.service.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {


	@Autowired
	private CompraService compraService;
	
	@GetMapping
	public List<Compra> listar(){
		return compraService.listar();
	}
	
	@GetMapping("/{compraId}")
	public Compra buscar(@PathVariable Long compraId) {
		Compra compra = compraService.buscar(compraId);
		return compra;
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@Valid @RequestBody Compra compra){
		Compra compraSalva = compraService.salvar(compra);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(compraSalva);
	}
}

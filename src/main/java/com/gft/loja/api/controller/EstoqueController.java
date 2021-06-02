package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.api.mapper.EstoqueMapper;
import com.gft.loja.api.model.EstoqueModel;
import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.service.EstoqueService;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private EstoqueMapper estoqueMapper;
	
	@GetMapping
	@PreAuthorize("hasAuthority('LOJA')")
	public List<EstoqueModel> listar(){
		return estoqueMapper.toCollectionModel(estoqueService.listar());
	}
	
	@GetMapping("/{estoqueId}")
	@PreAuthorize("hasAuthority('LOJA')")
	public EstoqueModel buscar(@PathVariable Long estoqueId) {
		return estoqueMapper.toModel(estoqueService.buscar(estoqueId));
		
	}
	

	@PutMapping("/{estoqueId}")
	@PreAuthorize("hasAuthority('LOJA')")
	public EstoqueModel atualizar (@Valid @RequestBody Estoque estoque,@PathVariable Long estoqueId){		
		return estoqueMapper.toModel(estoqueService.atualizar(estoqueId,estoque));
	}
	
	
}

package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.api.mapper.VendaMapper;
import com.gft.loja.api.model.VendaModel;
import com.gft.loja.domain.model.Venda;
import com.gft.loja.domain.service.VendaService;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

	@Autowired
	private VendaService vendaService;

	@Autowired
	private VendaMapper vendaMapper;

	@GetMapping
	@PreAuthorize("hasAuthority('LOJA')")
	public List<VendaModel> listar() {
		return vendaMapper.toCollectionModel(vendaService.listar());
	}
	
	@GetMapping("/cliente/{clienteId}")
	@PreAuthorize("hasAuthority('LOJA')")
	public List<VendaModel> listar(@PathVariable Long clienteId) {
		return vendaMapper.toCollectionModel(vendaService.listarComFiltroCliente(clienteId));
	}
	
	@GetMapping("/status/{statusVenda}")
	@PreAuthorize("hasAuthority('LOJA')")
	public List<VendaModel> listar(@PathVariable String statusVenda) {
		return vendaMapper.toCollectionModel(vendaService.listarComFiltroStatusVenda(statusVenda));
	}


	@GetMapping("/{vendaId}")
	@PreAuthorize("hasAuthority('LOJA')")
	public VendaModel buscar(@PathVariable Long vendaId) {

		return vendaMapper.toModel(vendaService.buscar(vendaId));

	}

	@PostMapping
	@PreAuthorize("hasAuthority('CLIENTE')")
	public VendaModel adicionar(@Valid @RequestBody Venda venda) {
		return vendaMapper.toModel(vendaService.salvar(venda));

	}

	@PutMapping("/{vendaId}/receber")
	@PreAuthorize("hasAuthority('CLIENTE')")
	@ResponseStatus(code = HttpStatus.OK)
	public VendaModel atualizarStatus(@PathVariable Long vendaId) {
		return vendaMapper.toModel(vendaService.atualizarStatusEntregaRecebido(vendaId));
		
	}

}

package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.api.mapper.CompraMapper;
import com.gft.loja.api.model.CompraModel;
import com.gft.loja.domain.model.Compra;
import com.gft.loja.domain.service.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

	@Autowired
	private CompraService compraService;

	@Autowired
	private CompraMapper compraMapper;

	@GetMapping
	@PreAuthorize("hasAuthority('LOJA')")
	public List<CompraModel> listar() {
		return compraMapper.toCollectionModel(compraService.listar());
	}

	@GetMapping("/fornecedor/{fornecedorId}")
	@PreAuthorize("hasAuthority('LOJA')")
	public List<CompraModel> listar(@PathVariable Long fornecedorId) {
		return compraMapper.toCollectionModel(compraService.listarComFitroCodFornecedor(fornecedorId));
	}

	@GetMapping("/{compraId}")
	@PreAuthorize("hasAuthority('LOJA')")
	public CompraModel buscar(@PathVariable Long compraId) {
		return compraMapper.toModel(compraService.buscar(compraId));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('LOJA')")
	public CompraModel adicionar(@Valid @RequestBody Compra compra) {
		return compraMapper.toModel(compraService.salvar(compra));

	}
}

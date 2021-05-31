package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public List<VendaModel> listar() {
		return vendaMapper.toCollectionModel(vendaService.listar());
	}

	@GetMapping("/{vendaId}")
	public VendaModel buscar(@PathVariable Long vendaId) {

		return vendaMapper.toModel(vendaService.buscar(vendaId));

	}

	@PostMapping
	public VendaModel adicionar(@Valid @RequestBody Venda venda) {
		return vendaMapper.toModel(vendaService.salvar(venda));

	}

	@PutMapping("/{vendaId}/receber")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VendaModel atualizarStatus(@PathVariable Long vendaId) {
		return vendaMapper.toModel(vendaService.atualizarStatusEntregaRecebido(vendaId));
		
	}

}

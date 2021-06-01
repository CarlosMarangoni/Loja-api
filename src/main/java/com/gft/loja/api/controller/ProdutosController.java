package com.gft.loja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.api.mapper.EstoqueMapper;
import com.gft.loja.api.mapper.ProdutoMapper;
import com.gft.loja.api.model.EstoqueResumoModel;
import com.gft.loja.api.model.ProdutoModel;
import com.gft.loja.domain.model.Produto;
import com.gft.loja.domain.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoMapper produtoMapper;
	
	@Autowired
	private EstoqueMapper estoqueMapper;
	
	@GetMapping("/todos")
	public List<ProdutoModel> listarTodos(){
		return produtoMapper.toCollectionModel(produtoService.listarTodos());
	}
	
	@GetMapping
	public List<EstoqueResumoModel> listar(){
		return estoqueMapper.toCollectionModelResumo(produtoService.listar());
	}
	
	@GetMapping("/asc")
	public List<EstoqueResumoModel> listarAsc(){
		return estoqueMapper.toCollectionModelResumo(produtoService.listarAsc());
	}
	
	@GetMapping("/desc")
	public List<EstoqueResumoModel> listarDesc(){
		return estoqueMapper.toCollectionModelResumo(produtoService.listarDesc());
	}
	
	@GetMapping("/nome/{produtoDesc}")
	public List<EstoqueResumoModel> listarComFiltro(@PathVariable String produtoDesc){
		return estoqueMapper.toCollectionModelResumo(produtoService.listarComFiltro(produtoDesc));
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long produtoId) {
		return produtoMapper.toModel(produtoService.buscar(produtoId));
		 
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutoModel adicionar(@Valid @RequestBody Produto produto){
		return produtoMapper.toModel(produtoService.salvar(produto));
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoModel editar (@Valid @RequestBody Produto produto,@PathVariable Long produtoId){
		return produtoMapper.toModel(produtoService.atualizar(produtoId,produto));
		
	}
	
	@DeleteMapping("/{produtoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long produtoId){
		produtoService.excluir(produtoId);
	}
}

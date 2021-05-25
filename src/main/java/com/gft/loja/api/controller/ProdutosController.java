package com.gft.loja.api.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

import com.gft.loja.domain.model.Produto;
import com.gft.loja.domain.repository.ProdutoRepository;
import com.gft.loja.domain.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> listar(){
		return produtoService.listar();
	}
	
	@GetMapping("/{produtoId}")
	public Produto buscar(@PathVariable Long produtoId) {
		Produto produto = produtoService.buscar(produtoId);
		return produto;
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@Valid @RequestBody Produto produto){
		Produto produtoSalvo = produtoService.salvar(produto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@PutMapping("/{produtoId}")
	public ResponseEntity<?> editar (@Valid @RequestBody Produto produto,@PathVariable Long produtoId){
		Produto produtoBuscado = produtoService.buscar(produtoId);
		BeanUtils.copyProperties(produto, produtoBuscado,"id");
		produtoService.salvar(produtoBuscado);
		return ResponseEntity.ok(produtoBuscado);
	}
	
	@DeleteMapping("/{produtoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long produtoId){
		produtoService.excluir(produtoId);
	}
}

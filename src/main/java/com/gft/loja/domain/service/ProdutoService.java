package com.gft.loja.domain.service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.EntidadeEmUsoException;
import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.model.Produto;
import com.gft.loja.domain.repository.EstoqueRepository;
import com.gft.loja.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private EstoqueService estoqueService;

	public List<Estoque> listar() {
		Collection<? extends GrantedAuthority> perfis = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("LOJA"))) {
			System.out.println("perfil loja");
			return estoqueRepository.findAll();
		}
		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("CLIENTE"))) {
			System.out.println("perfil cliente");
			return estoqueRepository.findByQuantidadeGreaterThanAndValorVendaIsNotNull(0);
		}
		return null;

	}

	public List<Estoque> listarAsc() {
		return estoqueRepository.findByQuantidadeGreaterThanAndValorVendaIsNotNullOrderByProdutoDescricaoAsc(0);
	}

	public List<Estoque> listarDesc() {
		return estoqueRepository.findByQuantidadeGreaterThanAndValorVendaIsNotNullOrderByProdutoDescricaoDesc(0);
	}

	public List<Estoque> listarComFiltro(String produtoDesc) {
		return estoqueRepository.findByQuantidadeGreaterThanAndValorVendaIsNotNullAndProdutoDescricaoContaining(0,
				produtoDesc);
	}

	public Produto buscar(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				"Produto n??o encontrado. Fa??a o preenchimento correto e tente novamente"));

	}

	@Transactional
	public Produto salvar(Produto produto) {
		boolean produtoExiste = produtoRepository.findByDescricao(produto.getDescricao()).stream()
				.anyMatch(produtoExistente -> !produtoExistente.equals(produto));

		if (produtoExiste) {
			throw new EntidadeEmUsoException("J?? existe um produto cadastrado com esta descri????o.");
		}

		Produto produtoSalvo = produtoRepository.save(produto);
		Estoque estoque = new Estoque(produtoSalvo.getId(), produto, 0, null); // No momento que cria o
																				// produto, cria um estoque
																				// com quantidade e valor 0
		estoqueRepository.save(estoque);

		return produtoSalvo;
	}

	public void excluir(Long produtoId) {
		try {
			Estoque produtoEstoque = estoqueService.buscar(produtoId);
			if (produtoEstoque.getQuantidade() == 0) {

				estoqueRepository.deleteById(produtoId);
				produtoRepository.deleteById(produtoId);
			} else {
				throw new EntidadeEmUsoException(
						"O produto possui uma quantidade no estoque. Favor excluir o produto do estoque para prosseguir.");
			}

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Produto est?? em uso por outra entidade.");
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException();
		}

	}

	public Produto atualizar(Long produtoId, Produto produto) {
		Produto produtoBuscado = buscar(produtoId);
		BeanUtils.copyProperties(produto, produtoBuscado, "id");
		salvar(produtoBuscado);
		return produtoBuscado;
	}

}

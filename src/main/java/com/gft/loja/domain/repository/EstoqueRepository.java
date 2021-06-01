package com.gft.loja.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.model.Produto;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	List<Estoque> findByQuantidadeGreaterThan(double quantidade);

	List<Estoque> findByQuantidadeGreaterThanAndValorVendaIsNotNull(double quantidade);

	List<Estoque> findByQuantidadeGreaterThanAndValorVendaIsNotNullOrderByProdutoDescricaoAsc(double quantidade);

	List<Estoque> findByQuantidadeGreaterThanAndValorVendaIsNotNullOrderByProdutoDescricaoDesc(double quantidade);

	List<Estoque> findByQuantidadeGreaterThanAndValorVendaIsNotNullAndProdutoDescricaoContaining(double quantidade,
			String produtoDesc);
}

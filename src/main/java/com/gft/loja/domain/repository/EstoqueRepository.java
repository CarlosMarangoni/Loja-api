package com.gft.loja.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.model.Produto;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	
}

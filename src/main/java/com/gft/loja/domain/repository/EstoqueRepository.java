package com.gft.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.loja.domain.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}

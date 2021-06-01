package com.gft.loja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.loja.domain.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {

	List<Compra> findByFornecedorId(Long fornecedorId);

}

package com.gft.loja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.loja.domain.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	List<Compra> findByFornecedorId(Long fornecedorId);

}

package com.gft.loja.domain.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.loja.domain.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{


	List<Fornecedor> findByCnpj(String nome);


}

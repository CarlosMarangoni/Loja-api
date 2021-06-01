package com.gft.loja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.loja.domain.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{


	List<Fornecedor> findByCnpj(String nome);

	List<Fornecedor> findByNomeContaining(String fornecedorDesc);

	List<Fornecedor> findByCnpjContaining(String fornecedorCnpj);


}

package com.gft.loja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.loja.domain.model.Venda;
import com.gft.loja.domain.model.enumeration.StatusVenda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	List<Venda> findByClienteId(Long clienteId);

	List<Venda> findByStatusVenda(StatusVenda statusVenda);

}

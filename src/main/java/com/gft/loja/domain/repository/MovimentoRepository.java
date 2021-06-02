package com.gft.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.loja.domain.model.Movimento;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {


}

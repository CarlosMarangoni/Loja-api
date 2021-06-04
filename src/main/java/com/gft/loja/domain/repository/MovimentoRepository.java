package com.gft.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.loja.domain.model.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

}

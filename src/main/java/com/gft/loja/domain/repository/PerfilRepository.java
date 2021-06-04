package com.gft.loja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.loja.domain.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{
	
	List<Perfil> findByDescricao(String descricao);

}

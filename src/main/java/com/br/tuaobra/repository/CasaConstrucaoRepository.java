package com.br.tuaobra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.tuaobra.model.CasaConstrucao;

@Repository
public interface CasaConstrucaoRepository extends JpaRepository<CasaConstrucao, Long>{
	
	Optional<CasaConstrucao> findByEmail(String email);
	
}

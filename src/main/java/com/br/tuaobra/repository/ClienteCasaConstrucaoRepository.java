package com.br.tuaobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.tuaobra.model.ClienteCasaConstrucao;

@Repository
public interface ClienteCasaConstrucaoRepository extends JpaRepository<ClienteCasaConstrucao, Long>{

}

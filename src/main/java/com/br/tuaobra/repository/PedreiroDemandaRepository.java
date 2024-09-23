package com.br.tuaobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.tuaobra.model.PedreiroDemanda;

@Repository
public interface PedreiroDemandaRepository extends JpaRepository<PedreiroDemanda, Long>{

}

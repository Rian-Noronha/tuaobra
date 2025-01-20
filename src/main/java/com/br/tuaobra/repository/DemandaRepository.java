package com.br.tuaobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.tuaobra.model.Demanda;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long>{

}

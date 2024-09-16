package com.br.tuaobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.tuaobra.model.Pedreiro;

@Repository
public interface PedreiroRepository extends JpaRepository<Pedreiro, Long> {
}
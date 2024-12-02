package com.br.tuaobra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.tuaobra.model.Pedreiro;

@Repository
public interface PedreiroRepository extends JpaRepository<Pedreiro, Long> {
	
	Optional<Pedreiro> findByEmail(String email);
	Optional<Pedreiro> findByFirebaseUid(String firebaseUid);
	
}

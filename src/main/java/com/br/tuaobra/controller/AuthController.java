package com.br.tuaobra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.model.Pedreiro;
import com.br.tuaobra.service.ClienteService;
import com.br.tuaobra.service.PedreiroService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedreiroService pedreiroService;

	@PostMapping("/cliente")
	public ResponseEntity<Cliente> autenticarCliente(@RequestHeader("Authorization") String authorization) {
		String firebaseUid = extractUidFromToken(authorization.replace("Bearer ", ""));
		Cliente cliente = clienteService.autenticarCliente(firebaseUid);
		return ResponseEntity.ok(cliente);
	}
	
	
	
	@PostMapping("/pedreiro")
	public ResponseEntity<Pedreiro> autenticarPedreiro(@RequestHeader("Authorization") String authorization){
		String firebaseUid = extractUidFromToken(authorization.replace("Bearer ", ""));
		Pedreiro pedreiro = pedreiroService.autenticarPedreiro(firebaseUid);
		return ResponseEntity.ok(pedreiro);
	}

	private String extractUidFromToken(String token) {
		try {
			FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
			return decodedToken.getUid();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao decodificar o token: " + e.getMessage());
		}
	}

}

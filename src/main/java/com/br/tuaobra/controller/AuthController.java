package com.br.tuaobra.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.tuaobra.model.CasaConstrucao;
import com.br.tuaobra.model.Endereco;
import com.br.tuaobra.model.dto.CasaDTO;
import com.br.tuaobra.model.dto.CasaLoginDTO;
import com.br.tuaobra.security.TokenService;
import com.br.tuaobra.service.CasaConstrucaoService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	CasaConstrucaoService casaConstrucaoService;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody CasaLoginDTO casaLoginDTO) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(casaLoginDTO.email(), casaLoginDTO.senha());

		var auth = this.authenticationManager.authenticate(usernamePassword);
		String token = tokenService.generateToken((CasaConstrucao) auth.getPrincipal());
		return ResponseEntity.ok().body(Map.of("token", token));
	}

	@PostMapping("/register")
    public ResponseEntity<CasaConstrucao> register(@RequestBody CasaDTO casaDTO) {
		
		 Endereco endereco = new Endereco(
			        casaDTO.endereco().cep(),
			        casaDTO.endereco().nomeLugar(),
			        casaDTO.endereco().numero()
			    );
	
        CasaConstrucao casaConstrucao = new CasaConstrucao(
            casaDTO.nome(), 
            casaDTO.descricao(), 
            casaDTO.horario(), 
            casaDTO.frete(), 
            casaDTO.email(), 
            casaDTO.contatoWhatsApp(), 
            casaDTO.senha(), 
            endereco
        );

        casaConstrucaoService.salvarCasaConstrucao(casaConstrucao);
        return ResponseEntity.ok(casaConstrucao);
    }
	
	
	@PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody CasaDTO casaDTO) {
		 Endereco endereco = new Endereco(
			        casaDTO.endereco().cep(),
			        casaDTO.endereco().nomeLugar(),
			        casaDTO.endereco().numero()
			    );
        CasaConstrucao casaConstrucao = new CasaConstrucao(
            casaDTO.nome(), 
            casaDTO.descricao(), 
            casaDTO.horario(), 
            casaDTO.frete(), 
            casaDTO.email(), 
            casaDTO.contatoWhatsApp(), 
            casaDTO.senha(), 
            endereco
        );

        casaConstrucaoService.salvarCasaConstrucao(casaConstrucao);
        return ResponseEntity.ok("Casa de construção registrada com sucesso!");
    }

}

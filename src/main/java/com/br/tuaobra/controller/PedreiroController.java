package com.br.tuaobra.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.model.Pedreiro;
import com.br.tuaobra.service.PedreiroService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/")
public class PedreiroController {

	@Autowired
	private PedreiroService pedreiroService;
	
	@GetMapping("/pedreiros")
	@ResponseStatus(HttpStatus.OK)
	public List<Pedreiro> listar(){
		
		return this.pedreiroService.listarPedreiros();
		
	}
	
	
	@GetMapping("/clientesvinculadospedreirodemanda/email/{email}")
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listarClientesPedreiroDemanda(@PathVariable String email){
		return this.pedreiroService.listarClientesVinculadoPedreiroDemanda(email);
	}
	
	@GetMapping("/pedreiro/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Pedreiro buscarPeloId(@PathVariable Long id) {
		return this.pedreiroService.buscarPedreiro(id);
	}	
	
	@DeleteMapping("/pedreiro/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Long id) {
		this.pedreiroService.deletarPedreiro(id);
	}
	
	@PostMapping("/pedreiro")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody Pedreiro pedreiro) {
		this.pedreiroService.salvarPedreiro(pedreiro);
	}
	
	@PostMapping("/pedreiro/email/{email}/demanda/{demandaId}")
	@ResponseStatus(HttpStatus.OK)
	public void vincularDemandaPedreiro(@PathVariable String email, @PathVariable Long demandaId) {
		pedreiroService.vincularDemandaPedreiroPorEmail(email, demandaId);
	}
	
	@PutMapping("/pedreiro/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Pedreiro atualizar(@PathVariable Long id, @RequestBody Pedreiro pedreiro) {
		return this.pedreiroService.atualizarPedreiro(pedreiro);
	}
}

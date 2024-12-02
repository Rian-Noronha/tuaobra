package com.br.tuaobra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.tuaobra.model.Demanda;
import com.br.tuaobra.model.dto.DemandaClienteDTO;
import com.br.tuaobra.service.DemandaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class DemandaController {
	
	@Autowired
	public DemandaService demandaService;
	
	@GetMapping("/demandas")
	@ResponseStatus(HttpStatus.OK)
	public List<Demanda> listar(){
		return this.demandaService.listarDemandas();
	}
	
	@GetMapping("/demanda/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Demanda buscarPorId(@PathVariable Long id) {
		return this.demandaService.buscarDemanda(id);
	}
	
	@GetMapping("/demandacliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DemandaClienteDTO buscarDemandaClientePorId(@PathVariable Long id) {
		return this.demandaService.buscarDemandaCliente(id);
	}
	
	@DeleteMapping("/demanda/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Long id) {
		this.demandaService.deletarDemanda(id);
	}
	
	@PostMapping("/demanda")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody Demanda demanda) {
		this.demandaService.salvarDemanda(demanda);
	}
	
	@PostMapping("/demandacliente")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarDemandaCliente(@RequestBody Demanda demanda) {
		this.demandaService.salvarDemandaCliente(demanda);
	}
	
	@PutMapping("/demanda/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Demanda atualizar(@PathVariable Long id, @RequestBody Demanda demanda) {
		return this.demandaService.atualizarDemanda(demanda);
	}
	

}

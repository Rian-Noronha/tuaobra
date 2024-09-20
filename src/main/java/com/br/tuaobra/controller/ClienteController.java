package com.br.tuaobra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.service.ClienteService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listar(){
		return this.clienteService.listarClientes();
	}
	
	@GetMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente buscarPorId(@PathVariable Long id) {
		return this.clienteService.buscarCliente(id);
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Long id) {
		this.clienteService.deletarCliente(id);
	}
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody Cliente cliente) {
		this.clienteService.salvarCliente(cliente);
	}
	
	
	@PutMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		return this.clienteService.atualizarCliente(cliente);
	}
	

}

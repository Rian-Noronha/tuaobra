package com.br.tuaobra.controller;

import org.springframework.web.bind.annotation.RequestBody;
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

import com.br.tuaobra.model.CasaConstrucao;
import com.br.tuaobra.service.CasaConstrucaoService;


@RestController
@RequestMapping("/api/")
public class CasaConstrucaoController {
	
	@Autowired
	private CasaConstrucaoService casaConstrucaoService;
	
	@GetMapping("/casasconstrucao")
	@ResponseStatus(HttpStatus.OK)
	public List<CasaConstrucao> listar(){
		return this.casaConstrucaoService.listarCasasConstrucao();
	}
	
	@GetMapping("/casaconstrucao/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CasaConstrucao buscarPorId(@PathVariable Long id) {
		return this.casaConstrucaoService.buscarCasaConstrucao(id);
	}
	
	@DeleteMapping("/casaconstrucao/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Long id) {
		this.casaConstrucaoService.deletarCasaConstrucao(id);
	}
	
	@PostMapping("/casaconstrucao")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody CasaConstrucao casaConstrucao) {
		this.casaConstrucaoService.salvarCasaConstrucao(casaConstrucao);
	}
	
	@PutMapping("/casaconstrucao/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CasaConstrucao atualizar(@PathVariable Long id, @RequestBody CasaConstrucao casaConstrucao) {
		return this.casaConstrucaoService.atualizarCasaConstrucao(casaConstrucao);
	}
	

}

package com.br.tuaobra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.tuaobra.model.Especialidade;
import com.br.tuaobra.service.EspecialidadeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/")
public class EspecialidadeController {

	@Autowired
	public EspecialidadeService especialidadeService;

	@GetMapping("/especialidades")
	@ResponseStatus(HttpStatus.OK)
	public List<Especialidade> listar() {
		return this.especialidadeService.listarEspecialidades();
	}

	@GetMapping("/especialidade{id}")
	@ResponseStatus(HttpStatus.OK)
	public Especialidade buscarPorId(@PathVariable Long id) {
		return this.especialidadeService.buscarEspecialidade(id);
	}

	@DeleteMapping("/especialidade/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Long id) {
		this.especialidadeService.deletarEspecialidade(id);
	}

	@PostMapping("/especialidade")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody Especialidade especialidade) {
		this.especialidadeService.salvarEspecialidade(especialidade);
	}

	@PutMapping("/especialidade/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Especialidade atualizar(@PathVariable Long id, @RequestBody Especialidade especialidade) {
		return this.especialidadeService.atualizarEspecialidade(especialidade);
	}

}

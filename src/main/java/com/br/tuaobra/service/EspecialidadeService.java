package com.br.tuaobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.tuaobra.model.Especialidade;
import com.br.tuaobra.repository.EspecialidadeRepository;
import com.br.tuaobra.utils.exceptions.CamposNaoValidadosException;
import com.br.tuaobra.utils.exceptions.EspecialidadeNaoEncontradaException;
import com.br.tuaobra.utils.exceptions.EspecialidadeNulaException;

@Service
public class EspecialidadeService {

	@Autowired
	public EspecialidadeRepository especialidadeRepository;

	public void salvarEspecialidade(Especialidade especialidade) {
		if (especialidade == null) {
			throw new EspecialidadeNulaException("Especialidade está nula");
		}

		if (especialidade.getId() != null) {
			especialidade.setId(null);
		}

		if (checarCampos(especialidade)) {
			this.especialidadeRepository.save(especialidade);
		} else {
			throw new CamposNaoValidadosException("Campos da especialidade não validados");
		}

	}

	public List<Especialidade> listarEspecialidades() {
		return this.especialidadeRepository.findAll();
	}

	public Especialidade buscarEspecialidade(Long id) {
		return this.especialidadeRepository.findById(id)
				.orElseThrow(() -> new EspecialidadeNaoEncontradaException("Demanda não encontrada"));
	}

	public Especialidade atualizarEspecialidade(Especialidade especialidade) {
		if (especialidade.getId() == null || especialidade.getId() == 0L) {
			throw new RuntimeException("Por favor, insira um id da especialidade válido");
		}

		if (!this.especialidadeRepository.existsById(especialidade.getId())) {
			throw new RuntimeException("A especialidade com ID não existe: " + especialidade.getId());
		}

		if (checarCampos(especialidade)) {
			Especialidade espe = this.especialidadeRepository.findById(especialidade.getId()).orElseThrow(
					() -> new RuntimeException("Falha em buscar especialidade com id: " + especialidade.getId()));
			
			espe.setNome(especialidade.getNome());
			espe.setPedreiros(especialidade.getPedreiros());
			
			return this.especialidadeRepository.save(espe);
		}else {
			throw new CamposNaoValidadosException("Por favor, preencha todos os campos");
		}
	}

	public void deletarEspecialidade(Long id) {
		if (id == null || id == 0L) {
			throw new RuntimeException("Este id referente a especialidade não existe");
		}

		if (!this.especialidadeRepository.existsById(id)) {
			throw new RuntimeException("A especialidade com ID não existe: " + id);
		}

		this.especialidadeRepository.deleteById(id);
	}

	public boolean checarCampos(Especialidade especialidade) {
		boolean checados = true;

		if (!StringUtils.hasLength(especialidade.getNome())) {
			checados = false;
		}

		return checados;
	}

}

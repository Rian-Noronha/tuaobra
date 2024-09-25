package com.br.tuaobra.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.tuaobra.model.PedreiroDemanda;
import com.br.tuaobra.repository.PedreiroDemandaRepository;
import com.br.tuaobra.utils.exceptions.CamposNaoValidadosException;
import com.br.tuaobra.utils.exceptions.PedreiroDemandaNaoEncontradaException;
import com.br.tuaobra.utils.exceptions.PedreiroDemandaNulaException;
@Service
public class PedreiroDemandaService {

	@Autowired
	private PedreiroDemandaRepository pedreiroDemandaRepository;

	public void salvarPedreiroDemanda(PedreiroDemanda pedreiroDemanda) {
		if (pedreiroDemanda == null) {
			throw new PedreiroDemandaNulaException("PedreiroDemanda está nula");
		}

		if (pedreiroDemanda.getId() != null) {
			pedreiroDemanda.setId(null);
		}

		if (checarCampos(pedreiroDemanda)) {
			this.pedreiroDemandaRepository.save(pedreiroDemanda);
		} else {
			throw new CamposNaoValidadosException("Campos de PedreiroDemanda não validados");
		}
	}

	public List<PedreiroDemanda> listarPedreiroDemandas() {
		return this.pedreiroDemandaRepository.findAll();
	}

	public PedreiroDemanda buscarPedreiroDemanda(Long id) {
		return this.pedreiroDemandaRepository.findById(id)
				.orElseThrow(() -> new PedreiroDemandaNaoEncontradaException("PedreiroDemanda não encontrada"));
	}

	public void deletarPedreiroDemanda(Long id) {
		if (id == null || id == 0L) {
			throw new RuntimeException("Este id referente a PedreiroDemanda não existe");
		}

		if (!this.pedreiroDemandaRepository.existsById(id)) {
			throw new RuntimeException("O PedreiroDemanda com ID não existe: " + id);
		}

		this.pedreiroDemandaRepository.deleteById(id);
	}

	public PedreiroDemanda atualizarPedreiroDemanda(PedreiroDemanda pedreiroDemanda) {

		if (pedreiroDemanda.getId() == null || pedreiroDemanda.getId() == 0L) {
			throw new RuntimeException("Por favor, insira um id da PedreiroDemanda válido");
		}

		if (!this.pedreiroDemandaRepository.existsById(pedreiroDemanda.getId())) {
			throw new RuntimeException("A PedreiroDemanda com id " + pedreiroDemanda.getId() + " não existe");
		}

		if (checarCampos(pedreiroDemanda)) {
			PedreiroDemanda pedreDeman = this.pedreiroDemandaRepository.findById(pedreiroDemanda.getId()).orElseThrow(
					() -> new RuntimeException("Falha em buscar PedreiroDemanda com id " + pedreiroDemanda.getId()));

			pedreDeman.setPedreiro(pedreiroDemanda.getPedreiro());
			pedreDeman.setDemanda(pedreiroDemanda.getDemanda());
			pedreDeman.setCliente(pedreiroDemanda.getCliente());
			pedreDeman.setAvaliacao(pedreiroDemanda.getAvaliacao());

			return this.pedreiroDemandaRepository.save(pedreDeman);

		} else {
			throw new CamposNaoValidadosException("Por favor, preencha todos os campos");
		}

	}

	public boolean checarCampos(PedreiroDemanda pedreiroDemanda) {
		boolean checados = true;

		if (pedreiroDemanda.getPedreiro().getId() == null || pedreiroDemanda.getPedreiro().getId() == 0L
				|| pedreiroDemanda.getDemanda().getId() == null || pedreiroDemanda.getDemanda().getId() == 0L
				|| pedreiroDemanda.getAvaliacao() == 0) {
			checados = false;
		}
		return checados;
	}

}

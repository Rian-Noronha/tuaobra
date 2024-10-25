package com.br.tuaobra.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.model.Demanda;
import com.br.tuaobra.model.Pedreiro;
import com.br.tuaobra.repository.DemandaRepository;
import com.br.tuaobra.repository.PedreiroRepository;
import com.br.tuaobra.utils.exceptions.DemandaNaoEncontradaException;
import com.br.tuaobra.utils.exceptions.EmailJaExisteException;
import com.br.tuaobra.utils.exceptions.PedreiroNaoEncontradoException;

import jakarta.mail.internet.InternetAddress;

@Service
public class PedreiroService {

	@Autowired
	private PedreiroRepository pedreiroRepository;

	@Autowired
	private DemandaRepository demandaRepository;

	public void salvarPedreiro(Pedreiro pedreiro) {

		if (pedreiro == null) {
			throw new RuntimeException("Pedreiro está nulo");
		}

		if (pedreiro.getId() != null) {
			pedreiro.setId(null);
		}

		boolean camposChecados = checarCampos(pedreiro);
		validarEmail(pedreiro.getEmail());

		if (pedreiroRepository.findByEmail(pedreiro.getEmail()).isPresent()) {
			throw new EmailJaExisteException("E-mail já existe");
		}

		if (camposChecados) {
			this.pedreiroRepository.save(pedreiro);
		}

	}

	public void vincularDemandaPedreiroPorEmail(String email, Long demandaId) {
		Pedreiro pedreiro = pedreiroRepository.findByEmail(email).orElseThrow(
				() -> new PedreiroNaoEncontradoException("Pedreiro não encontrado com o e-mail: " + email));

		Demanda demanda = demandaRepository.findById(demandaId)
				.orElseThrow(() -> new DemandaNaoEncontradaException("Demanda não encontrada com id: " + demandaId));

		if (pedreiro.getDemandas().contains(demanda)) {
			throw new IllegalArgumentException("Demanda já vinculada ao pedreiro com e-mail: " + email);
		} else {
			pedreiro.getDemandas().add(demanda);
			pedreiroRepository.save(pedreiro);
		}

	}

	public List<Cliente> listarClientesVinculadoPedreiroDemanda(String emailPedreiro) {

		Pedreiro pedreiro = pedreiroRepository.findByEmail(emailPedreiro).orElseThrow(
				() -> new PedreiroNaoEncontradoException("Pedreiro não encontrado com o e-mail: " + emailPedreiro));
		Set<Cliente> clientesUnicos = new HashSet<>();

		for (Demanda d : pedreiro.getDemandas()) {
			clientesUnicos.add(d.getCliente());
		}

		return new ArrayList<>(clientesUnicos);
	}
	
	

	public List<Pedreiro> listarPedreiros() {
		return pedreiroRepository.findAll();
	}

	public Pedreiro buscarPedreiro(Long id) {
		return pedreiroRepository.findById(id)
				.orElseThrow(() -> new PedreiroNaoEncontradoException("Pedreiro não encontrado"));
	}

	public void deletarPedreiro(Long id) {
		if (id == null || id == 0L) {
			throw new RuntimeException("Este id referente a um pedreiro não existe");
		}

		if (!this.pedreiroRepository.existsById(id)) {
			throw new RuntimeException("O pedreiro com ID não existe: " + id);
		}

		this.pedreiroRepository.deleteById(id);
	}

	public Pedreiro atualizarPedreiro(Pedreiro pedreiro) {
		if (pedreiro.getId() == null || pedreiro.getId() == 0L) {
			throw new RuntimeException("Por favor insira um id de pedreiro válido");
		}

		if (!this.pedreiroRepository.existsById(pedreiro.getId())) {
			throw new RuntimeException("O pedreiro com id " + pedreiro.getId() + " não existe");
		}

		if (checarCampos(pedreiro)) {
			validarEmail(pedreiro.getEmail());
			Pedreiro pedre = this.pedreiroRepository.findByEmail(pedreiro.getEmail()).orElseThrow(
					() -> new RuntimeException("Falha em buscar pedreiro com e-mail: " + pedreiro.getEmail()));

			pedre.setUrlImagemPerfil(pedreiro.getUrlImagemPerfil());
			pedre.setDescricao(pedreiro.getDescricao());
			pedre.setNome(pedreiro.getNome());
			pedre.setContatoWhatsApp(pedreiro.getContatoWhatsApp());

			pedre.setEspecialidades(pedreiro.getEspecialidades());
			pedre.setEndereco(pedreiro.getEndereco());
			pedre.setDemandas(pedreiro.getDemandas());
			return this.pedreiroRepository.save(pedre);
		} else {
			throw new RuntimeException("Por favor, preencha todos os campos");
		}
	}

	private boolean checarCampos(Pedreiro pedreiro) {
		boolean checados = true;
		if (!StringUtils.hasLength(pedreiro.getNome()) || !StringUtils.hasLength(pedreiro.getDescricao())
				|| !StringUtils.hasLength(pedreiro.getContatoWhatsApp()) || !StringUtils.hasLength(pedreiro.getEmail())
				|| pedreiro.getEndereco() == null) {
			checados = false;
		}

		return checados;
	}

	private void validarEmail(String email) {
		try {
			InternetAddress enderecoEmail = new InternetAddress(email);
			enderecoEmail.validate();
		} catch (Exception e) {
			throw new RuntimeException("E-mail inválido");
		}
	}

}

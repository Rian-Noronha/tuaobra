package com.br.tuaobra.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.tuaobra.model.CasaConstrucao;
import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.model.Demanda;
import com.br.tuaobra.repository.CasaConstrucaoRepository;
import com.br.tuaobra.repository.ClienteRepository;
import com.br.tuaobra.repository.DemandaRepository;
import com.br.tuaobra.utils.exceptions.CamposNaoValidadosException;
import com.br.tuaobra.utils.exceptions.CasaConstrucaoNaoEncontradaException;
import com.br.tuaobra.utils.exceptions.CasaConstrucaoNulaException;
import com.br.tuaobra.utils.exceptions.ClienteNaoEncontradoException;
import com.br.tuaobra.utils.exceptions.DemandaNaoEncontradaException;
import com.br.tuaobra.utils.exceptions.EmailJaExisteException;
import jakarta.mail.internet.InternetAddress;

@Service
public class CasaConstrucaoService {

	@Autowired
	private CasaConstrucaoRepository casaConstrucaoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private DemandaRepository demandaRepository;

	public void salvarCasaConstrucao(CasaConstrucao casaConstrucao) {
		if (casaConstrucao == null) {
			throw new CasaConstrucaoNulaException("Casa construção está nula");
		}

		if (casaConstrucao.getId() != null) {
			casaConstrucao.setId(null);
		}

		validarEmail(casaConstrucao.getEmail());

		if (casaConstrucaoRepository.findByEmail(casaConstrucao.getEmail()).isPresent()) {
			throw new EmailJaExisteException("E-mail da casa de construção já existe");
		}

		if (checarCampos(casaConstrucao)) {
			this.casaConstrucaoRepository.save(casaConstrucao);
		} else {
			throw new CamposNaoValidadosException("Campos da casa de construção não foram validados");
		}
	}

	public void vincularCasaCliente(Long casaId, Long demandaId, String email) {
		CasaConstrucao casaConstrucao = this.casaConstrucaoRepository.findById(casaId)
				.orElseThrow(() -> new CasaConstrucaoNaoEncontradaException(
						"Casa de construção com id: " + casaId + " não encontrada"));

		Cliente cliente = this.clienteRepository.findByEmail(email).orElseThrow(
				() -> new ClienteNaoEncontradoException("Cliente com email: " + email + " não encontrado"));

		Demanda demanda = this.demandaRepository.findById(demandaId)
				.orElseThrow(() -> new DemandaNaoEncontradaException("Demanda não encontrada"));

		if (casaConstrucao.getClientes().contains(cliente)) {
			throw new IllegalArgumentException("Cliente já vinculado à casa com e-mail: " + email);
		} else {
			if (demanda.getUrlListaOrcamento() != null) {
				casaConstrucao.getClientes().add(cliente);
				this.casaConstrucaoRepository.save(casaConstrucao);
			} else {
				throw new IllegalArgumentException("Lista de orçamento inválida");
			}
		}

	}

	public List<Cliente> listarClientesVinculados(String emailCasa) {
		return this.casaConstrucaoRepository.findByEmail(emailCasa).map(CasaConstrucao::getClientes)
				.orElseThrow(() -> new CasaConstrucaoNaoEncontradaException(
						"Casa de construção com email: " + emailCasa + " não encontrada"));
	}

	public List<Demanda> listarDemandasClienteVinculadoCasa(String emailCliente) {
		if (emailCliente.isEmpty() || emailCliente == null) {
			throw new RuntimeException("Este email referente ao cliente não existe");
		}

		Cliente cliente = this.clienteRepository.findByEmail(emailCliente.trim().toLowerCase()).orElseThrow(
				() -> new ClienteNaoEncontradoException("Cliente com o email: " + emailCliente + " não encontrado"));

		return cliente.getDemandas();
	}

	public List<CasaConstrucao> listarCasasConstrucao() {
		return this.casaConstrucaoRepository.findAll();
	}

	public CasaConstrucao buscarCasaConstrucao(Long id) {
		return this.casaConstrucaoRepository.findById(id)
				.orElseThrow(() -> new CasaConstrucaoNaoEncontradaException("Casa construção não encontrada"));
	}

	public void deletarCasaConstrucao(Long id) {
		if (id == null || id == 0L) {
			throw new RuntimeException("Este id referente à casa de construção não existe");
		}

		if (!this.casaConstrucaoRepository.existsById(id)) {
			throw new RuntimeException("A casa de construção com ID não existe: " + id);
		}

		this.casaConstrucaoRepository.deleteById(id);
	}

	public CasaConstrucao atualizarCasaConstrucao(CasaConstrucao casaConstrucao) {
		if (casaConstrucao.getId() == null || casaConstrucao.getId() == 0L) {
			throw new RuntimeException("Por favor, insira um id da casa de construção válido");
		}

		if (!this.casaConstrucaoRepository.existsById(casaConstrucao.getId())) {
			throw new RuntimeException("A casa de construção com ID não existe: " + casaConstrucao.getId());
		}

		if (checarCampos(casaConstrucao)) {
			validarEmail(casaConstrucao.getEmail());
			CasaConstrucao casaConstru = this.casaConstrucaoRepository.findByEmail(casaConstrucao.getEmail())
					.orElseThrow(() -> new RuntimeException(
							"Falha em buscar casa de construção com e-mail " + casaConstrucao.getEmail()));

			casaConstru.setNome(casaConstrucao.getNome());
			casaConstru.setDescricao(casaConstrucao.getDescricao());
			casaConstru.setHorario(casaConstrucao.getHorario());
			casaConstru.setUrlImagemPerfil(casaConstrucao.getUrlImagemPerfil());
			casaConstru.setFrete(casaConstrucao.getFrete());
			casaConstru.setContatoWhatsApp(casaConstrucao.getContatoWhatsApp());
			casaConstru.setEndereco(casaConstrucao.getEndereco());
			casaConstru.setClientes(casaConstrucao.getClientes());

			return this.casaConstrucaoRepository.save(casaConstru);
		} else {
			throw new CamposNaoValidadosException("Por favor, preencha todos os campos");
		}
	}

	public boolean checarCampos(CasaConstrucao casaConstrucao) {
		boolean checados = true;

		if (!StringUtils.hasLength(casaConstrucao.getNome()) || !StringUtils.hasLength(casaConstrucao.getDescricao())
				|| !StringUtils.hasLength(casaConstrucao.getHorario())
				|| !StringUtils.hasLength(casaConstrucao.getUrlImagemPerfil())
				|| !StringUtils.hasLength(casaConstrucao.getFrete())
				|| !StringUtils.hasLength(casaConstrucao.getEmail())
				|| !StringUtils.hasLength(casaConstrucao.getContatoWhatsApp())
				|| casaConstrucao.getEndereco() == null) {
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

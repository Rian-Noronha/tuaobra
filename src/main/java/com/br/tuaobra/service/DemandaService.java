package com.br.tuaobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.model.Demanda;
import com.br.tuaobra.model.dto.DemandaClienteDTO;
import com.br.tuaobra.repository.ClienteRepository;
import com.br.tuaobra.repository.DemandaRepository;
import com.br.tuaobra.utils.exceptions.CamposNaoValidadosException;
import com.br.tuaobra.utils.exceptions.ClienteNaoEncontradoException;
import com.br.tuaobra.utils.exceptions.DemandaNaoEncontradaException;
import com.br.tuaobra.utils.exceptions.DemandaNulaException;

@Service
public class DemandaService {

	@Autowired
	private DemandaRepository demandaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public void salvarDemanda(Demanda demanda) {
		if (demanda == null) {
			throw new DemandaNulaException("Demanda está nula");
		}

		if (demanda.getId() != null) {
			demanda.setId(null);
		}
		

		if (checarCampos(demanda)) {
			
			Cliente clienteExistente = clienteRepository.findById(demanda.getCliente().getId())
					.orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
			
			demanda.setCliente(clienteExistente);
			
			this.demandaRepository.save(demanda);
			
		} else {
			throw new CamposNaoValidadosException("Campos da demanda não foram validados");
		}
	}

	public List<Demanda> listarDemandas() {
		return this.demandaRepository.findAll();
	}
	
	

	public Demanda buscarDemanda(Long id) {
		return this.demandaRepository.findById(id)
				.orElseThrow(() -> new DemandaNaoEncontradaException("Demanda não encontrada"));
	}
	
	public DemandaClienteDTO buscarDemandaCliente(Long id) {
		Demanda demanda = this.demandaRepository.findById(id)
				.orElseThrow(() -> new DemandaNaoEncontradaException("Demanda não encontrada"));
		Cliente cliente = this.clienteRepository.findById(demanda.getCliente().getId())
				.orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
		
		DemandaClienteDTO demandaClienteDTO = new DemandaClienteDTO();
		demandaClienteDTO.setDetalhes(demanda.getDetalhes());
		demandaClienteDTO.setDataPublicacao(demanda.getDataPublicacao());
		demandaClienteDTO.setTrabalhoASerFeito(demanda.getTrabalhoSerFeito());
		demandaClienteDTO.setCep(demanda.getEndereco().getCep());
		demandaClienteDTO.setNomeLugar(demanda.getEndereco().getNomeLugar());
		demandaClienteDTO.setNumero(demanda.getEndereco().getNumero());
		demandaClienteDTO.setNomeCliente(cliente.getNome());
		demandaClienteDTO.setEmailCliente(cliente.getEmail());
		demandaClienteDTO.setContatoCliente(cliente.getContatoWhatsApp());
		
		return demandaClienteDTO;
		
	}

	public void deletarDemanda(Long id) {
		if (id == null || id == 0L) {
			throw new RuntimeException("Este id referente a demanda não existe");
		}

		if (!this.demandaRepository.existsById(id)) {
			throw new RuntimeException("A demanda com ID não existe: " + id);
		}

		this.demandaRepository.deleteById(id);
	}

	public Demanda atualizarDemanda(Demanda demanda) {
		if (demanda.getId() == null || demanda.getId() == 0L) {
			throw new RuntimeException("Por favor, insira um id da demanda válido");
		}

		if (!this.demandaRepository.existsById(demanda.getId())) {
			throw new RuntimeException("A demanda com ID não existe: " + demanda.getId());
		}

		if (checarCampos(demanda)) {
			Demanda deman = this.demandaRepository.findById(demanda.getId())
					.orElseThrow(() -> new RuntimeException("Falha em buscar demanda com id " + demanda.getId()));

			deman.setDetalhes(demanda.getDetalhes());
			deman.setTrabalhoSerFeito(demanda.getTrabalhoSerFeito());
			deman.setEndereco(demanda.getEndereco());
			deman.setDataPublicacao(demanda.getDataPublicacao());
			deman.setCliente(demanda.getCliente());

			return this.demandaRepository.save(deman);

		} else {
			throw new CamposNaoValidadosException("Por favor, preencha todos os campos");
		}
	}

	public boolean checarCampos(Demanda demanda) {
		boolean checados = true;

		if (!StringUtils.hasLength(demanda.getDetalhes()) || !StringUtils.hasLength(demanda.getTrabalhoSerFeito())
				|| demanda.getDataPublicacao() == null
				|| demanda.getCliente() == null
				|| demanda.getEndereco() == null) {
			checados = false;
		}

		return checados;

	}

}

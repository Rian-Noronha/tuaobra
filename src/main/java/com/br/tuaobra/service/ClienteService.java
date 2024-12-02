package com.br.tuaobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.model.Demanda;
import com.br.tuaobra.repository.ClienteRepository;
import com.br.tuaobra.utils.exceptions.CamposNaoValidadosException;
import com.br.tuaobra.utils.exceptions.ClienteNaoEncontradoException;
import com.br.tuaobra.utils.exceptions.ClienteNuloException;
import com.br.tuaobra.utils.exceptions.EmailJaExisteException;

import jakarta.mail.internet.InternetAddress;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void salvarCliente(Cliente cliente) {
		if (cliente == null) {
			throw new ClienteNuloException("Cliente está nulo");
		}

		if (cliente.getId() != null) {
			cliente.setId(null);
		}

		validarEmail(cliente.getEmail());

		if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
			throw new EmailJaExisteException("E-mail do cliente já existe");
		}

		if (checarCampos(cliente)) {
			this.clienteRepository.save(cliente);
		} else {
			throw new CamposNaoValidadosException("Campos do cliente não foram validados");
		}

	}

	public List<Cliente> listarClientes() {
		return this.clienteRepository.findAll();
	}

	public List<Demanda> listarDemandasCliente(String email) {
		if (email.isEmpty() || email == null) {
			throw new RuntimeException("Este email referente ao cliente não existe");
		}

		Cliente cliente = this.clienteRepository.findByEmail(email.trim().toLowerCase()).orElseThrow(
				() -> new ClienteNaoEncontradoException("Cliente com o email: " + email + " não encontrado"));

		return cliente.getDemandas();
	}

	public Cliente buscarCliente(Long id) {
		return this.clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
	}

	public void deletarCliente(Long id) {
		if (id == null || id == 0L) {
			throw new RuntimeException("Este id referente ao cliente não existe");
		}

		if (!this.clienteRepository.existsById(id)) {
			throw new RuntimeException("O cliente com ID não existe: " + id);
		}

		this.clienteRepository.deleteById(id);
	}

	public Cliente atualizarCliente(Cliente cliente) {
		if (cliente.getId() == null || cliente.getId() == 0L) {
			throw new RuntimeException("Por favor, insira um id de cliente válido");
		}

		if (!this.clienteRepository.existsById(cliente.getId())) {
			throw new RuntimeException("O cliente com id " + cliente.getId() + " não existe");
		}

		if (checarCampos(cliente)) {
			validarEmail(cliente.getEmail());
			Cliente client = this.clienteRepository.findByEmail(cliente.getEmail()).orElseThrow(
					() -> new RuntimeException("Falha em buscar cliente com e-mail " + cliente.getEmail()));
			client.setNome(cliente.getNome());
			client.setUrlImagemPerfil(cliente.getUrlImagemPerfil());
			client.setContatoWhatsApp(cliente.getContatoWhatsApp());
			client.setEndereco(cliente.getEndereco());
			client.setDemandas(cliente.getDemandas());
			client.setCasasConstrucao(cliente.getCasasConstrucao());

			return this.clienteRepository.save(client);
		} else {
			throw new CamposNaoValidadosException("Por favor, preencha todos os campos");
		}
	}

	public boolean checarCampos(Cliente cliente) {
		boolean checados = true;

		if (!StringUtils.hasLength(cliente.getNome()) || !StringUtils.hasLength(cliente.getEmail())) {
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

	public Cliente autenticarCliente(String firebaseUid) {
	    return clienteRepository.findByFirebaseUid(firebaseUid)
	        .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com UID não encontrado: " + firebaseUid));
	}

}

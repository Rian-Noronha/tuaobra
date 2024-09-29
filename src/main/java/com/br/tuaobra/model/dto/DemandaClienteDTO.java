package com.br.tuaobra.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandaClienteDTO {
	
	private Long id;
	private String detalhes;
	private LocalDateTime dataPublicacao;
	private String trabalhoASerFeito;
	private String cep;
	private String nomeCliente;
	private String emailCliente;
	private String contatoCliente;
	
}

package com.br.tuaobra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cep;
	private String nomeLugar;
	private String numero;
	
	
	
	
	public Endereco(String cep, String nomeLugar, String numero) {
		super();
		this.cep = cep;
		this.nomeLugar = nomeLugar;
		this.numero = numero;
	}
	
	

}

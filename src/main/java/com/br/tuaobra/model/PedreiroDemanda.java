package com.br.tuaobra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedreiroDemanda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pedreiro_id")
	private Pedreiro pedreiro;

	@ManyToOne
	@JoinColumn(name = "demanda_id")
	private Demanda demanda;
	
	@ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; 

	private double avaliacao;
}

package com.br.tuaobra.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Demanda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String detalhes;
	private String trabalhoSerFeito;
	private String cepOndeSera;
	private LocalDateTime dataPublicacao;

	@OneToMany(mappedBy = "demanda", cascade = CascadeType.ALL)
	private List<PedreiroDemanda> pedreirosDemanda;

	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

}

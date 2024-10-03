package com.br.tuaobra.model;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

	@ToStringExclude
	@ManyToMany(mappedBy = "demandas")
	private List<Pedreiro> pedreiros;
	
	@ToStringExclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Cliente cliente;

}

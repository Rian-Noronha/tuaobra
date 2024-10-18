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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	private LocalDateTime dataPublicacao;
	private String urlListaOrcamento;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;
	
	@ToStringExclude
	@ManyToMany(mappedBy = "demandas")
	private List<Pedreiro> pedreiros;
	
	@ToStringExclude
	@ManyToOne
	@JsonBackReference
	private Cliente cliente;

}

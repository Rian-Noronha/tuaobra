package com.br.tuaobra.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedreiro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String urlImagemPerfil;
	private String descricao;
	private String nome;
	private String contatoWhatsApp;
	private String email;

	@ManyToMany
	@JoinTable(name = "pedreiro_especialidade", joinColumns = @JoinColumn(name = "pedreiro_id"), inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
	private List<Especialidade> especialidades;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;

	@ManyToMany
	@JoinTable(
			name = "pedreiro_demanda",
			joinColumns = @JoinColumn(name = "pedreiro_id"),
			inverseJoinColumns = @JoinColumn(name = "demanda_id"))
	private List<Demanda> demandas;

}

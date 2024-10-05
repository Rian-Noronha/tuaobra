package com.br.tuaobra.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String email;
	private String urlImagemPerfil;
	private String urlListaOrcamento;
	private String contatoWhatsApp;
	private String senha;
	
	@ToStringExclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Demanda> demandas;

	@ManyToMany
	@JoinTable(
			name = "cliente_casa_construcao",
			joinColumns = @JoinColumn(name = "cliente_id"),
			inverseJoinColumns = @JoinColumn(name = "casa_construcao_id"))
	private List<CasaConstrucao> casasConstrucao;

}

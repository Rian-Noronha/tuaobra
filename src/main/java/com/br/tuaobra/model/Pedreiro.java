package com.br.tuaobra.model;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Pedreiro implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String urlImagemPerfil;
	private String descricao;
	private String nome;
	private String contatoWhatsApp;
	private String email;
	@JsonIgnore
	private String senha;

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
	@JsonIgnore
	private List<Demanda> demandas;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_PEDREIRO"));
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return this.senha;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return this.email;
	}
}

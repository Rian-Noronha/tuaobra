package com.br.tuaobra.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasaConstrucao implements UserDetails {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private String horario;
    private String urlImagemPerfil;
    private String frete;
    private String email;
    private String contatoWhatsApp;
    private String senha;
    
    public CasaConstrucao(String nome, String descricao, String horario,  String frete,
			String email, String contatoWhatsApp, String senha, Endereco endereco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.horario = horario;
		this.frete = frete;
		this.email = email;
		this.contatoWhatsApp = contatoWhatsApp;
		this.senha = senha;
		this.endereco = endereco;
	}
    
 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @ManyToMany(mappedBy = "casasConstrucao")
    private List<Cliente> clientes;
    
    @ManyToMany(mappedBy = "casasConstrucao")
    private List<Demanda> demandas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

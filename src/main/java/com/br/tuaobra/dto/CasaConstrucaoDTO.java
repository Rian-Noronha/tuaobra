package com.br.tuaobra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

    public class CasaConstrucaoDTO {
        private Long id;
        private String nome;
        private String descricao;
        private String horario;
        private double avaliacao;
        private String urlImagemPerfil;
        private String frete;
        private String email;
        private String contatoWhatsApp;
        //private Endereco endereco;

    }

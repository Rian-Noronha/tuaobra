package com.br.tuaobra.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.br.tuaobra.model.Cliente;
import com.br.tuaobra.model.Pedreiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandaDTO {
    
    private Long id;
    private String detalhes;
    private String trabalhoSerFeito;
    private String cepOndeSera;
    private LocalDateTime dataPublicacao;
    private List<Pedreiro> pedreiros;
    private Cliente cliente;
}

package br.com.company.fks.destinacao.dominio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class FormaDeIncorporacaoPermitidaDTO implements Serializable{
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String descricao;
}

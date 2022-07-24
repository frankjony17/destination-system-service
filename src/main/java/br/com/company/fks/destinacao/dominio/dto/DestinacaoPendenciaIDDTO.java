package br.com.company.fks.destinacao.dominio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class DestinacaoPendenciaIDDTO implements Serializable{
    @Getter
    @Setter
    @JsonIgnore
    private DestinacaoDTO destinacao;
    @Getter
    @Setter
    private PendenciaDTO pendencia;

}

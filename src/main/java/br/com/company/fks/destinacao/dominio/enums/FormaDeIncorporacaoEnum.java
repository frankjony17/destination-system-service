package br.com.company.fks.destinacao.dominio.enums;

import lombok.Getter;
import java.io.Serializable;

@Getter
public enum FormaDeIncorporacaoEnum implements Serializable{

    ORIGINALMENTE_DA_UNIAO(1L, "Originalmente da União"),
    POR_AQUISICAO(2L, "Por Aquisição"),
    POR_FRACIONAMENTO(3L, "Por Fracionamento/parcelamento"),
    POR_FUSAO(4L, "Por Fusão/Unificação"),
    IMOVEIS_DE_TERCEIROS(5L, "Imóveis de Terceiros");

    private Long codigo;
    private String descricao;

    FormaDeIncorporacaoEnum(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static FormaDeIncorporacaoEnum getByCodigo(Long codigo) {
        FormaDeIncorporacaoEnum formaDeIncorporacaoEnum = null;
        for (FormaDeIncorporacaoEnum value : values()) {
            if (value.getCodigo().equals(codigo)) {
                formaDeIncorporacaoEnum = value;
                break;
            }
        }
        return formaDeIncorporacaoEnum;
    }


}

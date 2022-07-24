package br.com.company.fks.destinacao.dominio.enums;

import java.io.Serializable;

/**
 * Created by jonatas on 04/05/18.
 */
public enum VersaoDestinacaoEnum implements Serializable, BasicEnum {
    VERSAO1("Versão 1", 1),
    VERSAO2("Versão 2", 2),
    VERSAO3("Versão 3", 3),
    VERSAO4("Versão 4", 4),
    VERSAO5("Versão 5", 5),
    VERSAO6("Versão 6", 6),
    VERSAO7("Versão 7", 7),
    VERSAO8("Versão 8", 8),
    VERSAO9("Versão 9", 9),
    VERSAO10("Versão 10", 10);

    private String descricao;
    private Integer codigo;

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public Integer getCodigo() {
        return codigo;
    }

    VersaoDestinacaoEnum(String descricao, Integer codigo){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static VersaoDestinacaoEnum getByDescricao(String descricao){
        VersaoDestinacaoEnum versaoDestinacaoEnum = null;
        for(VersaoDestinacaoEnum desc : values()){
            if(desc.getDescricao().equalsIgnoreCase(descricao)){
                versaoDestinacaoEnum = desc;
                break;
            }
        }
        return versaoDestinacaoEnum;
    }
}

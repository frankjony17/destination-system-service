package br.com.company.fks.destinacao.dominio.enums;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 04/10/2016.
 */
public enum TipoAtoAutorizativoEnum implements Serializable, BasicEnum {

    PORTARIA(1, "Portaria"),
    RESOLUCAO(2, "Resolução"),
    LEI_DECRETO_LEI(3,"Lei/Decreto-lei"),
    DECRETO(4,"Decreto"),
    DESPACHO(5,"Despacho"),
    OUTRO(6, "Outro");

    private Integer codigo;
    private String descricao;

    TipoAtoAutorizativoEnum(Integer codigo, String descricao) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    /**
     * Método get do atributo descricao
     * @return
     */
    public String getDescricao(){
        return this.descricao;
    }

    /**
     * Método get do atributo codigo
     * @return
     */
    public Integer getCodigo() {
        return this.codigo;
    }

}

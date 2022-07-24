package br.com.company.fks.destinacao.dominio.enums;

import java.io.Serializable;

public enum AdotarEnderecoEnum implements Serializable, BasicEnum{

    IMOVEL(1,"Imóvel"),
    FISCAL(2,"Fiscal"),
    CADUNICO(3,"CADÙnico"),
    OUTRO(4,"Outro");

    private Integer codigo;

    private String descricao;

    AdotarEnderecoEnum(Integer codigo, String descricao){
        this.descricao = descricao;
        this.codigo = codigo;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public Integer getCodigo(){return this.codigo;}
}

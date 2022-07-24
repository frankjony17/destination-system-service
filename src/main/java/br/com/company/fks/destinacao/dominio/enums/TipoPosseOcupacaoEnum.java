package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by tawan-souza on 27/11/17.
 */
public enum TipoPosseOcupacaoEnum implements BasicEnum {

    INDIVIDUAL("Individual", 1),
    COLETIVO("Coletivo", 2),
    OCUPANTE_NAO_IDENTIFICADO("Ocupante não identificado", 3);

    private String descricao;
    private Integer codigo;

    TipoPosseOcupacaoEnum(String descricao, Integer codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public Integer getCodigo() {
        return this.codigo;
    }

}

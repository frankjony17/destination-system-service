package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by tawan-souza on 27/11/17.
 */
public enum TipoRepresentacaoEnum implements BasicEnum{

    FORMAL("Formal", 1),
    INFORMAL("Informal (não exige CNPJ)", 2),
    SEM_REPRESENTACAO("Sem representação", 3);

    private String descricao;
    private Integer codigo;

    TipoRepresentacaoEnum(String descricao, Integer codigo) {
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

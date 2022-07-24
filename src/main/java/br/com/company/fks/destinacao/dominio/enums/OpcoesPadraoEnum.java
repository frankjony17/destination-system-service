package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by tawan-souza on 28/11/17.
 */
public enum OpcoesPadraoEnum implements BasicEnum {

    SIM("Sim", 1),
    NAO("Não", 2),
    SEM_INFORMACAO("Sem informação", 3);

    private String descricao;
    private Integer codigo;

    OpcoesPadraoEnum(String descricao, Integer codigo) {
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

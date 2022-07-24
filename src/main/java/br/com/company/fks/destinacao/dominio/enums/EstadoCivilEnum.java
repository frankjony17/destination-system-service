package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by tawan-souza on 27/11/17.
 */
public enum EstadoCivilEnum implements BasicEnum {

    SOLTEIRO("Solteiro", 1),
    CASADO_UNIAO_ESTAVEL("Casado/União Estável", 2),
    DIVORCIADO("Divorciado", 3),
    VIUVO("Viúvo", 4);

    private String descricao;
    private Integer codigo;

    EstadoCivilEnum(String descricao, Integer codigo) {
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

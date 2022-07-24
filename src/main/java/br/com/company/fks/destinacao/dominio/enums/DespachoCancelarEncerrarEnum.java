package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by tawan-souza on 15/12/17.
 */
public enum DespachoCancelarEncerrarEnum implements BasicEnum {

    DE_ACORDO_COM_CANCELAMENTO_ENCERRAMENTO("De acordo com o cancelamento/encerramento", 1),
    INDEFERO_CANCELAMENTO_ENCERRAMENTO("Indefiro o cancelamento/encerramento", 2),
    RETORNAR_PARA_COMPLEMENTACAO("Retornar para complementação", 3);

    private String descricao;
    private Integer codigo;

    DespachoCancelarEncerrarEnum(String descricao, Integer codigo) {
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

package br.com.company.fks.destinacao.dominio.enums;

public enum DespachoEncerrarDestinacaoEnum implements BasicEnum {

    DE_ACORDO_COM_O_ENCERRAMENTO("De acordo com o encerramento", 1),
    INDEFERO_ENCERRAMENTO("Indefiro o encerramento", 2),
    RETORNAR_PARA_COMPLEMENTACAO("Retornar para complementação", 3);

    private String descricao;
    private Integer codigo;

    DespachoEncerrarDestinacaoEnum(String descricao, Integer codigo) {
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

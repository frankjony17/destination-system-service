package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by tawan-souza on 14/12/17.
 */
public enum MotivoCancelarEncerrarEnum implements BasicEnum {
    CANCELAMENTO_POR_ERRO("Cancelamento por erro", 1, Boolean.TRUE),
    CANCELAMENTO_POR_ANULACAO("Cancelamento por anulação", 2, Boolean.FALSE),
    ENCERRAMENTO_POR_NAO_CUMPRIMENTO_DE_ENCARGO("Encerramento por não cumprimento de encargo", 3, Boolean.FALSE),
    ENCERRAMENTO_POR_INTERESSE_PUBLICO("Encerramento por interesse público", 4, Boolean.FALSE),
    ENCERRAMENTO_POR_ACORDO_ENTRE_AS_PARTES("Encerramento por acordo entre as partes", 5, Boolean.FALSE),
    ENCERRAMENTO_POR_DEVOLUVAO("Encerramento por devolução", 6, Boolean.FALSE),
    ENCERRAMENTO_POR_VENCIMENTO_DO_CONTRATO_TERMO("Encerramento por vencimento do contrato/termo (automático pela rotina)", 7, Boolean.FALSE),
    ENCERRAMENTO_POR_REINTEGRACAO_DE_POSSE("Encerramento por reintegração de posse", 8, Boolean.TRUE),
    ENCERRAMENTO_POR_REGULARIZACAO("Encerramento por regularização", 9, Boolean.TRUE);

    private String descricao;
    private Integer codigo;
    private Boolean isPosseInformal;

    MotivoCancelarEncerrarEnum(String descricao, Integer codigo, Boolean isPosseInformal) {
        this.descricao = descricao;
        this.codigo = codigo;
        this.isPosseInformal = isPosseInformal;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public Integer getCodigo() {
        return this.codigo;
    }

    public Boolean getIsPosseInformal() {
        return this.isPosseInformal;
    }
}

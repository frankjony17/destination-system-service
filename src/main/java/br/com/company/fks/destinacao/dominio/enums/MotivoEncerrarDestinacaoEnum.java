package br.com.company.fks.destinacao.dominio.enums;

public enum MotivoEncerrarDestinacaoEnum implements BasicEnum {

    CONCLUSAO_DO_CONTRATO("Conclusão do Contrato", 1, Boolean.FALSE),
    QUEBRA_DE_CLAUSULA_DO_CONTRATO("Quebra de Cláusula do Contrato", 2, Boolean.FALSE),
    INTERESSE_DE_SERVICO_PUBLICO("Interesse de Serviço Público", 3, Boolean.FALSE),
    DEVOLUCAO_DO_IMOVEL("Devolução do Imóvel", 4, Boolean.FALSE),
    REMICAO("Remição", 5, Boolean.FALSE),
    ENCERRAMENTO_POR_REINTEGRACAO_DE_POSSE("Encerramento por Reintegração de Posse", 6, Boolean.TRUE),
    ENCERRAMENTO_POR_REGULARIZACAO("Encerramento por Regularização", 7, Boolean.TRUE);


    private String descricao;
    private Integer codigo;
    private Boolean isPosseInformal;

    MotivoEncerrarDestinacaoEnum(String descricao, Integer codigo, Boolean isPosseInformal) {
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

package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by raphael on 08/12/16.
 */
public enum DespachoEnum {

    ATENDE_AOS_REQUISITOS(1L),
    NAO_ATENDE_AOS_REQUISITOS(2L),
    RETORNAR_PENDENCIA_REQUERENTE(3L),
    CANCELAR_ERRO_DUPLICIDADE(4L),
    SOLICITAR_MANIFEST_AREA(5L),
    DE_ACORDO_AV_TECNICA(6L),
    RETORNAR_PARA_ANALISE_TECNICA(7L),
    ALTERAR_AVALIACAO(8L),
    APROVO_ADESAO(9L),
    APROVO_INDEFERIMENTO(10L),
    RETORNAR_SUPERINTENDENTE(11L),
    RETORNAR_PARA_CHEFIA(12L),
    DE_ACORDO_AV_TEC_CHEFIA(13L),
    RETORNAR_AVALIACAO_TEC_CHEFIA(14L),
    ALTERAR_AVALIACAO_TEC_CHEFIA(15L);

    private Long codigo;

    DespachoEnum(Long codigo){
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }
}

package br.com.company.fks.destinacao.dominio.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum TipoAquisicaoEnum implements Serializable{

    REVERSAO(1L, "Reversão"),
    COMPRA(2L, "Compra"),
    DACAO_EM_PAGAMENTO(3L, "Dação em pagamento"),
    DESAPROPRIACAO_AMIGAVEL(4L, "Desapropriação amigável"),
    DETERMINACAO_JUDICIAL(5L, "Determinação judicial"),
    HERANCA_JACENTE(6L, "Herança jacente"),
    PERMUTA(7L, "Permuta"),
    RECEBIMENTO_EM_DOACAO(8L, "Recebimento em Doação"),
    ARREMATACAO_JUDICIAL(10L, "Arrematação judicial"),
    REGISTRO_APOSSAMENTO(11L, "Registro por Apossamento Vintenário (RAV)"),
    USACAPIAO(12L, "Usucapião"),
    EXPROPRIACAO_COM_FUNDAMENTO(13L, "Expropriação com fundamento na Lei nº 8.257/1991"),
    ENCAMPACAO(14L, "Encampação"),
    ADJUDICACAO(15L, "Adjudicação"),
    DESAPROPRIACAO_JUDICIAL(16L, "Desapropriação judicial"),
    SUCESSAO_POR_ENTIDADES(9L, "Sucessão por Entidades da APF");

    private Long codigo;
    private String descricao;

    TipoAquisicaoEnum(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoAquisicaoEnum getByCodigo(Long codigo) {
        TipoAquisicaoEnum tipoAquisicaoEnum= null;
        for (TipoAquisicaoEnum value : values()) {
            if (value.getCodigo().equals(codigo)) {
                tipoAquisicaoEnum = value;
                break;
            }
        }
        return tipoAquisicaoEnum;
    }


}

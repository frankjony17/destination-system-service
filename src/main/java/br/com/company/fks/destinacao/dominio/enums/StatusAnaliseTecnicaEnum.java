package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by diego on 07/12/16.
 */
public enum StatusAnaliseTecnicaEnum implements BasicEnum {

    AGUARDANDO_ANALISE_TECNICA(1, "Aguardando Análise Técnica"),
    RASCUNHO(2, "Rascunho"),
    EM_ANALISE_TECNICA(3,"Em Análise Técnica"),
    AGUARDANDO_MANIFESTACAO_CHEFIA(4,"Aguardando Manifestação da Chefia"),
    AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE(5,"Aguardando Manifestação do Superintendente"),
    AGUARDANDO_MANIFESTACAO_SECRETARIO(6,"Aguardando Manifestação do Secretário"),
    ENVIADO_PUBLICACAO(7, "Enviado para publicação"),
    PUBLICADO(8, "Publicado"),
    AGUARDANDO_REQUERENTE(9, "Aguardando Requerente"),
    CANCELADO(10, "Cancelado"),
    INDEFERIDO(11, "Indeferido");

    private Integer codigo;
    private String descricao;

    /**
     * Método construtor da classe, inicializa os atributos
     * @param codigo
     * @param descricao
     */
    StatusAnaliseTecnicaEnum(Integer codigo, String descricao) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    /**
     * Método que retorna um StatusAnaliseTecnicaEnum por codigo
     * @param idCampo
     * @return
     */
    public static StatusAnaliseTecnicaEnum getPorCodigo(Integer idCampo) {
        for (StatusAnaliseTecnicaEnum tipo : StatusAnaliseTecnicaEnum.values()) {
            if(tipo.codigo.equals(idCampo)){
                return tipo;
            }
        }
        return null;
    }

    /**
     * Método get do atributo descricao
     * @return
     */
    public String getDescricao(){
        return this.descricao;
    }

    /**
     * Método get do atributo codigo
     * @return
     */
    public Integer getCodigo() {
        return this.codigo;
    }
}

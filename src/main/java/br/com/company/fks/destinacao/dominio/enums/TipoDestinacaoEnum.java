package br.com.company.fks.destinacao.dominio.enums;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 04/10/2016.
 */
public enum TipoDestinacaoEnum implements Serializable, BasicEnum {

    DOACAO(1, "Doação", "DESTINACAO_CONSULTAR_DESTINACAO_DOACAO", "DESTINACAOMANTERDOACAO"),
    VENDA(2, "Venda", "DESTINACAO_CONSULTAR_DESTINACAO_VENDA", "DESTINACAOMANTERVENDA"),
    POSSE_INFORMAL(3,"Posse Informal", "DESTINACAO_CONSULTAR_DESTINACAO_POSSE_INFORMAL", "DESTINACAOMANTERPOSSEINFORMAL"),
    CUEM(4,"Cuem", "DESTINACAO_CONSULTAR_DESTINACAO_CUEM", "DESTINACAOMANTERCUEM"),
    CDRU(5,"Cdru", "DESTINACAO_CONSULTAR_DESTINACAO_CDRU", "DESTINACAOMANTERCDRU"),
    CESSAO_GRATUITA(6, "Cessão Gratuita", "DESTINACAO_CONSULTAR_DESTINACAO_CESSAO_GRATUITA", "DESTINACAOMANTERCESSAOGRATUITA"),
    CESSAO_ONEROSA(7, "Cessão Onerosa/Em Condições Especiais", "DESTINACAO_CONSULTAR_DESTINACAO_CESSAO_ONEROSA", "DESTINACAOMANTERCESSAOONEROSA"),
    LOCACAO(9, "Locação", "", ""),
    TERMO_ENTREGA(10, "Termo Entrega", "DESTINACAO_CONSULTAR_DESTINACAO_TERMO_ENTREGA", "DESTINACAOMANTERTERMOENTREGA"),
    TRANSFERENCIA(11,"Transferência de Gestão/Titularidade", "DESTINACAO_CONSULTAR_DESTINACAO_TRANSFERENCIA", "DESTINACAOMANTERTRANSFERENCIA"),
    //alterado por conta da TS002 - Criar/Alterar Utilização Inicial do Imóvel
    //jonatas.sousa 06/2018
    SEM_UTILIZACAO(12, "Sem Uso Definido", "", ""),
    USO_PROPRIO(13, "Uso Próprio", "DESTINACAO_CONSULTAR_DESTINACAO_USO_PROPRIO", "DESTINACAOMANTERUSOPROPRIO"),
    PERMISSAO_USO_IMOVEL_FUNCIONAL(14,"Permissão de Uso de Imóvel Funcional","DESTINACAO_CONSULTAR_DESTINACAO_PERMISSAO_USO_IMOVEL_FUNCIONAL","DESTINACAOMANTERPERMISSAOUSOIMOVELFUNCIONAL");

    private Integer codigo;
    private String descricao;
    private String permissaoConsultar;
    private String permissaoCadastrarEditar;

    TipoDestinacaoEnum(Integer codigo, String descricao, String permissaoConsultar, String permissaoCadastrarEditar) {
        this.descricao = descricao;
        this.codigo = codigo;
        this.permissaoConsultar = permissaoConsultar;
        this.permissaoCadastrarEditar = permissaoCadastrarEditar;
    }

    /**
     * Método que retorna a descrição
     * @return
     */
    public String descricao(){
        return this.descricao;
    }

    /**
     * Método que retorna o código
     * @return
     */
    public Integer codigo() {
        return this.codigo;
    }

    /**
     * Método que retorna a permissaoConsultar
     * @return
     */
    public String permissaoConsultar() {
        return this.permissaoConsultar;
    }

    /**
     * Método que retorna a permissaoCadastrarEditar
     * @return
     */
    public String permissaoCadastrarEditar() {
        return this.permissaoCadastrarEditar;
    }

    /**
     * Método que retorna TipoDestinacaoEnum pelo código
     * @param idCampo
     * @return
     */

    public static TipoDestinacaoEnum getPorCodigo(Integer idCampo) {
        for (TipoDestinacaoEnum tipo : TipoDestinacaoEnum.values()) {
            if(tipo.codigo().equals(idCampo)){
                return tipo;
            }
        }
        return null;
    }

    public static TipoDestinacaoEnum getPorNome(String descricao) {
        for (TipoDestinacaoEnum tipo : TipoDestinacaoEnum.values()) {
            if(tipo.descricao.equals(descricao)){
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

    /**
     * Método get do atributo permissaoConsultar
     * @return
     */
    public String getPermissaoConsultar() {
        return permissaoConsultar;
    }

    /**
     * Método get do atributo permissaoCadastrarEditar
     * @return
     */
    public String getPermissaoCadastrarEditar() {
        return permissaoCadastrarEditar;
    }
}

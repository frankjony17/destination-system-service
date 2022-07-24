package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by diego on 29/11/16.
 */

public class AnaliseTecnicaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String justificativa;
    @Getter
    @Setter
    private Boolean documentoPendente;
    @Getter
    @Setter
    private String obsDocumentoPendente;
    @Getter
    @Setter
    private String informacaoComplementar;
    @Getter
    @Setter
    private String informacaoComplementarEspecifica;
    @Getter
    @Setter
    private List<ItemVerificacaoDTO> itensVerificacao;
    @Getter
    @Setter
    private List<ItemVerificacaoEspecificoDTO> itensVerificacaoEspecifica;
    @Getter
    @Setter
    private List<DespachoDTO> despachosTecnico;
    @Getter
    @Setter
    private List<DespachoDTO> despachosChefia;
    @Getter
    @Setter
    private List<DespachoDTO> despachosSuperintendente;
    @Getter
    @Setter
    private List<DespachoDTO> despachosSecretario;
    @Getter
    @Setter
    private DominioDTO statusAnaliseTecnica;
    @Getter
    @Setter
    private TipoDestinacaoDTO tipoDestinacao;
    @Getter
    @Setter
    private Long codFundamentoLegal;
    @Getter
    @Setter
    private List<DocumentoComplementarEspecificoDTO> documentosComplementaresEspecificos;
    @Getter
    @Setter
    private List<DocumentoComplementarDTO> documentosComplementares;
    @Getter
    @Setter
    private Long idRequerimento;
    @Getter
    @Setter
    private PublicacaoDTO publicacao;
    @Getter
    @Setter
    private List<DocumentoAnaliseDTO> documentosAnaliseObrigatorio;
    @Getter
    @Setter
    private List<DocumentoAnaliseDTO> documentosAnaliseComplementar;
    @Getter
    @Setter
    private Date dataEnvioPublicacao;

    /**
     * Altera a data de envio de publicação
     * @param dataEnvioPublicacao
     */
    public void setDataEnvioPublicacao(final Date dataEnvioPublicacao) {
        if (dataEnvioPublicacao == null) {
            this.dataEnvioPublicacao = null;
        } else {
            this.dataEnvioPublicacao = new Date(dataEnvioPublicacao.getTime());
        }
    }

    /**
     * Retorna a data de envio para publicação
     * @return Date
     */
    public Date getDataEnvioPublicacao() {
        if (this.dataEnvioPublicacao == null) {
            return null;
        }
        return new Date(this.dataEnvioPublicacao.getTime());
    }

}

package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.SubTipoUtilizacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AfetacaoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private TipoAfetacaoDTO tipo;
    @Getter
    @Setter
    private TipoAcaoDTO tipoDeAcao;
    @Getter
    @Setter
    private String especificar;

    private Date prazoDaReserva;
    @Getter
    @Setter
    private TipoAtoDTO tipoDeAto;
    @Getter
    @Setter
    private Integer numeroAto;

    private Date dataDoAto;
    @Getter
    @Setter
    private Boolean publicado;
    @Getter
    @Setter
    private String pagina;
    @Getter
    @Setter
    private String secao;

    private Date dataPublicacao;
    @Getter
    @Setter
    private Boolean isImovel;
    @Getter
    @Setter
    private List<TipoUtilizacao> tipoDeUso;
    @Getter
    @Setter
    private List<SubTipoUtilizacao> especificacao;
    @Getter
    @Setter
    private List<Arquivo> documentos;
    @Getter
    @Setter
    private List<Imovel> imoveis;

    /**
     * Informa a data de obito do responsável
     * @param prazoDaReserva
     */
    public void setPrazoDaReserva(final Date prazoDaReserva){
        if (prazoDaReserva == null){
            this.prazoDaReserva = null;
        }else{
            this.prazoDaReserva = new Date(prazoDaReserva.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getPrazoDaReserva() {
        if (this.prazoDaReserva == null) {
            return null;
        }
        return new Date(this.prazoDaReserva.getTime());
    }

    /**
     *
     * @param dataDoAto
     */
    public void setDataDoAto(final Date dataDoAto){
        if (dataDoAto == null){
            this.dataDoAto = null;
        }else{
            this.dataDoAto = new Date(dataDoAto.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataDoAto() {
        if (this.dataDoAto == null) {
            return null;
        }
        return new Date(this.dataDoAto.getTime());
    }

    /**
     *
     * @param dataPublicacao
     */
    public void setDataPublicacao(final Date dataPublicacao){
        if (dataPublicacao == null){
            this.dataPublicacao = null;
        }else{
            this.dataPublicacao = new Date(dataPublicacao.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataPublicacao() {
        if (this.dataPublicacao == null) {
            return null;
        }
        return new Date(this.dataPublicacao.getTime());
    }



}

package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class SuspensaoImovelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String numeroProcesso;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private Date vigencia;
    @Getter
    @Setter
    private String dataFimVigencia;
    @Getter
    @Setter
    private MotivacaoSuspensaoImovelDTO motivacao;
    @Getter
    @Setter
    private TipoSuspensaoImovelDTO tipo;
    @Getter
    @Setter
    private List<RestricaoSuspensaoImovelDTO> restricoes;
    @Getter
    @Setter
    private List<RipSuspensaoImovelDTO> rips;
    @Getter
    @Setter
    private boolean ativo;

    /**
     * Informa a data do cumprimento.
     * @param data
     */
    public void setVigencia(final Date data) {
        if (data == null) {
            this.vigencia = null;
        } else {
            this.vigencia = new Date(data.getTime());
        }
    }

    /**
     * Retorna da data do cumprimento.
     * @return Date
     */
    public Date getVigencia() {
        if (this.vigencia == null) {
            return null;
        }
        return new Date(this.vigencia.getTime());
    }
}

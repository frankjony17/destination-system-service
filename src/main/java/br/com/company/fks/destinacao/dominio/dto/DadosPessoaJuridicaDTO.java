package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tawan-souza on 14/11/17.
 */

public class DadosPessoaJuridicaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String cnpj;
    @Getter
    @Setter
    private String estabelecimento;
    @Getter
    @Setter
    private String nomeEmpresarial;
    @Getter
    @Setter
    private String nomeFantasia;
    @Getter
    @Setter
    private String situacaoCadastral;
    @Getter
    @Setter
    private Date dataSituacaoCadastral;
    @Getter
    @Setter
    private String cidadeExterior;
    @Getter
    @Setter
    private String codigoPais;
    @Getter
    @Setter
    private String nomePais;
    @Getter
    @Setter
    private String naturezaJuridica;
    @Getter
    @Setter
    private Date dataAbertura;
    @Getter
    @Setter
    private String cnaePrincipal;
    @Getter
    @Setter
    private List<String> cnaesSecundario;
    @Getter
    @Setter
    private EnderecoDTO endereco;
    @Getter
    @Setter
    private List<TelefoneDTO> telefones;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String cpfResponsavel;
    @Getter
    @Setter
    private String nomeResponsavel;
    @Getter
    @Setter
    private String capitalSocial;
    @Getter
    @Setter
    private List<SocioDTO> socios;
    @Getter
    @Setter
    private String tipoCRCContadorPJ;
    @Getter
    @Setter
    private String classificacaoCRCContadorPJ;
    @Getter
    @Setter
    private String numeroCRCContadorPJ;
    @Getter
    @Setter
    private String ufcrcContadorPJ;
    @Getter
    @Setter
    private String cnpjContador;
    @Getter
    @Setter
    private String tipoCRCContadorPF;
    @Getter
    @Setter
    private String classificacaoCRCContadorPF;
    @Getter
    @Setter
    private String numeroCRCContadorPF;
    @Getter
    @Setter
    private String ufcrcContadorPF;
    @Getter
    @Setter
    private String cpfContador;
    @Getter
    @Setter
    private String porte;
    @Getter
    @Setter
    private String opcaoSimples;
    @Getter
    @Setter
    private Date dataOpcaoSimples;
    @Getter
    @Setter
    private Date dataExclusaoSimples;
    @Getter
    @Setter
    private String cnpjSucedida;
    @Getter
    @Setter
    private List<String> cnpjsSucessora;
    @Getter
    @Setter
    private String erro;
    @Getter
    @Setter
    private Date dataCriacao;
    @Getter
    @Setter
    private String fonte;


    /**
     * Informa a data de obito do responsável
     * @param dataSituacaoCadastral
     */
    public void setDataSituacaoCadastral(final Date dataSituacaoCadastral){
        if (dataSituacaoCadastral == null){
            this.dataSituacaoCadastral = null;
        }else{
            this.dataSituacaoCadastral = new Date(dataSituacaoCadastral.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataSituacaoCadastral() {
        if (this.dataSituacaoCadastral == null) {
            return null;
        }
        return new Date(this.dataSituacaoCadastral.getTime());
    }
    /**
     * Informa a data de obito do responsável
     * @param dataAbertura
     */
    public void setDataAbertura(final Date dataAbertura){
        if (dataAbertura == null){
            this.dataAbertura = null;
        }else{
            this.dataAbertura = new Date(dataAbertura.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataAbertura() {
        if (this.dataAbertura == null) {
            return null;
        }
        return new Date(this.dataAbertura.getTime());
    }

    /**
     * Informa a data de obito do responsável
     * @param dataOpcaoSimples
     */
    public void setDataOpcaoSimples(final Date dataOpcaoSimples){
        if (dataOpcaoSimples == null){
            this.dataOpcaoSimples = null;
        }else{
            this.dataOpcaoSimples = new Date(dataOpcaoSimples.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataOpcaoSimples() {
        if (this.dataOpcaoSimples == null) {
            return null;
        }
        return new Date(this.dataOpcaoSimples.getTime());
    }

    /**
     * Informa a data de obito do responsável
     * @param dataExclusaoSimples
     */
    public void setDataExclusaoSimples(final Date dataExclusaoSimples){
        if (dataExclusaoSimples == null){
            this.dataExclusaoSimples = null;
        }else{
            this.dataExclusaoSimples = new Date(dataExclusaoSimples.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataExclusaoSimples() {
        if (this.dataExclusaoSimples == null) {
            return null;
        }
        return new Date(this.dataExclusaoSimples.getTime());
    }

    /**
     * Informa a data de obito do responsável
     * @param dataCriacao
     */
    public void setDataCriacao(final Date dataCriacao){
        if (dataCriacao == null){
            this.dataCriacao = null;
        }else{
            this.dataCriacao = new Date(dataCriacao.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataCriacao() {
        if (this.dataCriacao == null) {
            return null;
        }
        return new Date(this.dataCriacao.getTime());
    }
}

package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//TODO COMPLEMENTAR CONFORME OUTRAS FONTES FOREM SENDO ADICIONADAS
/**
 * Dados de uma pessoa fisica buscado, a primeiro momento, atraves do InfoConv, WebService da RFB.
 */

public class DadosPessoaFisicaDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String cpf;
    @Getter
    @Setter
    private String situacaoCadastral;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private Date dataNascimento;
    @Getter
    @Setter
    private Integer anoObito;
    @Getter
    @Setter
    private String nomeMae;
    @Getter
    @Setter
    private String sexo;
    @Getter
    @Setter
    private Boolean estrangeiro;
    @Getter
    @Setter
    private String tituloEleitor;
    @Getter
    @Setter
    private Date dataCriacao;
    @Getter
    @Setter
    private EnderecoDTO endereco;
    @Getter
    @Setter
    private String fonte;
    @Getter
    @Setter
    private List<TelefoneDTO> telefones;


    /**
     * Informa a data de nascimento
     * @param dataDeNascimento
     */
    public void setDataNascimento(final Date dataDeNascimento){
        if (dataDeNascimento == null){
            this.dataNascimento = null;
        }else{
            this.dataNascimento = new Date(dataDeNascimento.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getDataNascimento() {
        if (this.dataNascimento == null) {
            return null;
        }
        return new Date(this.dataNascimento.getTime());
    }
    /**
     * Informa a data de criacao
     * @param dataCriacao
     */
    public void setDataCriacao(final Date dataCriacao){
        if (dataCriacao == null){
            this.dataCriacao= null;
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

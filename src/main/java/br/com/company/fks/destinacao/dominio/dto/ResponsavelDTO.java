package br.com.company.fks.destinacao.dominio.dto;


import br.com.company.fks.destinacao.dominio.enums.EstadoCivilEnum;
import br.com.company.fks.destinacao.dominio.enums.OpcoesPadraoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */


public class ResponsavelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String cpfCnpj;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private EstadoCivilEnum estadoCivil;
    @Getter
    @Setter
    private Boolean isObito;
    @Getter
    @Setter
    private Date dataObito;
    @Getter
    @Setter
    private Boolean isFalecidoSisobi;
    @Getter
    @Setter
    private Boolean isFalecidoReceita;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private Double areaFracao;
    @Getter
    @Setter
    private String observacoes;
    @Getter
    @Setter
    private Double renda;
    @Getter
    @Setter
    private Double rendaFamiliar;
    @Getter
    @Setter
    private Double rendaFamiliarCDAUnico;
    @Getter
    @Setter
    private OpcoesPadraoEnum possuiImovelParticular;
    @Getter
    @Setter
    private List<ResidenteDTO> residentes;
    @Getter
    @Setter
    private EnderecoCorrespondenciaDTO enderecoCorrespondencia;
    @Getter
    @Setter
    private String orgaoEntidade;
    @Getter
    @Setter
    private List<FamiliaBeneficiadaDTO> familiasBeneficiadas;
    @Getter
    @Setter
    private List<TelefoneDTO> telefones;
    @Getter
    @Setter
    private Boolean isPrincipal;

    /**
     * Informa a data de obito do responsável
     * @param dataObito
     */
    public void setDataObito(final Date dataObito){
        if (dataObito == null){
            this.dataObito = null;
        }else{
            this.dataObito = new Date(dataObito.getTime());
        }
    }

    /**
     * Retorna a data de obito do responsavel
     * @return
     */
    public Date getDataObito() {
        if (this.dataObito == null) {
            return null;
        }
        return new Date(this.dataObito.getTime());
    }

}

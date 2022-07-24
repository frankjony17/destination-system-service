package br.com.company.fks.destinacao.dominio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by haillanderson on 11/07/17.
 */


public class OcupanteDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String cpfCnpj;
    @Getter
    @Setter
    @Transient
    @JsonIgnore
    private PosseInformalDTO posseInformal;
    @Getter
    @Setter
    private String nomeRazaoSocial;
    @Getter
    @Setter
    private String ug;
    @Getter
    @Setter
    private BigDecimal areaUtilizada;
    @Getter
    @Setter
    private EnderecoDTO endereco;
    @Getter
    @Setter
    private String estadoCivil;
    @Getter
    @Setter
    private String cpfConjuge;
}

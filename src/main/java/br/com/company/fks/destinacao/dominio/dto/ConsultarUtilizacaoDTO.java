package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConsultarUtilizacaoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Imovel imovel;

    @Getter
    @Setter
    private String sequencialParcela;

    @Getter
    @Setter
    private String codigoUtilizacao;


    @Getter
    @Setter
    private DominioDTO tipoDestinacao;

    @Getter
    @Setter
    private String codigoContrato;
    @Getter
    @Setter
    private DominioDTO tipoUtilizacao;

    @Getter
    @Setter
    private BigDecimal fracaoIdeal;

    @Getter
    @Setter
    private DominioDTO subTipoUtilizacao;

    @Getter
    @Setter
    private String nomeResponsavel;

    @Getter
    @Setter
    private BigDecimal areaConstruida;

    @Getter
    @Setter
    private String cpfCnpj;



    @Getter
    @Setter
    private Boolean ativa;

    @Getter
    @Setter
    private Double latitude;

    @Getter
    @Setter
    private String dataInicioContrato;

    @Getter
    @Setter
    private BigDecimal areaTerreno;

    @Getter
    @Setter
    private DominioDTO statusDestinacao;

    @Getter
    @Setter
    private String dataFimContrato;
    @Getter
    @Setter
    private String imagem;

    @Getter
    @Setter
    private Long codigoClassificacaoImovel;

    @Getter
    @Setter
    private EnderecoDTO endereco;

    @Getter
    @Setter
    private Double longitude;

}

package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.enums.TipoImovelEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoOperacaoEnum;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Basis Tecnologia on 06/10/2016.
 */

public class ImovelDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String rip;
    @Getter
    @Setter
    private String memorialDescritivo;
    @Getter
    @Setter
    private Double areaTerreno;
    @Getter
    @Setter
    private BigDecimal areaConstruida;
    @Getter
    @Setter
    private List<ImagemDTO> imagens;
    @Getter
    @Setter
    private Double longitude;
    @Getter
    @Setter
    private Double latitude;
    @Getter
    @Setter
    private BigDecimal valor;
    @Getter
    @Setter
    private EnderecoDTO endereco;
    @Getter
    @Setter
    private String numeroProcesso;
    @Getter
    @Setter
    private BigDecimal valorLaudo;
    @Getter
    @Setter
    private List<DestinacaoDTO> destinacoes;
    @Getter
    @Setter
    private String tipoProprietarioReal;
    @Getter
    @Setter
    private String tipoProprietarioParcial;
    @Getter
    @Setter
    private Long idCadastroImovel;
    @Getter
    @Setter
    private List<BenfeitoriaDTO> benfeitorias;
    @Getter
    @Setter
    private BigDecimal totalAreaDestinada;
    @Getter
    @Setter
    private List<BenfeitoriaDestinadaDTO> benfeitoriasDestinadas;
    @Getter
    @Setter
    private List<ArquivoDTO> plantas;
    @Getter
    @Setter
    private BigDecimal areaTerrenoDestinada;
    @Getter
    @Setter
    private Boolean fracaoIdeal;
    @Getter
    @Setter
    private Boolean areaTotalTerreno;
    @Getter
    @Setter
    private String memorialDescAreaConstruida;
    @Getter
    @Setter
    private UnidadeAutonomaDTO unidadeAutonoma;
    @Getter
    @Setter
    private TipoOperacaoEnum indicadorOperacao;
    @Getter
    @Setter
    private TipoImovelEnum indicadorUnidadeBenfeitoria;
    @Getter
    @Setter
    private BigDecimal areaDisponivel;
    @Getter
    @Setter
    private List<ParcelaDTO> parcelas;
    @Getter
    @Setter
    private Integer quantidade;
    @Getter
    @Setter
    private String descSequencialParcela;
    @Getter
    @Setter
    private BigDecimal areaConstruidaBenfeitoria;
    @Getter
    @Setter
    private Long codigoTipoImovel;
    @Getter
    @Setter
    private Long codigoSituacaoIncorporacao;
    @Getter
    @Setter
    private SituacaoImovelDTO situacaoImovel;
    @Getter
    @Setter
    private Long codigoNaturezaImovel;
    @Getter
    @Setter
    private Long coditoTipoProprietario;
    @Getter
    @Setter
    private String proprietario;
    @Getter
    @Setter
    private Boolean selecionado;
    @Getter
    @Setter
    private Long idBenfeitoriaCadImovel;
    @Getter
    @Setter
    private Long idParcela;
    @Getter
    @Setter
    private Boolean parcelaAtiva;
    @Getter
    @Setter
    private BigDecimal areaDisponivelBenfeitoria;
    @Getter
    @Setter
    private Boolean ativo;
    @Getter
    @Setter
    private String codigoUtilizacao;
    @Getter
    @Setter
    private ParcelaDTO parcela;
    @Getter
    @Setter
    private List<UtilizacaoDTO> utilizacao;
    @Getter
    @Setter
    private String ultimaParcelaCriada;
    @Getter
    @Setter
    private TipoDestinacaoDTO tipoDestinacao;
    @Getter
    @Setter
    private List<ResponsavelDTO> responsaveis;
    @Getter
    @Setter
    private List<OcupanteDTO> ocupantes;
    @Getter
    @Setter
    private Long codigoFormaIncorporacao;
    @Getter
    @Setter
    private Long codigoTipoAquisicao;
    @Getter
    @Setter
    private Long codigoClassificacaoImovel;
    @Getter
    @Setter

    private Long codigoEntidadeExtinta;
    @Getter
    @Setter
    private Boolean possuiPendencia;
    @Getter
    @Setter
    private Double areaTerrenoAntigo;
    @Getter
    @Setter
    private BigDecimal areaConstruidaAntigo;
    @Getter
    @Setter
    private Boolean parcelacomPendenciaDestinacao;
    @Getter
    @Setter
    private Endereco enderecamento;


    public ImovelDTO(String rip,
                     Object endereco,
                     Number areaTerreno,
                     Number areaConstruidaBenfeitoria,
                     Number areaConstruidaUnidade,
                     Number quantidade
    ) {
        ModelMapper map = new ModelMapper();
        map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.rip = rip;
        this.endereco = map.map(endereco, EnderecoDTO.class);
        this.areaTerreno = areaTerreno.doubleValue();

        if (areaConstruidaBenfeitoria != null) {
            this.areaConstruida = BigDecimal.valueOf(areaConstruidaBenfeitoria.doubleValue());
        } else if (areaConstruidaUnidade != null) {
            this.areaConstruida = BigDecimal.valueOf(areaConstruidaUnidade.doubleValue());
        } else {
            this.areaConstruida = BigDecimal.valueOf(this.areaTerreno);
        }

        this.quantidade = quantidade.intValue();
    }

    public ImovelDTO(Long id, Object endereco, String proprietario, Object destinacao, Long idCadastroImovel, String rip){

        ModelMapper map = new ModelMapper();
        map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        DestinacaoDTO destinacaoDTO = map.map(destinacao, DestinacaoDTO.class);
        this.responsaveis = new ArrayList<>();
        this.utilizacao = new ArrayList<>();
        this.id = id;
        this.endereco = map.map(endereco, EnderecoDTO.class);
        this.proprietario = proprietario;
        this.tipoDestinacao = destinacaoDTO.getTipoDestinacao();
        this.responsaveis = destinacaoDTO.getDadosResponsavel().getResponsaveis();
        this.utilizacao.add(destinacaoDTO.getUtilizacao());
        this.idCadastroImovel = idCadastroImovel;
        this.rip = rip;
    }

    public ImovelDTO(){
    }

    public void setProprietario(Object proprietario){
        if(proprietario instanceof String){
            this.proprietario = (String) proprietario;
        } else {
            this.proprietario = null;
        }
    }
}
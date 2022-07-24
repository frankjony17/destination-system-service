package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Classe PosseInformalDTO que extende DeestinacaoDTO
 */

public class PosseInformalDTO extends DestinacaoDTO implements Serializable {
    @Getter
    @Setter
    private ImovelDTO imovel;
    @Getter
    @Setter
    private List<DestinacaoDTO> destinacoes;
    @Getter
    @Setter
    private TipoPosseDTO tipoPosse;
    @Getter
    @Setter
    private String nomeEntidade;
    @Getter
    @Setter
    private String cnpj;
    @Getter
    @Setter
    private List<OcupanteDTO> ocupantes;
    @Getter
    @Setter
    private List<FotoDTO> fotos;
    @Getter
    @Setter
    private List<DocumentoArquivoDTO> documentosArquivo;
    @Getter
    @Setter
    private UtilizacaoDTO utilizacoes;
}

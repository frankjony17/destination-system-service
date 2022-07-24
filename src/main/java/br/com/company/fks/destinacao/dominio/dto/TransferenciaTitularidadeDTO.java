package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


public class TransferenciaTitularidadeDTO extends DestinacaoDTO implements Serializable {

    @Getter
    @Setter
    private List<ArquivoDTO> atosComplementares;

    @Getter
    @Setter
    private TipoTransferenciaDTO tipoTransferencia;

    @Getter
    @Setter
    private DestinatarioDTO destinatario;

    @Getter
    @Setter
    private TipoDestinatarioDTO tipoDestinatario;


}

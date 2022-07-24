package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.TransferenciaTitularidadeDTO;
import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.DestinatarioRepository;
import br.com.company.fks.destinacao.repository.TipoDestinatarioRepository;
import br.com.company.fks.destinacao.repository.TipoTransferenciaRepository;
import br.com.company.fks.destinacao.repository.TransferenciaTitularidadeRepository;
import br.com.company.fks.destinacao.service.destinacao.DestinacaoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Classe responsável por salvar os dados referentes à Trasferência de Titularidade
 * Created by basis on 17/01/17.
 */
@Service
public class TransferenciaTitularidadeService extends DestinacaoService<TransferenciaTitularidade> {

    @Autowired
    private TransferenciaTitularidadeRepository transferenciaTitularidadeRepository;

    @Autowired
    private TipoTransferenciaRepository tipoTransferenciaRepository;

    @Autowired
    private DestinatarioService destinatarioService;

    @Autowired
    private TipoDestinatarioRepository tipoDestinatarioRepository;

    @Autowired
    private EntityConverter entityConverter;


    @Override
    public TransferenciaTitularidade salvarDadosEspecificos(TransferenciaTitularidade transferenciaTitularidade) {
        transferenciaTitularidade.setTipoTransferencia(tipoTransferenciaRepository.save(transferenciaTitularidade.getTipoTransferencia()));
        transferenciaTitularidade.setTipoDestinatario(tipoDestinatarioRepository.save(transferenciaTitularidade.getTipoDestinatario()));
        transferenciaTitularidade.setDestinatario(destinatarioService.salvar(transferenciaTitularidade.getDestinatario(), transferenciaTitularidade.getTipoDestinatario()));
        TransferenciaTitularidade transferenciaTitularidadeSalva = transferenciaTitularidadeRepository.save(transferenciaTitularidade);
        return transferenciaTitularidadeSalva;
    }

    /**
     * Consulta Cessão Gratuita pelo ID
     * @param id
     * @return
     * @throws IntegracaoException
     * @throws IOException
     */
    @Override
    public Resposta<TransferenciaTitularidadeDTO> findOne(Long id) throws IntegracaoException, IOException{
        TransferenciaTitularidadeDTO transferenciaTitularidadeDTO = entityConverter.converterStrict(transferenciaTitularidadeRepository.findOne(id), TransferenciaTitularidadeDTO.class);
        montarDadosMapa(transferenciaTitularidadeDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(transferenciaTitularidadeDTO).build();
    }

}

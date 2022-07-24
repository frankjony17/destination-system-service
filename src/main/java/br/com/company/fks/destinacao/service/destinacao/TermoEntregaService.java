package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.TermoEntregaDTO;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.TermoEntrega;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.TermoEntregaRepository;
import br.com.company.fks.destinacao.service.AtoAutorizativoService;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Classe responsável por salvar dados Termo Entrega
 * Created by basis on 19/01/17.
 */
@Service
public class TermoEntregaService extends DestinacaoService<TermoEntrega> {

    @Autowired
    private TermoEntregaRepository termoEntregaRepository;

    @Autowired
    private AtoAutorizativoService atoAutorizativoService;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    /**
     * Salva dados Termo Entrega
     * @param termoEntrega
     * @return TermoEntrega gravado no banco de dados
     */
    @Override
    public TermoEntrega salvarDadosEspecificos(TermoEntrega termoEntrega) {
        termoEntrega.setDadosResponsavel(dadosResponsavelService.salvar(termoEntrega.getDadosResponsavel(), false));
        return termoEntregaRepository.save(termoEntrega);
    }

    /**
     *
     * @param destinacaoImoveis Recebe um objeto de destinação de imovel e atualiza a area disponivel para zero
     *                          do terreno destinada para benfeitorias que utilizam parcelas do imovel.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizarParcela(List<DestinacaoImovel> destinacaoImoveis) {
        atualizarParcelaEspecifica(destinacaoImoveis);
    }

    /**
     *  Desmarca a destinação como a ultima destinada
     * @param destinacaoImoveis
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void desmarcarUltimaDestiancao(List<DestinacaoImovel> destinacaoImoveis, Boolean copiaDestiancao) {
        desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas(destinacaoImoveis);
    }

    /**
     * Consulta Cessão Gratuita pelo ID
     * @param id
     * @return
     * @throws IntegracaoException
     * @throws IOException
     */
    @Override
    public Resposta<TermoEntregaDTO> findOne(Long id) throws IntegracaoException, IOException {
        TermoEntregaDTO termoEntregaDTO = entityConverter.converterStrict(termoEntregaRepository.findOne(id), TermoEntregaDTO.class);
        montarDadosMapa(termoEntregaDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(termoEntregaDTO).build();
    }
}

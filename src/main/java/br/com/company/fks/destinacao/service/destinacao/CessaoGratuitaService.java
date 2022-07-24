package br.com.company.fks.destinacao.service.destinacao;


import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.CessaoGratuitaDTO;
import br.com.company.fks.destinacao.dominio.entidades.CessaoGratuita;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.CessaoGratuitaRepository;
import br.com.company.fks.destinacao.service.ArquivoService;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.service.ImovelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Classe responsável por salvar CessaoGratuita
 */
@Service
public class CessaoGratuitaService extends DestinacaoService<CessaoGratuita> {

    @Autowired
    private CessaoGratuitaRepository cessaoGratuitaRepository;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    /**
     * Salva CessaoGratuita
     * @param cessaoGratuita
     * @return CessaoGratuita perssistida no banco de dados
     */
    @Override
    public CessaoGratuita salvarDadosEspecificos(CessaoGratuita cessaoGratuita) {
        cessaoGratuita.setDadosResponsavel(dadosResponsavelService.salvar(cessaoGratuita.getDadosResponsavel(), false));
        return cessaoGratuitaRepository.save(cessaoGratuita);
    }

    /**
     *
     * @param destinacaoImoveis Recebe um objeto de destinação de imovel e atualiza a area disponivel para zero
     *                          do terreno destinada para benfeitorias que utilizam parcelas do imovel.
     * @throws NegocioException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizarParcela(List<DestinacaoImovel> destinacaoImoveis) throws NegocioException {
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
    public Resposta<CessaoGratuitaDTO> findOne(Long id) throws IntegracaoException, IOException{
        CessaoGratuitaDTO cessaoGratuitaDTO = entityConverter.converterStrict(cessaoGratuitaRepository.findOne(id), CessaoGratuitaDTO.class);
        montarDadosMapa(cessaoGratuitaDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(cessaoGratuitaDTO).build();
    }
}

package br.com.company.fks.destinacao.service.destinacao;


import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.CdruDTO;
import br.com.company.fks.destinacao.dominio.entidades.Cdru;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.CdruRepository;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Classe responsável por salvar dados CDRU
 */
@Service
public class CdruService extends DestinacaoService<Cdru> {

    @Autowired
    private CdruRepository cdruRepository;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    /**
     * Salvar dados cdru no banco de dados
     * @param cdru
     * @return Cdru gravado no banco de dados
     */
    @Override
    public Cdru salvarDadosEspecificos(Cdru cdru) {
        cdru.setDadosResponsavel(dadosResponsavelService.salvar(cdru.getDadosResponsavel(), false));
        return cdruRepository.save(cdru);
    }

    /**
     * Método sobrescrito para buscar a destinação específica
     * @param id
     * @return
     * @throws IntegracaoException
     * @throws IOException
     */
    @Override
    public Resposta<CdruDTO> findOne(Long id) throws IntegracaoException, IOException {
        CdruDTO cdruDTO = entityConverter.converterStrict(cdruRepository.findOne(id), CdruDTO.class);
        montarDadosMapa(cdruDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(cdruDTO).build();
    }
}

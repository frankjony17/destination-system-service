package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.CessaoOnerosaDTO;
import br.com.company.fks.destinacao.dominio.entidades.CessaoOnerosa;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.CessaoOnerosaRepository;
import br.com.company.fks.destinacao.service.destinacao.DestinacaoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CessaoOnerosaService extends DestinacaoService<CessaoOnerosa> {


    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    @Autowired
    private CessaoOnerosaRepository cessaoOnerosaRepository;

    @Autowired
    private EntityConverter entityConverter;

    /**
     * salva destinação do tipo cessao onerosa/em condição especial
     * @param cessaoOnerosa
     * @return uma cessaoOnerosa salva
     */
    @Override
    public CessaoOnerosa salvarDadosEspecificos(CessaoOnerosa cessaoOnerosa) {
        cessaoOnerosa.setDadosResponsavel(dadosResponsavelService.salvar(cessaoOnerosa.getDadosResponsavel(), false));
        CessaoOnerosa cessaoOnerosaSalva= cessaoOnerosaRepository.save(cessaoOnerosa);
        return cessaoOnerosaSalva;
    }

    @Override
    public Resposta<CessaoOnerosa> findOne(Long id) throws IntegracaoException, IOException {
        CessaoOnerosaDTO cessaoOnerosaDTO = entityConverter.converterStrict(cessaoOnerosaRepository.findOne(id), CessaoOnerosaDTO.class);
        montarDadosMapa(cessaoOnerosaDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(cessaoOnerosaDTO).build();
    }
}

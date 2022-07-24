package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.CuemDTO;
import br.com.company.fks.destinacao.dominio.entidades.Cuem;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.CuemRepository;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Classe responsável por salvar dados do CUEM
 * Created by Basis Tecnologia on 10/10/2016.
 */
@Service
public class CuemService extends DestinacaoService<Cuem> {

    @Autowired
    private CuemRepository cuemRepository;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    /**
     * Salva dados CUEm
     * @param cuem
     * @return Cuem gravado no banco de dados
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Cuem salvarDadosEspecificos(Cuem cuem) {
        cuem.setDadosResponsavel(dadosResponsavelService.salvar(cuem.getDadosResponsavel(), false));
        return cuemRepository.save(cuem);
    }

    /**
     * Metodo para buscar um cuem pra editar e detalhar
     * @param id
     * @return Cuem
     * @throws IntegracaoException
     * @throws IOException
     */
    @Override
    public Resposta<CuemDTO> findOne(Long id) throws IntegracaoException, IOException {
        CuemDTO cuemDTO = entityConverter.converterStrict(cuemRepository.findOne(id), CuemDTO.class);
        montarDadosMapa(cuemDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(cuemDTO).build();
    }



}

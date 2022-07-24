package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.PendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.UsoProprioDTO;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoPendencia;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoPendenciaID;
import br.com.company.fks.destinacao.dominio.entidades.Pendencia;
import br.com.company.fks.destinacao.dominio.entidades.UsoProprio;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.UsoProprioRepository;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.service.DestinacaoPendenciaService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by basis on 03/07/17.
 */
@Service
public class UsoProprioService extends DestinacaoService<UsoProprio> {

    private static final Long USO_PROPRIO_PENDENTE_HOMOLOGACAO = 4L;

    public static final long ID_PENDENCIA_USO_PROPRIO = 4L;
    @Autowired
    private UsoProprioRepository usoProprioRepository;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DestinacaoPendenciaService destinacaoPendenciaService;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UsoProprio salvarDadosEspecificos(UsoProprio usoProprio) {
        destinacaoPendenciaService.gerarPendencia(usoProprio, ID_PENDENCIA_USO_PROPRIO);
        usoProprio.setDadosResponsavel(dadosResponsavelService.salvar(usoProprio.getDadosResponsavel(), false));
        return usoProprioRepository.save(usoProprio);
    }

    /**
     *
     * @param destinacaoImoveis Recebe um objeto de destinação de imovel e
     * atualiza a area disponivel para zero do terreno destinada para
     * benfeitorias que utilizam parcelas do imovel.
     * @throws NegocioException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizarParcela(List<DestinacaoImovel> destinacaoImoveis) throws NegocioException {
        atualizarParcelaEspecifica(destinacaoImoveis);
    }

    /**
     * método responsável por encontrar uma destinação pelo id recebido.
     *
     * @param id
     * @return Resposta contendo a destinação
     * @throws IntegracaoException
     * @throws IOException
     */
    @Override
    public Resposta<UsoProprioDTO> findOne(Long id) throws IntegracaoException, IOException {
        UsoProprioDTO usoProprioDTO = entityConverter.converterStrict(usoProprioRepository.findOne(id), UsoProprioDTO.class);
        montarDadosMapa(usoProprioDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(usoProprioDTO).build();
    }

    /**
     * Desmarca a destinação como a ultima destinada
     *
     * @param destinacaoImoveis
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void desmarcarUltimaDestiancao(List<DestinacaoImovel> destinacaoImoveis, Boolean copiaDestiancao) {
        desmarcarDestinacaoSemUtilizacaoDestinacoesUtilizamParcelas(destinacaoImoveis);
    }

    /**
     * Método responsável por remover pendências de homologação.
     *
     * @param usoProprio
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removerPendenciaDeHomologacao(UsoProprio usoProprio) {

        Set<DestinacaoPendencia> destinacoesPendencias = new HashSet<>();
        Set<PendenciaDTO> pendenciaDTOSet = destinacaoPendenciaService.buscarPendenciasPorIdDestinacao(usoProprio.getId());

        for (PendenciaDTO pendenciaDTO : pendenciaDTOSet) {
            DestinacaoPendencia destinacaoPendencia = new DestinacaoPendencia();
            DestinacaoPendenciaID destinacaoPendenciaID = new DestinacaoPendenciaID();
            destinacaoPendenciaID.setPendencia(entityConverter.converterStrict(pendenciaDTO, Pendencia.class));
            destinacaoPendenciaID.setDestinacao(usoProprio);
            destinacaoPendencia.setDestinacaoPendenciaID(destinacaoPendenciaID);
            destinacaoPendencia.setDataGerada(pendenciaDTO.getDataGerada());
            if (!destinacaoPendencia.getDestinacaoPendenciaID().getPendencia().getId().equals(USO_PROPRIO_PENDENTE_HOMOLOGACAO)) {
                destinacoesPendencias.add(destinacaoPendencia);
            }
        }
        usoProprio.setPendencias(destinacoesPendencias);
    }

}

package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.CadastroSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.DoacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.MotivacaoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.RestricaoSuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.SuspensaoImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.DoacaoRepository;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável por salvar dados da Docação
 * Created by Basis Tecnologia on 10/10/2016.
 */
@Service
public class DoacaoService extends DestinacaoService<Doacao> {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;



    private static final String DESCRICAOSUSPENSAO = "Imóvel suspenso por Doação com Encargos";


    /**
     * Salva os dados referentes à docação
     * @param doacao
     * @return Doacao gravada no banco de dados
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Doacao salvarDadosEspecificos(Doacao doacao){
        doacao.setDadosResponsavel(dadosResponsavelService.salvar(doacao.getDadosResponsavel(), false));
        Doacao doacaoSalva = doacaoRepository.save(doacao);
        if(doacaoSalva.getExisteEncargo()){
            suspenderImovel(doacao);
        }

        return doacaoSalva;
    }

    @Override
    public Resposta<DoacaoDTO> findOne(Long id) throws IntegracaoException, IOException{
        DoacaoDTO doacaoDTO = entityConverter.converterStrict(doacaoRepository.findOne(id), DoacaoDTO.class);
        montarDadosMapa(doacaoDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(doacaoDTO).build();
    }

    private CadastroSuspensaoImovelDTO criarObjetoSuspensao(Doacao doacao, DestinacaoImovel destinacaoImovel){
        MotivacaoSuspensaoImovelDTO motivacaoSuspensaoImovelDTO = new MotivacaoSuspensaoImovelDTO(6L, "Doação com encargos", 1L,"" );
        List<RestricaoSuspensaoImovelDTO> restricoes = getListaRestricoes();
        SuspensaoImovelDTO suspensaoImovelDTO = new SuspensaoImovelDTO();
        suspensaoImovelDTO.setVigencia(new Date());
        suspensaoImovelDTO.setNumeroProcesso(doacao.getNumeroProcesso());
        suspensaoImovelDTO.setDescricao(DESCRICAOSUSPENSAO + "("+ destinacaoImovel.getImovel().getRip() + "/" + destinacaoImovel.getCodigoUtilizacao() + ")");
        suspensaoImovelDTO.setMotivacao(motivacaoSuspensaoImovelDTO);

        suspensaoImovelDTO.setRestricoes(restricoes);
        CadastroSuspensaoImovelDTO cadastroSuspensaoImovelDTO = new CadastroSuspensaoImovelDTO();
        cadastroSuspensaoImovelDTO.setSuspensaoImovel(suspensaoImovelDTO);
        List<String> rip = new ArrayList<>();
        rip.add(destinacaoImovel.getImovel().getRip());
        cadastroSuspensaoImovelDTO.setRips(rip);
        return cadastroSuspensaoImovelDTO;

    }

    private ResponseEntity<?> suspenderImovel(Doacao doacao){
            doacao.getDestinacaoImoveis().forEach(destinacaoImovel -> {
                CadastroSuspensaoImovelDTO cadastroSuspensaoImovelDTO = criarObjetoSuspensao(doacao, destinacaoImovel);
                String url = urlIntegracaoUtils.getUrlSuspenderImovel();

                requestUtils.doPostBetweenModules(url, cadastroSuspensaoImovelDTO);

            });
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private List<RestricaoSuspensaoImovelDTO> getListaRestricoes(){
        String url = urlIntegracaoUtils.getUrlBuscarRestricoes();
        List resposta = (List) requestUtils.doGet(url, List.class).getBody();
        List<RestricaoSuspensaoImovelDTO> restricaoSuspensaoImovelDTO = new ArrayList<>();
        for(int i = 0; i < resposta.size(); i++) {
            restricaoSuspensaoImovelDTO.add(entityConverter.converterStrict(resposta.get(i), RestricaoSuspensaoImovelDTO.class));
        }
        return restricaoSuspensaoImovelDTO;
    }

}

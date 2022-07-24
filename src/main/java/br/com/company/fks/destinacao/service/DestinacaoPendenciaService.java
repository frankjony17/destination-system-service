package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoPendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.PendenciaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoPendencia;
import br.com.company.fks.destinacao.dominio.entidades.Pendencia;
import br.com.company.fks.destinacao.repository.DestinacaoPendenciaRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



/**
 * Classe responsável por realizar operações na Pendencia
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Service
public class DestinacaoPendenciaService {

    @Value("${saml.config.home_sp}")
    private String urlDetinacao;

    @Autowired
    private DestinacaoPendenciaRepository destinacaoPendenciaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PendenciaService pendenciaService;

    @Autowired
    private EntityConverter entityConverter;

    /**
     * Gera um pendencia para a destinação de acordo com a pendencia informada.
     * @param destinacao
     * @param pendencia
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void gerarPendencia(Destinacao destinacao, Pendencia pendencia) {
        DestinacaoPendenciaDTO destinacaoPendenciaDTO = this.buscarDestinacaoPendenciaPorId(destinacao.getId(), pendencia.getId());

        if(destinacaoPendenciaDTO != null){
            
            DestinacaoPendencia destinacaoPendencia = entityConverter.converterStrict(destinacaoPendenciaDTO, DestinacaoPendencia.class);
            destinacao.setPendencias(new HashSet<DestinacaoPendencia>());
            destinacao.getPendencias().add(destinacaoPendencia);

        } else {

            DestinacaoPendencia destinacaoPendencia = new DestinacaoPendencia(destinacao, pendenciaService.findById(pendencia.getId()));
            Set<DestinacaoPendencia> pendencias = new HashSet<>();
            pendencias.add(destinacaoPendencia);
            destinacao.setPendencias(pendencias);

        }
    }

    /**
     * Gera um pendencia para a destinacao de acordo com o id da pendencia informada.
     * @param destinacao
     * @param idPendencia
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void gerarPendencia(Destinacao destinacao, Long idPendencia) {
        Pendencia pendencia = pendenciaService.findById(idPendencia);
        gerarPendencia(destinacao, pendencia);
    }

    @Transactional(readOnly = true)
    public Set<PendenciaDTO> buscarPendenciasUsuario() {
        UsuarioLogadoDTO usuarioLogadoDTO = usuarioService.getUsuarioLogado();
        Set<PendenciaDTO> pendenciaDTOs = destinacaoPendenciaRepository.buscarPendenciasUsuarioLogado(usuarioLogadoDTO.getPermissoes(),
                usuarioLogadoDTO.getJurisdicoes());

        Map<String, Pendencia> mapaPendencias = pendenciaService.getMapPendencias();
        Map<String, Long> mapaQuantidadePendencias = calcularQuantidadePorTipoPendencia(pendenciaDTOs);

        return montarSetPendencias(mapaQuantidadePendencias, mapaPendencias);
    }

    @Transactional(readOnly = true)
    public Set<PendenciaDTO> buscarPendenciasPorIdDestinacao(Long idDestinacao) {
        return destinacaoPendenciaRepository.buscarPendenciasPorIdDestinacao(idDestinacao);

    }

    @Transactional(readOnly = true)
    public DestinacaoPendenciaDTO buscarDestinacaoPendenciaPorId(Long idDestinacao, Long idPendencia) {
        return destinacaoPendenciaRepository.buscarDestinacaoPendenciaPorId(idDestinacao, idPendencia);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removerPendencia(Long idDestinacao, Long idPendencia) {
        DestinacaoPendenciaDTO destinacaoPendenciaDTO = this.buscarDestinacaoPendenciaPorId(idDestinacao, idPendencia);

        if(destinacaoPendenciaDTO != null){

            DestinacaoPendencia destinacaoPendencia = entityConverter.converterStrict(destinacaoPendenciaDTO, DestinacaoPendencia.class);
            destinacaoPendenciaRepository.delete(destinacaoPendencia);


        }
    }

    private Map<String, Long> calcularQuantidadePorTipoPendencia(Set<PendenciaDTO> pendencias) {
        return pendencias.stream()
                .collect(Collectors.groupingBy(PendenciaDTO::getDescricao,
                        Collectors.counting()));
    }

    private Set<PendenciaDTO> montarSetPendencias(Map<String, Long> mapaQuantidadePendencias, Map<String, Pendencia> mapaPendencias) {
        Set<PendenciaDTO> pendencias = new HashSet<>();
        mapaPendencias.forEach((k, v) -> {
            if (mapaQuantidadePendencias.containsKey(k)) {
                PendenciaDTO pendenciaDTO = new PendenciaDTO();
                pendenciaDTO.setQuantidade(mapaQuantidadePendencias.get(k));
                pendenciaDTO.setDescricao(v.getDescricao());
                pendenciaDTO.setUrl(urlDetinacao + "destinacao/consultar");
                pendencias.add(pendenciaDTO);
            }
        });

        return pendencias;
    }
}

package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.HistoricoAnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoChefia;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSecretario;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSuperintendente;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoTecnico;
import br.com.company.fks.destinacao.dominio.entidades.HistoricoAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;
import br.com.company.fks.destinacao.repository.HistoricoAnaliseTecnicaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável por realizar busca e salvar Histórico da Análise Técnica
 * Created by diego on 29/11/16.
 */
@Service
public class HistoricoAnaliseTecnicaService {

    @Autowired
    private HistoricoAnaliseTecnicaRepository historicoAnaliseTecnicaRepository;

    /**
     * Procura histórico da análise por id da análise técnica
     * @param idAnaliseTecnica
     * @param offset
     * @param limit
     * @return Page<HistoricoAnaliseTecnicaDTO> lista com os históricos da análise encontrados pelo id da análise técnica
     */
    @Transactional(readOnly = true)
    public Page<HistoricoAnaliseTecnicaDTO> findByAnaliseTecnicaId (Long idAnaliseTecnica, int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit, Sort.Direction.ASC, "id");
        return historicoAnaliseTecnicaRepository.findByAnaliseTecnicaId(idAnaliseTecnica, pageable);
    }

    /**
     * Salva no banco de dados o histórico da análise técnica
     * @param statusAnaliseTecnicaAnterior
     * @param statusAnaliseTecnicaAtual
     * @param analiseTecnica
     * @param usuarioLogadoDTO
     * @return HistoricoAnaliseTecnica salvo no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public HistoricoAnaliseTecnica salvar(StatusAnaliseTecnica statusAnaliseTecnicaAnterior,
                                          StatusAnaliseTecnica statusAnaliseTecnicaAtual,
                                          AnaliseTecnica analiseTecnica,
                                          UsuarioLogadoDTO usuarioLogadoDTO) {

        HistoricoAnaliseTecnica historicoAnaliseTecnica = construirHistorico(statusAnaliseTecnicaAnterior, statusAnaliseTecnicaAtual, analiseTecnica, usuarioLogadoDTO);
        return historicoAnaliseTecnicaRepository.save(historicoAnaliseTecnica);
    }

    private HistoricoAnaliseTecnica construirHistorico(StatusAnaliseTecnica statusAnaliseTecnicaAnterior, StatusAnaliseTecnica statusAnaliseTecnicaAtual, AnaliseTecnica analiseTecnica, UsuarioLogadoDTO usuarioLogadoDTO) {
        HistoricoAnaliseTecnica historicoAnaliseTecnica = new HistoricoAnaliseTecnica();
        historicoAnaliseTecnica.setAnaliseTecnica(analiseTecnica);
        historicoAnaliseTecnica.setDataAlteracao(new Date());
        historicoAnaliseTecnica.setIdUsuario(usuarioLogadoDTO.getId());
        historicoAnaliseTecnica.setNomeUsuario(usuarioLogadoDTO.getNome());
        historicoAnaliseTecnica.setStatusAnaliseTecnicaAnterior(statusAnaliseTecnicaAnterior);
        historicoAnaliseTecnica.setStatusAnaliseTecnicaAtual(statusAnaliseTecnicaAtual);
        historicoAnaliseTecnica.setJustificativa(getJustificativaPerfil(analiseTecnica, usuarioLogadoDTO.getPermissoes()));
        return historicoAnaliseTecnica;
    }

    private String getJustificativaPerfil(AnaliseTecnica analiseTecnica, Set<String> permissoes) {
        PermissaoAnaliseEnum permissaoAnaliseEnum =
                PermissaoAnaliseEnum.getPermissaoAnaliseDescricao(permissoes);
        String justificativa = StringUtils.EMPTY;

        switch (permissaoAnaliseEnum) {
            case EXEC_ANALISE_TECNICO:
                justificativa = getJustificativa(analiseTecnica.getDespachosAnaliseTecnico());
                break;
            case EXEC_ANALISE_CHEFIA:
                justificativa = getJustificativa(analiseTecnica.getDespachosAnaliseChefia());
                break;
            case EXEC_ANALISE_SUPERINTENDENTE:
                justificativa = getJustificativa(analiseTecnica.getDespachosAnaliseSuperintendente());
                break;
            case EXEC_ANALISE_SECRETARIO:
                justificativa = getJustificativa(analiseTecnica.getDespachosAnaliseSecretario());
                break;

            default:
                break;
        }
        return justificativa;
    }


    private String getJustificativa(List<?> analises) {
        Iterator iterator = analises.iterator();
        String justificativa = StringUtils.EMPTY;

        while (iterator.hasNext()) {
            Object element = iterator.next();
            justificativa = getJustificativaPorTipo(element);
            if (justificativa != null && !justificativa.isEmpty()) {
                break;
            }
        }
        return justificativa;
    }

    private String getJustificativaPorTipo(Object object) {
        if (object instanceof AnaliseTecnicaDespachoTecnico) {
            return ((AnaliseTecnicaDespachoTecnico) object).getJustificativa();
        } else if (object instanceof AnaliseTecnicaDespachoChefia) {
            return ((AnaliseTecnicaDespachoChefia) object).getJustificativa();
        } else if (object instanceof AnaliseTecnicaDespachoSuperintendente) {
            return ((AnaliseTecnicaDespachoSuperintendente) object).getJustificativa();
        } else {
            return ((AnaliseTecnicaDespachoSecretario) object).getJustificativa();
        }
    }

}

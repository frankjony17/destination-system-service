package br.com.company.fks.destinacao.service.analiseTecnica;

import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.DespachoEnum;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raphael on 09/12/16.
 */
@Component
public class StatusAnaliseDespachoSuperintendente extends StatusAnaliseDespacho {

    private static final Map<Long, StatusAnaliseTecnicaEnum> mapaStatus = new HashMap<>();

    public static final long TREZE = 13L;

    public static final long QUATORZE = 14L;

    public static final long UM = 1L;

    public static final long DOIS = 2L;

    public static final long TRES = 3L;

    public static final long QUATRO = 4L;

    public static final long CINCO = 5L;

    static {
        //CONCORDO
        mapaStatus.put(TREZE, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO);
        mapaStatus.put(QUATORZE, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA);

        //ALTERAR
        mapaStatus.put(UM, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO);
        mapaStatus.put(DOIS, StatusAnaliseTecnicaEnum.INDEFERIDO);
        mapaStatus.put(TRES, StatusAnaliseTecnicaEnum.AGUARDANDO_REQUERENTE);
        mapaStatus.put(QUATRO, StatusAnaliseTecnicaEnum.CANCELADO);
        mapaStatus.put(CINCO, null);//TODO ALTERAR QUANDO RESOLVER PENDENCIA DO REQUISITO

    }

    /**
     * Verifica o status da análise técnica de acordo com os despachos superintendente
     * @param analiseTecnicaDTO
     * @return status da análise técnica
     */
    @Override
    public StatusAnaliseTecnica getCodigoStatusPorDespacho(AnaliseTecnicaDTO analiseTecnicaDTO) {
        List<DespachoDTO> despachosChefia= analiseTecnicaDTO.getDespachosChefia();
        List<DespachoDTO> despachosSuperintendente = analiseTecnicaDTO.getDespachosSuperintendente();

        if (despachosChefia.isEmpty()) {
            DespachoDTO despachoDTO = despachosSuperintendente.get(INDICE_ZERO);
            montarMapaChefiaSemResposta();
            return getStatusAnalisePorDesapacho(despachoDTO, mapaStatus);
        } else {
            montarMapaChefiaResposta();
            return getStatusAnaliseChefiaDecisaoSuperintendente(despachosChefia, despachosSuperintendente);
        }

    }

    private void montarMapaChefiaResposta() {
        mapaStatus.put(QUATORZE, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA);
        mapaStatus.put(DOIS, StatusAnaliseTecnicaEnum.INDEFERIDO);
        mapaStatus.put(TRES, StatusAnaliseTecnicaEnum.AGUARDANDO_REQUERENTE);
        mapaStatus.put(QUATRO, StatusAnaliseTecnicaEnum.CANCELADO);
    }

    private void montarMapaChefiaSemResposta() {
        mapaStatus.put(QUATORZE, StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA);
        mapaStatus.put(DOIS, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO);
        mapaStatus.put(TRES, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO);
        mapaStatus.put(QUATRO, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO);
    }

    private StatusAnaliseTecnica getStatusAnaliseChefiaDecisaoSuperintendente(List<DespachoDTO> despachosChefia,
                                                                              List<DespachoDTO> desapachoSuperintendente ) {
        if (desapachoSuperintendente.size() == QTD_UM) {

            DespachoDTO despachoChefia = despachosChefia.get(INDICE_ZERO);
            DespachoDTO despachoSuperintendente = desapachoSuperintendente.get(INDICE_ZERO);

            StatusAnaliseTecnica statusAnaliseTecnica = getStatusAnaliseChefiaDecisaoSuperintendente(despachoChefia, despachoSuperintendente);

            if (statusAnaliseTecnica == null) {
                return superintendenteRetornarChefia(despachoSuperintendente);
            } else {
                return statusAnaliseTecnica;
            }

        } else if (desapachoSuperintendente.size() > QTD_UM) {
            DespachoDTO despachoDTO = filtrarDespachos(desapachoSuperintendente);
            return getStatusAnalisePorDesapacho(despachoDTO, mapaStatus);
        }
        return null;
    }

    private StatusAnaliseTecnica getStatusAnaliseChefiaDecisaoSuperintendente(DespachoDTO despachoChefia, DespachoDTO despachoSuperintendente) {
        if (chefiaAcordoSuperintendenteAcordoAvaliacao(despachoChefia, despachoSuperintendente)) {
            return getStatusAnalise(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SECRETARIO.getCodigo());
        } else if (chefiaAcordoSuperintendenteRetornarChefia(despachoSuperintendente)) {
            return getStatusAnalise(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_CHEFIA.getCodigo());
        }
        return null;
    }

    private Boolean chefiaAcordoSuperintendenteAcordoAvaliacao(DespachoDTO despachoChefia, DespachoDTO despachoSuperintendente) {
        return  despachoSuperintendente.getId().equals(DespachoEnum.DE_ACORDO_AV_TEC_CHEFIA.getCodigo())
                && despachoChefiaAcordo(despachoChefia);
    }

    private Boolean despachoChefiaAcordo(DespachoDTO despachoChefia) {
        return despachoChefia.getId().equals(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
    }

    private Boolean chefiaAcordoSuperintendenteRetornarChefia(DespachoDTO despachoSuperintendente) {
        return  despachoSuperintendente.getId().equals(DespachoEnum.RETORNAR_AVALIACAO_TEC_CHEFIA.getCodigo());
    }

    private StatusAnaliseTecnica superintendenteRetornarChefia(DespachoDTO despachoSuperintendente) {
        return getStatusAnalisePorDesapacho(despachoSuperintendente, mapaStatus);
    }

}

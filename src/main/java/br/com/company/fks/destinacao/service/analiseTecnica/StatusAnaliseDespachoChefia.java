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
public class StatusAnaliseDespachoChefia extends StatusAnaliseDespacho {

    private static final Long UM = 1L;
    private static final Long DOIS = 2L;
    private static final Long TRES = 3L;
    private static final Long QUATRO = 4L;
    private static final Long CINCO = 5L;
    private static final Long SEIS = 6L;
    private static final Long SETE = 7L;

    private static final Map<Long, StatusAnaliseTecnicaEnum> mapaStatus = new HashMap<>();
    static {

        mapaStatus.put(UM, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE);
        mapaStatus.put(SEIS, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE);
        mapaStatus.put(SETE, StatusAnaliseTecnicaEnum.AGUARDANDO_ANALISE_TECNICA);


        mapaStatus.put(DOIS, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE);
        mapaStatus.put(TRES, StatusAnaliseTecnicaEnum.AGUARDANDO_REQUERENTE);
        mapaStatus.put(QUATRO, StatusAnaliseTecnicaEnum.CANCELADO);
        mapaStatus.put(CINCO, null);//TODO ALTERAR QUANDO RESOLVER PENDENCIA DO REQUISITO

    }

    /**
     * Verifica o Status da análise técnica de acordo com os despachos e despachos chefia
     * @param analiseTecnicaDTO
     * @return status da análise
     */
    @Override
    public StatusAnaliseTecnica getCodigoStatusPorDespacho(AnaliseTecnicaDTO analiseTecnicaDTO) {
        List<DespachoDTO> despachosTecnico = analiseTecnicaDTO.getDespachosTecnico();
        List<DespachoDTO> despachosChefia = analiseTecnicaDTO.getDespachosChefia();

        if (despachosTecnico.isEmpty()) {
            DespachoDTO despachoDTO = despachosChefia.get(INDICE_ZERO);
            return getStatusAnalisePorDesapacho(despachoDTO, mapaStatus);
        } else {
            return getStatusAnaliseTecnicaDecisaoTecnico(despachosTecnico, despachosChefia);
        }

    }

    private StatusAnaliseTecnica getStatusAnaliseTecnicaDecisaoTecnico(List<DespachoDTO> despachosTecnico,
                                                                       List<DespachoDTO> despachosChefia) {
        if (despachosChefia.size() == QTD_UM) {

            DespachoDTO despachoDTOChefia = despachosChefia.get(INDICE_ZERO);
            DespachoDTO despachoDTOTecnico = despachosTecnico.get(INDICE_ZERO);

            return getStatusAnaliseTecnicaDecisaoTecnico(despachoDTOTecnico, despachoDTOChefia);

        } else if (despachosChefia.size() > QTD_UM) {
            DespachoDTO despachoDTO = filtrarDespachos(despachosChefia);
            return getStatusAnalisePorDesapacho(despachoDTO, mapaStatus);
        }
        return null;
    }

    private StatusAnaliseTecnica getStatusAnaliseTecnicaDecisaoTecnico(DespachoDTO despachoTecnico, DespachoDTO despachoChefia) {
        if (tecnicoAtendeReqChefiaDeAcordoAvTecnica(despachoTecnico, despachoChefia)) {
            return getStatusAnalise(StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE.getCodigo());
        } else {
            return getStatusAnalisePorDesapacho(despachoChefia, mapaStatus);
        }
    }

    private Boolean tecnicoAtendeReqChefiaDeAcordoAvTecnica(DespachoDTO despachoTecnico, DespachoDTO despachoChefia) {
        return  chefiaAcordo(despachoChefia)
                && despachoTecnico.getId().equals(DespachoEnum.ATENDE_AOS_REQUISITOS.getCodigo());
    }

    private Boolean chefiaAcordo(DespachoDTO despachoChefia) {
        return despachoChefia.getId().equals(DespachoEnum.DE_ACORDO_AV_TECNICA.getCodigo());
    }

}

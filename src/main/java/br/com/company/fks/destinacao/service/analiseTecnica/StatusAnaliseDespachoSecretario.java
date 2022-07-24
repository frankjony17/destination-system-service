package br.com.company.fks.destinacao.service.analiseTecnica;

import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raphael on 09/12/16.
 */
@Component
public class StatusAnaliseDespachoSecretario extends StatusAnaliseDespacho {

    private static final Map<Long, StatusAnaliseTecnicaEnum> mapaStatus = new HashMap<>();

    private static final long ID_ENVIADO_PUBLICACAO = 9L;

    private static final long ID_INDEFERIDO = 10L;

    private static final long ID_AGUARD_MANIF_SUPERINTENDENTE = 11L;

    static {
        //CONCORDO
        mapaStatus.put(ID_ENVIADO_PUBLICACAO, StatusAnaliseTecnicaEnum.ENVIADO_PUBLICACAO);
        mapaStatus.put(ID_INDEFERIDO, StatusAnaliseTecnicaEnum.INDEFERIDO);
        mapaStatus.put(ID_AGUARD_MANIF_SUPERINTENDENTE, StatusAnaliseTecnicaEnum.AGUARDANDO_MANIFESTACAO_SUPERINTENDENTE);
    }

    /**
     * Verifica o status da análise técnica de acordo com os despachos secretário
     * @param analiseTecnicaDTO
     * @return status da análise
     */
    @Override
    public StatusAnaliseTecnica getCodigoStatusPorDespacho(AnaliseTecnicaDTO analiseTecnicaDTO) {
        List<DespachoDTO> despachosSecretario = analiseTecnicaDTO.getDespachosSecretario();

        if (!despachosSecretario.isEmpty()) {
            DespachoDTO despachoDTO = despachosSecretario.get(INDICE_ZERO);
            return getStatusAnalisePorDesapacho(despachoDTO, mapaStatus);
        }

        return null;
    }
}

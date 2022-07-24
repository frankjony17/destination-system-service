package br.com.company.fks.destinacao.service.analiseTecnica;

import br.com.company.fks.destinacao.dominio.dto.AnaliseTecnicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.service.DominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by raphael on 09/12/16.
 */
@Component
public abstract class StatusAnaliseDespacho {

    protected static final int QTD_UM = 1;
    protected static final int INDICE_ZERO = 0;
    protected static final int ID_DESPACHO_SEIS = 6;
    protected static final int ID_DESPACHO_UM = 1;

    @Autowired
    private DominioService dominioService;

    /**
     * Metodo abstrato
     * @param analiseTecnicaDTO
     * @return
     */
    public abstract  StatusAnaliseTecnica getCodigoStatusPorDespacho(AnaliseTecnicaDTO analiseTecnicaDTO);

    protected StatusAnaliseTecnica getStatusAnalisePorDesapacho(DespachoDTO despachoDTO, Map<Long, StatusAnaliseTecnicaEnum> mapaStatus) {
        StatusAnaliseTecnicaEnum statusAnaliseTecnicaEnum = mapaStatus.get(despachoDTO.getId());
        return getStatusAnalise(statusAnaliseTecnicaEnum.getCodigo());
    }

    protected StatusAnaliseTecnica getStatusAnalise(Integer id) {
        return (StatusAnaliseTecnica) dominioService.findById(id, StatusAnaliseTecnica.class);
    }

    protected DespachoDTO filtrarDespachos(List<DespachoDTO> despachos) {
        Optional<DespachoDTO> optional =
                despachos
                        .stream()
                        .filter(despacho ->
                                despacho.getId() >= ID_DESPACHO_UM && despacho.getId() <= ID_DESPACHO_SEIS

                        )
                        .findFirst();
        return optional.orElse(null);
    }

}

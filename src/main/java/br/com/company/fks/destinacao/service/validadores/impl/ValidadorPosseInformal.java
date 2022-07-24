package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Basis Tecnologia on 26/10/2016.
 */

/**
 * Classe para fazer as validações específicas de Posse Informal
 */
@Service
public class ValidadorPosseInformal extends ValidadorDestinacao<PosseInformal> {

    private static final long ID_TIPO_POSSE_ENTIDADE_COLETIVA = 2L;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    /**
     *
     * @param posseInformal Recebe o objeto de posse informal para verifcar os dados específicos
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(PosseInformal posseInformal) throws NegocioException {
        List<String> erros = new ArrayList<>();
        validarCampoTipoPosseVazio(posseInformal.getDadosResponsavel().getTipoPosseOcupacao(), erros);
        validarCamposEntidadeVazios(posseInformal, erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }
    }

    private void validarCampoTipoPosseVazio(TipoPosseOcupacaoEnum tipoPosse, List<String> erros) {
        Boolean tipoPossePreenchida = getTipoPossePreenchida(tipoPosse);
        if (!tipoPossePreenchida) {
            erros.add(mensagemNegocio.get("posse.informal.tipo.posse"));
        }

    }

    private void validarCampoEntidadeVazio(PosseInformal posseInformal, List<String> erros) {
        if (posseInformal.getNomeEntidade() == null) {
            erros.add(mensagemNegocio.get("posse.informal.nome.entidade"));
        }
    }

    private void validarCamposEntidadeVazios(PosseInformal posseInformal, List<String> erros) {
        Boolean tipoPossePreenchida = getTipoPossePreenchida(posseInformal.getDadosResponsavel().getTipoPosseOcupacao());
        if (tipoPossePreenchida && posseInformal.getDadosResponsavel().getTipoPosseOcupacao().getCodigo().longValue() == ID_TIPO_POSSE_ENTIDADE_COLETIVA) {
            validarCampoEntidadeVazio(posseInformal, erros);
        }
    }

    private Boolean getTipoPossePreenchida(TipoPosseOcupacaoEnum tipoPosse) {
        Optional<TipoPosseOcupacaoEnum> optional = Optional.ofNullable(tipoPosse);
        return optional.map(map -> map.getCodigo() != null).orElse(false);
    }
}

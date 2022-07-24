package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Cdru;
import br.com.company.fks.destinacao.dominio.entidades.TipoConcessao;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe para fazer as validações específicas de CDRU
 */
@Service
public class ValidadorCdru extends ValidadorDestinacao<Cdru> {

    @Autowired
    private MensagemNegocio mensagemNegocio;

    /**
     *
     * @param cdru recebe um CDRU pra validar seus parametros específicos
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(Cdru cdru) throws NegocioException {

        List<String> erros = new ArrayList<>();

        validarCampoTipoConcessaoVazio(cdru.getTipoConcessao(), erros);
        validarCamposObrigatoriosLicitacao(cdru.getLicitacao(),erros);
        validarExisteFundamentoLegal(cdru.getCodFundamentoLegal(),erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }

    }

    /**
     *
     * @param tipoConcessao recebe o tipo de concessao para verificação
     * @param erros recebe uma lista de erros para serem printadas
     */
    private void validarCampoTipoConcessaoVazio (TipoConcessao tipoConcessao, List<String> erros) {
        Optional<TipoConcessao> optional = Optional.ofNullable(tipoConcessao);
        Boolean tipoConcessaoPreenchido = optional.map(map -> map.getId() != null).orElse(false);

        if (!tipoConcessaoPreenchido) {
            erros.add(mensagemNegocio.get("tipo.concessao"));
        }
        
    }
}

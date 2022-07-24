package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import br.com.company.fks.destinacao.dominio.entidades.Encargo;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */

/**
 * Classe para fazer as validações específicas de Doacao
 */
@Service
public class ValidadorDoacao extends ValidadorDestinacao<Doacao> {

    @Autowired
    private MensagemNegocio mensagemNegocio;

    /**
     *
     * @param doacao Recebe o objeto de doacao para verifcar os dados específicos
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(Doacao doacao) throws NegocioException {

        List<String> erros = new ArrayList<>();

        validarCamposObrigatorioContrato(doacao, erros);

        validarExisteEncargo(doacao.getExisteEncargo(), doacao.getEncargos(), erros);
        validarDatasEntreEncargoEAssinatura(doacao.getContrato(), doacao.getEncargos(), erros);
        validarExisteFundamentoLegal(doacao.getCodFundamentoLegal(), erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }

    }

    private void validarExisteEncargo(Boolean existeEncargo, List<Encargo> encargos, List<String> erros) {
        Optional<List<Encargo>> listaEncargos = Optional.ofNullable(encargos);
        if (encargos == null || isNaoPossuiEncargos(listaEncargos, existeEncargo)) {
            erros.add(mensagemNegocio.get("encargo.existe.encago"));
        }
    }

    private Boolean isNaoPossuiEncargos(Optional<List<Encargo>> encargos, Boolean existeEncargo) {
        return existeEncargo && encargos.get().isEmpty();
    }

}

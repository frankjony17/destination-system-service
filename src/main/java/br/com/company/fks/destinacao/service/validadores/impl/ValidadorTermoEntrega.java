package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.TermoEntrega;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basis on 19/01/17.
 */

/**
 * Classe para fazer as validações específicas de Termo Entrega
 */
@Service
public class ValidadorTermoEntrega extends ValidadorDestinacao<TermoEntrega> {

    /**
     *
     * @param termoEntrega Recebe o objeto de termo entrega para verifcar os dados específicos
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(TermoEntrega termoEntrega) throws NegocioException {
        List<String> erros = new ArrayList<>();

        validarCamposObrigatorioContrato(termoEntrega, erros);

        validarExisteFundamentoLegal(termoEntrega.getCodFundamentoLegal(), erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }
    }
}

package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.CessaoGratuita;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe para fazer as validações específicas de Cessao Gratuita
 */
@Service
public class ValidadorCessaoGratuita extends ValidadorDestinacao<CessaoGratuita> {

    /**
     *
     * @param cessaoGratuita Recebe o objeto de cessao gratuita para verifcar os dados específicos
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(CessaoGratuita cessaoGratuita) throws NegocioException {

        List<String> erros = new ArrayList<>();

        validarExisteFundamentoLegal(cessaoGratuita.getCodFundamentoLegal(), erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }

    }

}

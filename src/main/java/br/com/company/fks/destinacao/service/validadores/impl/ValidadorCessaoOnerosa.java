package br.com.company.fks.destinacao.service.validadores.impl;


import br.com.company.fks.destinacao.dominio.entidades.CessaoOnerosa;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorCessaoOnerosa extends ValidadorDestinacao<CessaoOnerosa> {

    @Override
    public void validadorEspecifico(CessaoOnerosa cessaoOnerosa) throws NegocioException {
        List<String> erros = new ArrayList<>();

        validarCamposObrigatorioContrato(cessaoOnerosa, erros);

        validarExisteFundamentoLegal(cessaoOnerosa.getCodFundamentoLegal(), erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }
    }
}

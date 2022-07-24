package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.PermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorPermissaoUsoImovelFuncional extends ValidadorDestinacao<PermissaoUsoImovelFuncional> {

    @Override
    public void validadorEspecifico(PermissaoUsoImovelFuncional permissaoUsoImovelFuncional) throws NegocioException {
        List<String> erros = new ArrayList<>();

        validarCamposObrigatorioContrato(permissaoUsoImovelFuncional, erros);

        validarExisteFundamentoLegal(permissaoUsoImovelFuncional.getCodFundamentoLegal(), erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }
    }
}

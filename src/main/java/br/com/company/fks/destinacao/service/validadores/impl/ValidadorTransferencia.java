package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidadorTransferencia extends ValidadorDestinacao<TransferenciaTitularidade> {


    @Override
    public void validadorEspecifico(TransferenciaTitularidade transferenciaTitularidade) throws NegocioException {
        List<String> erros = new ArrayList<>();


        validarExisteFundamentoLegal(transferenciaTitularidade.getCodFundamentoLegal(), erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }
    }

}

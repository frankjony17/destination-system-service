package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.UsoProprio;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import org.springframework.stereotype.Service;

/**
 * Created by basis on 06/07/17.
 */
@Service
public class ValidadorUsoProprio extends ValidadorDestinacao<UsoProprio> {

    @Override
    public void validadorEspecifico(UsoProprio usoProprio) throws NegocioException {
        //Valida Uso proprio
    }
}

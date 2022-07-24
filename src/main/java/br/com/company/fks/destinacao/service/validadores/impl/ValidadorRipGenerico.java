package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import org.springframework.stereotype.Component;

/**
 * Created by diego on 26/05/17.
 */
@Component
public class ValidadorRipGenerico extends ValidadorRip {

    @Override
    public void validadorEspecifico(Imovel imovel, Long idModalidade) throws NegocioException {
        if(imovel.getAtivo().equals(false)){
            throw new NegocioException("RIP INVÁLIDO");
        }

    }

}

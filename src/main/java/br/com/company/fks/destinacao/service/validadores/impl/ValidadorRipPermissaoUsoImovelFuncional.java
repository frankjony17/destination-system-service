package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRipPermissaoUsoImovelFuncional extends ValidadorRip {

    @Override
    public void validadorEspecifico(Imovel imovel, Long idModalidade) throws NegocioException{
        if(!verificaImovelDf(imovel) || verificarParcela(imovel)){
            throw setaMensagemErro("RIP Invalido");
        }

    }
}

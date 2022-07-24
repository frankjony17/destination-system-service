package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import br.com.company.fks.destinacao.utils.Constants;
import org.springframework.stereotype.Component;

/**
 * Created by diego on 26/05/17.
 */
@Component
public class ValidadorRipCuem extends ValidadorRip {

    @Override
    public void validadorEspecifico(Imovel imovel, Long idModalidade) throws NegocioException {
        if(verificaAreaCUEM(idModalidade, imovel)){
            throw setaMensagemErro(null);
        }
    }

    private Boolean verificaAreaCUEM(Long idModalidade, Imovel imovel){
        if((idModalidade == Constants.UM)
                && (imovel.getAreaTerreno().compareTo(Constants.DUZENTOS_E_CINQUENTA) == Constants.UM)){
            return true;
        }
        return false;
    }

}

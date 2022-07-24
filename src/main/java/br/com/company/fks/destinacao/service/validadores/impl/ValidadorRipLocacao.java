package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import org.springframework.stereotype.Component;

/**
 * Created by diego on 26/05/17.
 */
@Component
public class ValidadorRipLocacao extends ValidadorRip {

    @Override
    public void validadorEspecifico(Imovel imovel, Long idModalidade) throws NegocioException {
        if(!isImovelCavidadeOuEspelhoDagua(imovel.getCodigoTipoImovel())){
            throw  setaMensagemErro(null);
        }
    }

}

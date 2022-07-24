package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import org.springframework.stereotype.Component;

/**
 * Created by diego on 15/08/17.
 */
@Component
public class ValidadorRipTermoEntrega extends ValidadorRip {

    private static final String UNIAO = "UNIAO";

    @Override
    public void validadorEspecifico(Imovel imovel, Long idModalidade) throws NegocioException {

        if(verificaAreaConstruidaCessaoEntrega(imovel)) {
            throw setaMensagemErro(null);
        }

        if (proprietarioDiferenteUniao(imovel)) {
            throw setaMensagemErro("O imóvel informado não pertence a União");
        }

    }

    private Boolean proprietarioDiferenteUniao(Imovel imovel) {
        return !imovel.getProprietario().equals(UNIAO);
    }
}

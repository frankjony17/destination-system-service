package br.com.company.fks.destinacao.service.validadores.impl;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.UsuarioService;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by basis on 03/07/17.
 */
@Component
public class ValidadorRipUsoProprio extends ValidadorRip {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Método que valida o rip de acordo com as regras do Uso Próprio
     * @param imovel
     * @param idModalidade
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(Imovel imovel, Long idModalidade) throws NegocioException {
        UsuarioLogadoDTO usuarioLogadoDTO = usuarioService.getUsuarioLogado();
        if(imovel.getProprietario().contains(";")){
            if(!imovel.getProprietario().contains(usuarioLogadoDTO.getCpf())){
                throw setaMensagemErro(null);
            }
        }
        else if(imovel.getProprietario().contains("-")){
            if(!imovel.getProprietario().equals(usuarioLogadoDTO.getIdOrganizacao()+"-"+usuarioLogadoDTO.getOrganizacao())){
                throw setaMensagemErro(null);
            }
        }
        else{
            if(!imovel.getProprietario().equals(usuarioLogadoDTO.getOrganizacao())){
                throw setaMensagemErro(null);
            }
        }
    }
}

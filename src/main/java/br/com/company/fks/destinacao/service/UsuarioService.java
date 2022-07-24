package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.gov.mpog.acessos.cliente.servico.AcessosClienteService;
import br.com.company.fks.destinacao.dominio.enums.PerfilEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por realizar operações com o Usuário
 * Created by Basis Tecnologia on 27/10/2016.
 */
@Service
public class UsuarioService{

    @Value(value = "${saml.config.idSistema}")
    private String idSistema;

    @Autowired
    private AcessosClienteService acessosClienteService;

    /**
     * Identifica o usuário logado
     * @return UsuarioLogadoDTO
     */
    public UsuarioLogadoDTO getUsuarioLogado() {
        return acessosClienteService.buscarUsuarioLogado();
    }

    /**
     * Identifica o perfil do usuário logado
     * @return PerfilEnum perfil do usuário logado
     */
    public PerfilEnum getPerfil() {
        UsuarioLogadoDTO usuarioLogadoDTO = getUsuarioLogado();
        return PerfilEnum.buscarPorNome(usuarioLogadoDTO.getPerfil());
    }
}
package br.com.company.fks.destinacao.apresentacao;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.builder.ServicosResposta;
import br.com.company.fks.destinacao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Basis Tecnologia on 27/10/2016.
 */

/**
 * Classe que conterá a comunicação rest de usuario
 */
@RestController
@RequestMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * @return o objeto do usuario que está logado no sistema
     */
    @RequestMapping(value = "usuario-logado",method = RequestMethod.GET)
    public ResponseEntity<ServicosResposta> getUsuarioLogado() {

        UsuarioLogadoDTO usuarioLogadoDTO = usuarioService.getUsuarioLogado();

        ServicosResposta servicosResposta = new ServicosResposta.Builder().resultado(usuarioLogadoDTO).build();

        return new ResponseEntity<>(servicosResposta, HttpStatus.OK);

    }

}
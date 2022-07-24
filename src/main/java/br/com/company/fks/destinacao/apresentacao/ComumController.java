package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.ServicosResposta;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Basis Tecnologia on 02/06/2016.
 */

/**
 * Classe que conterá a comunicação rest para metodos comuns a todos os caminhos do sistema
 */
@RestController
@RequestMapping(value = "/comum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ComumController {

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private URLIntegracaoUtils urlUtils;

    @Value(value = "${saml.config.idSistema}")
    private String idSistema;

    @Value("${url.integrador}")
    private String urlIntegrador;

    @Value("${url.cadastro.imovel}")
    private String urlCadastroImovel;

    @Value("${url.servicos}")
    private String urlServicos;

    @Value("${url.destinacao}")
    private String urlDestinacao;

    @Value("${url.siapa}")
    private String urlSiapa;

    @Value("${saml.config.home_sp}")
    private String urlHome;

    /**
     *
     * @return retorna o menu do sistema
     */
    @RequestMapping(value="/url-menu", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getUrlAmbiente(){

        Map<String, String> mapaUrls = new HashMap<>();
        mapaUrls.put("servicos", urlServicos);
        mapaUrls.put("cadastroImoveis", urlCadastroImovel);
        mapaUrls.put("integrador", urlIntegrador);
        mapaUrls.put("destinacao", urlDestinacao);
        mapaUrls.put("siapa", urlSiapa);
        List menus = Arrays.asList(mapaUrls);
        Map<String, Object> resposta = new HashedMap();
        resposta.put("resposta", menus);

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return recupera o id do sistema
     */
    @RequestMapping(value = "/buscar-id-sistema", method = RequestMethod.GET)
    public ResponseEntity<ServicosResposta> buscarIdSistema() {
        ServicosResposta resposta = new ServicosResposta.Builder()
                .resultado(idSistema)
                .build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar-url-home", method = RequestMethod.GET)
    public ResponseEntity<ServicosResposta> getUrlHome(){
        ServicosResposta resposta = new ServicosResposta.Builder()
                .resultado(urlHome)
                .build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}

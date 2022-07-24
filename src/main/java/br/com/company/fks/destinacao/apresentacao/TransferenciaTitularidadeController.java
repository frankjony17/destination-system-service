package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.builder.ServicosResposta;
import br.com.company.fks.destinacao.dominio.entidades.TipoDestinatario;
import br.com.company.fks.destinacao.dominio.entidades.TipoTransferencia;
import br.com.company.fks.destinacao.dominio.enums.AutarquiasFundacoesEnum;
import br.com.company.fks.destinacao.service.DominioService;
import br.com.company.fks.destinacao.service.TransferenciaTitularidadeService;
import br.com.company.fks.destinacao.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

/**
 * Classe que conterá a comunicação rest de transferencia titularidade
 */
@RestController
@RequestMapping(value = "transferencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TransferenciaTitularidadeController {

    @Autowired
    private DominioService dominioService;

    @Autowired
    private TransferenciaTitularidadeService transferenciaTitularidadeService;


    /**
     *
     * @return todos os tipos de tranferencia no banco
     */
    @RequestMapping(value = "/tipo-transferencia", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<TipoTransferencia>>> findAllTipoTransferencia() {
        return new ResponseEntity<>(
                RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoTransferencia.class)).build(), HttpStatus.OK);
    }


    /**
     *
     * @return todos os tipos de destinatario no banco
     */
    @RequestMapping(value = "/tipo-destinatario", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<TipoDestinatario>>> findAllTipoDestinatario() {
        return new ResponseEntity<>(
                RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoDestinatario.class)).build(), HttpStatus.OK);
    }


    /**
     *
     * @return todas as Autarquias/fundacoes
     */
    @RequestMapping(value ="/buscar-autarquias", method = RequestMethod.GET)
    public ResponseEntity<ServicosResposta> buscarNaturezaJuridicaPermitida(){
        ServicosResposta resposta = new ServicosResposta.Builder()
                .resultado(EnumUtils.ordenarEnum(AutarquiasFundacoesEnum.class))
                .build();
        return new ResponseEntity<ServicosResposta>(resposta, HttpStatus.OK);
    }
}

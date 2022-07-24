package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.entidades.ClassificacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.TipoAcao;
import br.com.company.fks.destinacao.dominio.entidades.TipoAfetacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoAto;
import br.com.company.fks.destinacao.dominio.entidades.TipoAtoAutorizativo;
import br.com.company.fks.destinacao.dominio.entidades.TipoConcessao;
import br.com.company.fks.destinacao.dominio.entidades.TipoDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoDocumento;
import br.com.company.fks.destinacao.dominio.entidades.TipoInstrumento;
import br.com.company.fks.destinacao.dominio.entidades.TipoJuro;
import br.com.company.fks.destinacao.dominio.entidades.TipoLicitacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoModalidade;
import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import br.com.company.fks.destinacao.dominio.entidades.TipoPagamento;
import br.com.company.fks.destinacao.dominio.entidades.TipoPeriocidade;
import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import br.com.company.fks.destinacao.dominio.entidades.TipoReajuste;
import br.com.company.fks.destinacao.dominio.enums.UFEnum;
import br.com.company.fks.destinacao.service.DominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */

/**
 * Classe que conterá a comunicação rest de dominio
 */
@RestController
@RequestMapping(value = "/dominio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DominioController {

    @Autowired
    private DominioService dominioService;

    /**
     *
     * @return todos os tipos de pagamento no banco
     */
    @RequestMapping(value = "buscar-tipo-pagamento", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTipoPagamentos(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoPagamento.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de periocidade do banco
     */
    @RequestMapping(value = "buscar-tipo-periocidade", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTipoPeriocidades(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoPeriocidade.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de moeda do banco
     */
    @RequestMapping(value = "buscar-tipo-moeda", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTipoMoeda(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoMoeda.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de reajuste no banco
     */
    @RequestMapping(value = "buscar-tipo-reajuste", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTipoReajuste(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoReajuste.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de juros do sistema
     */
    @RequestMapping(value = "buscar-tipo-juros", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTipoJuros(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoJuro.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de licitação do banco
     */
    @RequestMapping(value = "buscar-tipo-licitacao", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTipoLicitacao(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoLicitacao.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de posse do banco
     */
    @RequestMapping(value = "buscar-tipo-posse", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTipoPosse(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoPosse.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os classificações do imovel
     */
    @RequestMapping(value = "buscar-classificacao-imovel", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosClassificacaoImovel(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(ClassificacaoImovel.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de modalidade do banco
     */
    @RequestMapping(value = "buscar-tipo-modalidade", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTipoModalidade(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoModalidade.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de concessão do banco
     */
    @RequestMapping(value = "buscar-tipo-concessao", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTiposConcessao(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoConcessao.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de instrumento do sistema
     */
    @RequestMapping(value = "buscar-tipo-instrumento", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTiposInstrumento(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoInstrumento.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos os tipos de destinacao do sistema
     */
    @RequestMapping(value = "buscar-tipo-destinacao", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTodosTiposDestinacao(){
        Resposta<DominioDTO> resposta = RespostaBuilder.getBuilder().setResultado(dominioService.buscarTodos(TipoDestinacao.class)).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     *
     * @return todos as UFs do banco
     */
    @RequestMapping(value = "buscar-ufs", method = RequestMethod.GET)
    public ResponseEntity<Resposta<UFEnum>> buscarTodasUfs(){
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(UFEnum.values()).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "buscar-tipo-documento", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscarTipoDocumento(){
        List<DominioDTO> lista = dominioService.buscarTodos(TipoDocumento.class);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(lista).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "atoAutorizativo", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscaTodosTiposAtosAdministrativos(){
        List<DominioDTO> lista = dominioService.buscarTodos(TipoAtoAutorizativo.class);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(lista).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "tipoAfetacao", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscaTodosTiposAfetacao(){
        List<DominioDTO> lista = dominioService.buscarTodos(TipoAfetacao.class);
            return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(lista).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "tipoAcao", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscaTodosTiposAcao(){
        List<DominioDTO> lista = dominioService.buscarTodos(TipoAcao.class);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(lista).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "tipoAto", method = RequestMethod.GET)
    public ResponseEntity<Resposta<DominioDTO>> buscaTodosTiposAto(){
        List<DominioDTO> lista = dominioService.buscarTodos(TipoAto.class);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(lista).build(), HttpStatus.OK);
    }
}

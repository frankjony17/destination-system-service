package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.EncerramentoDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.OrdenacaoEnumDTO;
import br.com.company.fks.destinacao.dominio.entidades.EncerramentoDestinacao;
import br.com.company.fks.destinacao.dominio.enums.DespachoEncerrarDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoEncerrarDestinacaoEnum;
import br.com.company.fks.destinacao.service.EncerramentoDestinacaoService;
import br.com.company.fks.destinacao.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haillanderson on 01/09/17.
 */

@RestController
@RequestMapping(value = "/encerrarDestinacao", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EncerrarDestinacaoController {

    @Autowired
    private EncerramentoDestinacaoService encerramentoDestinacaoService;


    @RequestMapping(value = "/lista-motivos", method = RequestMethod.GET)
    public ResponseEntity<Resposta> buscarMotivosEncerrarDestinacao() {
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnumMotivoPosseInformalparaEncerrarDestinacao(MotivoEncerrarDestinacaoEnum.class);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(result).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/lista-despachos", method = RequestMethod.GET)
    public ResponseEntity<Resposta> buscarDespachosCancelarEncerrar() {
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnum(DespachoEncerrarDestinacaoEnum.class);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(result).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/submeter/{idDestinacao}", method = RequestMethod.PUT)
    public ResponseEntity submeterSuperIntendente(@PathVariable(value = "idDestinacao") Long idDestinacao, @RequestBody EncerramentoDestinacaoDTO dto) {
        encerramentoDestinacaoService.submeterSuperintendente(idDestinacao, dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmar/{idDestinacao}", method = RequestMethod.PUT)
    public ResponseEntity confirmarCancelamento(@PathVariable(value = "idDestinacao") Long idDestinacao, @RequestBody EncerramentoDestinacaoDTO dto) {
        encerramentoDestinacaoService.confirmarEncerramento(idDestinacao, dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/destinacao/{idDestinacao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta> buscarPorIdDestinacao(@PathVariable("idDestinacao") Long idDestinacao) {
        EncerramentoDestinacao result = encerramentoDestinacaoService.buscarPorIdDestinacao(idDestinacao);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(result).build(), HttpStatus.OK);
    }
    
}

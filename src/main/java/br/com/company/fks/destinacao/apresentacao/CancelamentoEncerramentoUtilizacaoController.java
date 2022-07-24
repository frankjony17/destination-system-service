package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.CancelamentoEncerramentoDTO;
import br.com.company.fks.destinacao.dominio.dto.OrdenacaoEnumDTO;
import br.com.company.fks.destinacao.dominio.entidades.CancelamentoEncerramento;
import br.com.company.fks.destinacao.dominio.enums.DespachoCancelarEncerrarEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoCancelarEncerrarEnum;
import br.com.company.fks.destinacao.service.CancelamentoEncerramentoUtilizacaoService;
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
 * Created by tawan-souza on 18/12/17.
 */
@RestController
@RequestMapping(value = "/cancelamento-encerramento", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CancelamentoEncerramentoUtilizacaoController {

    @Autowired
    private CancelamentoEncerramentoUtilizacaoService service;

    @RequestMapping(value = "/lista-motivos", method = RequestMethod.GET)
    public ResponseEntity<Resposta> buscarMotivosCancelarEncerrar() {
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnumMotivoPosseIformal(MotivoCancelarEncerrarEnum.class);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(result).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/lista-despachos", method = RequestMethod.GET)
    public ResponseEntity<Resposta> buscarDespachosCancelarEncerrar() {
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnum(DespachoCancelarEncerrarEnum.class);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(result).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/submeter/{idDestinacao}", method = RequestMethod.PUT)
    public ResponseEntity submeterSuperIntendente(@PathVariable(value = "idDestinacao") Long idDestinacao, @RequestBody CancelamentoEncerramentoDTO dto) {
        service.submeterSuperintendente(idDestinacao, dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmar/{idDestinacao}", method = RequestMethod.PUT)
    public ResponseEntity confirmarCancelamento(@PathVariable(value = "idDestinacao") Long idDestinacao, @RequestBody CancelamentoEncerramentoDTO dto) {
        service.confirmarCancelamento(idDestinacao, dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/destinacao/{idDestinacao}", method = RequestMethod.GET)
    public ResponseEntity<Resposta> buscarPorIdDestinacao(@PathVariable("idDestinacao") Long idDestinacao) {
        CancelamentoEncerramento result = service.buscarPorIdDestinacao(idDestinacao);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(result).build(), HttpStatus.OK);
    }
}

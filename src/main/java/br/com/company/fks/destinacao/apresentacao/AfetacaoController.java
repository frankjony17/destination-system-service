package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.AfetacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Afetacao;
import br.com.company.fks.destinacao.service.AfetacaoService;
import br.com.company.fks.destinacao.service.UtilizacaoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/afetacao/")
public class AfetacaoController {

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private AfetacaoService afetacaoService;

    @Autowired
    private UtilizacaoService utilizacaoService;

    @RequestMapping(value = "salvar", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Resposta<Afetacao>> salvarDoacao(@RequestBody AfetacaoDTO afetacaoDTO) {
        Resposta<Afetacao> resposta;
        HttpStatus httpStatus = HttpStatus.OK;
        Afetacao afetacao = entityConverter.converterStrict(afetacaoDTO, Afetacao.class);
        afetacao = afetacaoService.salvar(afetacao);
        resposta =  RespostaBuilder.getBuilder().setResultado(afetacao).build();
        afetacao.getImoveis().forEach(i -> i.setParcelas(null));
        return new ResponseEntity<>(resposta, httpStatus);

    }

}

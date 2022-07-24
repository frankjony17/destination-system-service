package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.LaudoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping (value = "laudo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LaudoController  {

    @RequestMapping(value = "imovel", method = RequestMethod.GET)
    public ResponseEntity <Resposta<List<LaudoDTO>>> buscarLaudo (@RequestParam(value = "rips",required = true) String rips){
        HttpStatus httpStatus = HttpStatus.OK;
        Resposta<List<LaudoDTO>> resposta = RespostaBuilder.getBuilder().setResultado(criarMockLaudos()).build();
        return new ResponseEntity<>(resposta, httpStatus);
    }

    private List<LaudoDTO>criarMockLaudos(){

        LaudoDTO laudo = new LaudoDTO();
        ImovelDTO imovel = new ImovelDTO();
        imovel.setRip("00000000000000000");
        laudo.setImovel(imovel);
        laudo.setValorLaudo(BigDecimal.TEN);
        laudo.setArquivosLaudo("laudo.pdf");
        laudo.setAssinaturaLaudo("laudoAssinatura");
        laudo.setValorVenda(BigDecimal.TEN);
        return Arrays.asList(laudo);
    }

}

package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.dominio.enums.AdotarEnderecoEnum;
import br.com.company.fks.destinacao.service.EnderecoCorrespondenciaService;
import br.com.company.fks.destinacao.service.EnderecoService;
import br.com.company.fks.destinacao.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "enderecoCorrespondencia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EnderecoCorrespondenciaController {

    @Autowired
    private EnderecoCorrespondenciaService enderecoCorrespondenciaService;

    @Autowired
    private EnderecoService enderecoService;

    @RequestMapping(value = "/adotar-endereco", method = RequestMethod.GET)
    public ResponseEntity <Map<String, String>> buscarAdotarEndereco(){
        Map<String, String> result = EnumUtils.buscarEnumChaveValor(AdotarEnderecoEnum.class);
        return new ResponseEntity <>(result, HttpStatus.OK);
    }

}

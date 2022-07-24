package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaConsultaDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaFisicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaHistoricoCampoDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaJuridicaDTO;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.DadosPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/dados-pessoa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DadosPessoaController {

    @Autowired
    private DadosPessoaService service;

    /**
     * Busca os dados de uma pessoa a partir do cpf
     * @param dadosPessoaConsultaDTO dto com os parametros de busca
     * @return dados da pessoa com cpf consultado
     */
    @RequestMapping(value = "/fisica", method = RequestMethod.POST)
    public ResponseEntity<Resposta> buscarDadosPessoaFisica(@RequestBody DadosPessoaConsultaDTO dadosPessoaConsultaDTO, HttpServletRequest request) throws NegocioException {

        DadosPessoaFisicaDTO dadosPessoaFisicaDTO = service.buscarPessoaFisica(dadosPessoaConsultaDTO, dadosPessoaConsultaDTO.getFundamentoLegal());
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(dadosPessoaFisicaDTO).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/fisica/historico", method = RequestMethod.GET)
    public ResponseEntity<Resposta> buscarDadosPessoaFisicaHistoricoCampo(@RequestParam(value = "campo") String campo, @RequestParam(value = "cpf") String cpf) {
        List<DadosPessoaHistoricoCampoDTO> dtos = service.buscarPessoaFisicaHistoricaCampo(cpf, campo);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(dtos).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/juridica", method = RequestMethod.POST)
    public ResponseEntity<Resposta> buscarDadosPessoaJuridica(@RequestBody DadosPessoaConsultaDTO dadosPessoaConsultaDTO, HttpServletRequest request) throws NegocioException {

        DadosPessoaJuridicaDTO dadosPessoaJuridicaDTO = service.buscarPessoaJuridica(dadosPessoaConsultaDTO, dadosPessoaConsultaDTO.getFundamentoLegal());
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(dadosPessoaJuridicaDTO).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/juridica/historico", method = RequestMethod.GET)
    public ResponseEntity<Resposta> buscarDadosPessoaJuridicaHistoricoCampo(@RequestParam(value = "campo") String campo, @RequestParam(value = "cnpj") String cnpj) {
        List<DadosPessoaHistoricoCampoDTO> dtos = service.buscarPessoaJuridicaHistoricaCampo(cnpj, campo);
        return new ResponseEntity<>(RespostaBuilder.getBuilder().setResultado(dtos).build(), HttpStatus.OK);
    }

}

package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.dto.LocalizacaoEctDTO;
import br.com.company.fks.destinacao.dominio.dto.MunicipioDTO;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.LocalidadeService;
import br.com.company.fks.destinacao.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by basis on 16/01/17.
 */

/**
 * Classe que conterá a comunicação rest de localidade
 */
@RestController
@RequestMapping(value = "/localidade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LocalidadeController {

    private static final Logger LOGGER = Logger.getLogger(LocalizacaoEctDTO.class);

    @Autowired
    private LocalidadeService localidadeService;

    /**
     *
     * @param cep recebe o cep que o usuario informou
     * @return todos o endereço atrelado aquele CEP. Faz a busca na receita federal
     */
    @RequestMapping(value="/buscar-endereco-cep/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resposta<LocalizacaoEctDTO>> listarEnderecoPorCep(@PathVariable("cep") String cep){
        Resposta<LocalizacaoEctDTO> resposta;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(
                    localidadeService.findEnderecoByCep(cep)).build();
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @RequestMapping(value="/buscar-paises", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resposta<List<DominioDTO>>> buscarPaises(){
        Resposta<List<DominioDTO>> resposta;
        List<DominioDTO> paises = Arrays.asList(criarPais(Constants.UM, "Brasil"), criarPais(Constants.DOIS,"Alemanha"), criarPais(Constants.TRES,"Japão"));
        resposta = RespostaBuilder.getBuilder().setResultado(paises).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    private DominioDTO criarPais(Integer id, String descricao){
        DominioDTO dominioDTO = new DominioDTO();
        dominioDTO.setId(id);
        dominioDTO.setDescricao(descricao);
        return dominioDTO;
    }

    @RequestMapping(value = "/buscar-municipo-uf/{uf}", method = RequestMethod.GET)
    public ResponseEntity<Resposta<List<MunicipioDTO>>> buscarMunicipioPorUf(@PathVariable("uf") String uf) {
        Resposta<List<MunicipioDTO>> resposta = null;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(
                    localidadeService.findMunicipioByUF(uf)).build();
        } catch (IntegracaoException e) {
            LOGGER.error(e.getCause(), e);
            resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}

package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoResponsavelDTO;
import br.com.company.fks.destinacao.dominio.dto.OrdenacaoEnumDTO;
import br.com.company.fks.destinacao.dominio.dto.ResponsavelDTO;
import br.com.company.fks.destinacao.dominio.enums.DescricaoParentescoEnum;
import br.com.company.fks.destinacao.dominio.enums.EstadoCivilEnum;
import br.com.company.fks.destinacao.dominio.enums.OpcoesPadraoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoRepresentacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.ResponsavelService;
import br.com.company.fks.destinacao.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */

/**
 * Classe que conterá a comunicação rest de responsavel
 */
@RestController
@RequestMapping(value = "responsavel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResponsavelController {


    @Autowired
    private ResponsavelService responsavelService;


    /**
     * Método que recebe o id do imovel e retorna o responsável deste imóvel de acordo com os dados do cadastro de imóveis
     * @param idImovel
     * @return Responsável do imóvel
     */
    @RequestMapping(value = "buscarResponsavelImovel/{id}")
    public ResponseEntity<Resposta<ResponsavelDTO>> buscarResponsavelImovel(@PathVariable("id") Long idImovel){
        Resposta<ResponsavelDTO> resposta;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            resposta = RespostaBuilder.getBuilder().setResultado(responsavelService.buscarResponsavelImovel(idImovel)).build();
        } catch (IntegracaoException e){
            httpStatus = HttpStatus.BAD_REQUEST;
           resposta = RespostaBuilder.getBuilder().setErro(e.getMessage()).build();
        }
        return new ResponseEntity<>(resposta, httpStatus);
    }

    @RequestMapping(value = "destinacoes/{cpf}", method = RequestMethod.GET)
    public ResponseEntity<List<DestinacaoResponsavelDTO>> buscarPorResponsavel(@PathVariable("cpf") String cpf){
        return new ResponseEntity<>(responsavelService.buscarResponsavelDestinacao(cpf), HttpStatus.OK);
    }

    @RequestMapping(value = "/tipo-posse-ocupacao", method = RequestMethod.GET)
    public ResponseEntity<List<OrdenacaoEnumDTO>> buscarTipoPosseOcupacao(){
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnum(TipoPosseOcupacaoEnum.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/tipo-representacao", method = RequestMethod.GET)
    public ResponseEntity<List<OrdenacaoEnumDTO>> buscarTipoRepresentacao(){
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnum(TipoRepresentacaoEnum.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/estado-civil", method = RequestMethod.GET)
    public ResponseEntity<List<OrdenacaoEnumDTO>> buscarEstadoCivil(){
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnum(EstadoCivilEnum.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/opcoes-padrao", method = RequestMethod.GET)
    public ResponseEntity<List<OrdenacaoEnumDTO>> buscarOpcoesPadrao(){
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnum(OpcoesPadraoEnum.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/descricao-parentesco", method = RequestMethod.GET)
    public ResponseEntity<List<OrdenacaoEnumDTO>> buscarDescricoesParentesco(){
        List<OrdenacaoEnumDTO> result = EnumUtils.ordenarEnum(DescricaoParentescoEnum.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

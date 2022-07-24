package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.DocumentoAnalise;
import br.com.company.fks.destinacao.dominio.entidades.DocumentoArquivo;
import br.com.company.fks.destinacao.dominio.entidades.Foto;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.destinacao.PosseInformalService;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by basis on 22/05/17.
 */
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ValidadorPosseInformalIT extends BaseIntegrationTestCofig{
    @Autowired
    private ValidadorPosseInformal validadorPosseInformal;


    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarCampoEntidadeVazioEntidadeNulo(){
        PosseInformal posseInformal = new PosseInformal();
        List<String> erros = new ArrayList<>();
        TipoPosse tipoPosse = new TipoPosse();
        DadosResponsavel dadosResponsavel = new DadosResponsavel();
        tipoPosse.setId(3);
        dadosResponsavel.setTipoPosseOcupacao(TipoPosseOcupacaoEnum.COLETIVO);
        validadorPosseInformal.validadorEspecifico(posseInformal);


    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarCampoEntidadeVazioEntidadePreenchida(){
        PosseInformal posseInformal = new PosseInformal();
        List<String> erros = new ArrayList<>();
        DadosResponsavel dadosResponsavel = new DadosResponsavel();
        TipoPosseOcupacaoEnum.COLETIVO.getCodigo();
        posseInformal.setNomeEntidade("teste");
        dadosResponsavel.setTipoPosseOcupacao(TipoPosseOcupacaoEnum.COLETIVO);
        posseInformal.setDocumentosArquivo(criarDocumentoArquivos());
        posseInformal.setFotos(criarFotos());
        validadorPosseInformal.validadorEspecifico(posseInformal);
        Assert.assertTrue(erros.isEmpty());
    }

    private List<DocumentoArquivo> criarDocumentoArquivos() {
        DocumentoArquivo documentoArquivo = new DocumentoArquivo();
        documentoArquivo.setId(1L);
        return asList(documentoArquivo);
    }

    private List<Foto> criarFotos() {
        Foto foto = new Foto();
        foto.setId(1L);
        return asList(foto);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarCampoEntidadeVazioEntidadePreenchidaTipoPosseVazio(){
        PosseInformal posseInformal = new PosseInformal();
        List<String> erros = new ArrayList<>();
        posseInformal.setNomeEntidade("teste");
        DadosResponsavel dadosResponsavel = new DadosResponsavel();
        dadosResponsavel.setTipoPosseOcupacao(TipoPosseOcupacaoEnum.COLETIVO);
        validadorPosseInformal.validadorEspecifico(posseInformal);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarCampoEntidadeVazioEntidadePreenchidaComIdNulo(){
        PosseInformal posseInformal = new PosseInformal();
        List<String> erros = new ArrayList<>();
        posseInformal.setNomeEntidade("teste");
        TipoPosse tipoPosse = new TipoPosse();
        tipoPosse.setId(null);
        DadosResponsavel dadosResponsavel = new DadosResponsavel();
        dadosResponsavel.setTipoPosseOcupacao(TipoPosseOcupacaoEnum.COLETIVO);
        validadorPosseInformal.validadorEspecifico(posseInformal);
    }

}

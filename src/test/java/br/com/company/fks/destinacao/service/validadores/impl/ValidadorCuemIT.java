package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.FamiliaBeneficiada;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.TipoModalidade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 22/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ValidadorCuemIT extends BaseIntegrationTestCofig {

    @Autowired
    private ValidadorCuem validadorCuem;

    private List<String> erros;

    @Before
    public void setUp() {
        erros = new ArrayList<>();
    }

    @Test
    public void validarCampoTipoModalidadeVazioNull() {
        validadorCuem.validarCampoTipoModalidadeVazio(null, erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void validarCampoTipoModalidadeVazio() {
        validadorCuem.validarCampoTipoModalidadeVazio(new TipoModalidade(), erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioListaVazia() {
        validadorCuem.validarBeneficiario(Collections.emptyList(), new TipoModalidade(), erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioListaNula() {
        validadorCuem.validarBeneficiario(null, new TipoModalidade(), erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioTipoModalidadeVazio() {
        Responsavel responsavel = new Responsavel();
        List<FamiliaBeneficiada> familiaBeneficiadas = asList(criarFamiliaBeneficiada());
        responsavel.setFamiliasBeneficiadas(familiaBeneficiadas);
        List<Responsavel> responsaveis = asList(responsavel);
        validadorCuem.validarBeneficiario(responsaveis, new TipoModalidade(), erros);
        assertTrue(erros.isEmpty());
    }


    @Test
    public void validarBeneficiarioTipoModalidadeDiferenteColetivoResponsavelPessoaJuridicaPossuiFamiliasBeneficiadas() {
        Responsavel responsavel = new Responsavel();
        responsavel.setCpfCnpj("04240370000157");
        List<FamiliaBeneficiada> familiaBeneficiadas = asList(criarFamiliaBeneficiada(), criarFamiliaBeneficiada());
        responsavel.setFamiliasBeneficiadas(familiaBeneficiadas);
        List<Responsavel> responsaveis = asList(responsavel);
        validadorCuem.validarBeneficiario(responsaveis, criarTipoModalidade(1), erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioTipoModalidadeDiferenteColetivoResponsavelPessoaJuridicaNaoPossuiFamiliasBeneficiadas() {
        Responsavel responsavel = new Responsavel();
        responsavel.setCpfCnpj("04240370000157");
        List<FamiliaBeneficiada> familiaBeneficiadas = asList(criarFamiliaBeneficiada());
        responsavel.setFamiliasBeneficiadas(familiaBeneficiadas);
        List<Responsavel> responsaveis = asList(responsavel);
        validadorCuem.validarBeneficiario(responsaveis, criarTipoModalidade(1), erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioTipoModalidadeColetivoResponsavelPessoaJuridicaPossuiFamiliasBeneficiadas() {
        Responsavel responsavel = new Responsavel();
        responsavel.setCpfCnpj("04240370000157");
        List<FamiliaBeneficiada> familiaBeneficiadas = asList(criarFamiliaBeneficiada(), criarFamiliaBeneficiada());
        responsavel.setFamiliasBeneficiadas(familiaBeneficiadas);
        List<Responsavel> responsaveis = asList(responsavel);
        validadorCuem.validarBeneficiario(responsaveis, criarTipoModalidade(2), erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioTipoModalidadeColetivoResponsavelPessoaJuridicaNaoPossuiFamiliasBeneficiadas() {
        Responsavel responsavel = new Responsavel();
        responsavel.setCpfCnpj("04240370000157");
        List<FamiliaBeneficiada> familiaBeneficiadas = asList(criarFamiliaBeneficiada());
        responsavel.setFamiliasBeneficiadas(familiaBeneficiadas);
        List<Responsavel> responsaveis = asList(responsavel);
        validadorCuem.validarBeneficiario(responsaveis, criarTipoModalidade(2), erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioTipoModalidadeColetivoResponsavelPessoaJuridicaNaoPossuiFamiliasBeneficiadasCpf() {
        Responsavel responsavel = new Responsavel();
        responsavel.setCpfCnpj("00000000191");
        List<FamiliaBeneficiada> familiaBeneficiadas = asList(criarFamiliaBeneficiada());
        responsavel.setFamiliasBeneficiadas(familiaBeneficiadas);
        List<Responsavel> responsaveis = asList(responsavel);
        validadorCuem.validarBeneficiario(responsaveis, criarTipoModalidade(1), erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarBeneficiarioTipoModalidadeColetivoResponsavelPessoaJuridicaNaoPossuiFamiliasBeneficiadasDuasFamiliasBenefiadas() {
        Responsavel responsavel = new Responsavel();
        responsavel.setCpfCnpj("00000000191");
        List<FamiliaBeneficiada> familiaBeneficiadas = asList(criarFamiliaBeneficiada(), criarFamiliaBeneficiada());
        responsavel.setFamiliasBeneficiadas(familiaBeneficiadas);
        List<Responsavel> responsaveis = asList(responsavel);
        validadorCuem.validarBeneficiario(responsaveis, criarTipoModalidade(1), erros);
        assertTrue(erros.isEmpty());
    }

    private FamiliaBeneficiada criarFamiliaBeneficiada() {
        FamiliaBeneficiada familiaBeneficiada = new FamiliaBeneficiada();
        familiaBeneficiada.setCpfResponsavel("00000000191");
        return familiaBeneficiada;
    }

    private TipoModalidade criarTipoModalidade(Integer id) {
        TipoModalidade tipoModalidade = new TipoModalidade();
        tipoModalidade.setId(id);
        return tipoModalidade;
    }


}

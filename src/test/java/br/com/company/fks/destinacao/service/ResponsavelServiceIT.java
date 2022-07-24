package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.FamiliaBeneficiada;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.utils.Constants;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Basis Tecnologia on 21/11/2016.
 */

@Ignore
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ResponsavelServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private ResponsavelService responsavelService;

   /* @Test
    @SneakyThrows
    public void salvar(){
        Responsavel responsavel = responsavelService.salvar(criarResponsavel());
        assertNotNull(responsavel);
        assertNotNull(responsavel.getId());
    }

    @Test
    @SneakyThrows
    public void salvarListaResponsavelFamiliaBeneficiadaNull(){
        List<Responsavel> responsaveis = criarListaResponsavel();
        responsaveis.get(Constants.ZERO).setFamiliasBeneficiadas(null);
        List<Responsavel> retorno = responsavelService.salvar(responsaveis,criarDestinacao());
        assertNotNull(retorno);
        assertTrue(retorno.get(Constants.ZERO).getFamiliasBeneficiadas() == null);
    }

    @Test
    @SneakyThrows
    public void salvarListaResponsavelFamiliaBeneficiadaEmpty(){
        List<Responsavel> responsaveis = criarListaResponsavel();
        responsaveis.get(Constants.ZERO).setFamiliasBeneficiadas(Collections.EMPTY_LIST);
        List<Responsavel> retorno = responsavelService.salvar(responsaveis,criarDestinacao());
        assertNotNull(retorno);
        assertTrue(retorno.get(Constants.ZERO).getFamiliasBeneficiadas().isEmpty());
    }*/

   /* private Responsavel criarResponsavel(){
        Responsavel responsavel = new Responsavel();
        responsavel.setRegimeColetivoCnpj(true);
        responsavel.setFamiliasBeneficiadas(criarFamiliaBeneficiada());
        responsavel.setId(1L);
        return responsavel;
    }

    private List<FamiliaBeneficiada> criarFamiliaBeneficiada(){
        Responsavel responsavel = new Responsavel();
        responsavel.setId(1L);
        List<FamiliaBeneficiada> familiaBeneficiadas = new ArrayList<>();
        FamiliaBeneficiada familiaBeneficiada = new FamiliaBeneficiada();
        familiaBeneficiada.setId(1L);
        familiaBeneficiada.setResponsavel(responsavel);
        familiaBeneficiadas.add(familiaBeneficiada);
        return familiaBeneficiadas;
    }

    private List<Responsavel> criarListaResponsavel(){
        List<Responsavel> responsaveis = new ArrayList<>();
        responsaveis.add(criarResponsavel());
        return responsaveis;
    }

    private Destinacao criarDestinacao(){
        Destinacao destinacao = new Destinacao();
        destinacao.setId(1L);
        return destinacao;
    }*/

}
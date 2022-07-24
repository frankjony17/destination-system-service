package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.FamiliaBeneficiada;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.repository.FamiliaBeneficiadaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 18/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FamiliaBeneficiadaServiceTest {

    @InjectMocks
    private FamiliaBeneficiadaService familiaBeneficiadaService;

    @Mock
    private FamiliaBeneficiadaRepository familiaBeneficiadaRepository;

    @Mock
    private FamiliaBeneficiada familiaBeneficiada;

    @Mock
    private Responsavel responsavel;

    @Before
    public void setUp(){
        when(familiaBeneficiadaRepository.save(any(FamiliaBeneficiada.class))).thenReturn(familiaBeneficiada);
    }


    @Test
    public void salvar(){
        FamiliaBeneficiada familiaBeneficiadaTeste = familiaBeneficiadaService.salvar(familiaBeneficiada);
        Assert.assertNotNull(familiaBeneficiadaTeste);
    }

    @Test
    public void salvarListaFamiliares(){
        List<FamiliaBeneficiada> listaFamiliaBeneficiadas = new ArrayList<>();

        List<FamiliaBeneficiada> listaFamiliaBeneficiadasTeste =
                familiaBeneficiadaService.salvar(listaFamiliaBeneficiadas, responsavel);

        Assert.assertTrue(listaFamiliaBeneficiadas.isEmpty());
    }

    @Test
    public void salvarListaFamiliaresNotNull(){
        List<FamiliaBeneficiada> listaFamiliaBeneficiadas = new ArrayList<>();
        FamiliaBeneficiada familiaBeneficiada = new FamiliaBeneficiada();

        familiaBeneficiada.setId(Long.valueOf(1));
        familiaBeneficiada.setAreaUtilizada(Double.valueOf(100));
        familiaBeneficiada.setCpfConjuge("03247831169");
        familiaBeneficiada.setCpfResponsavel("03247831169");
        familiaBeneficiada.setNomeConjuge("Teste");
        familiaBeneficiada.setNomeResponsavel("Teste");
        familiaBeneficiada.setSequencial(1);

        listaFamiliaBeneficiadas.add(familiaBeneficiada);

        List<FamiliaBeneficiada> listaFamiliaBeneficiadasTeste =
                familiaBeneficiadaService.salvar(listaFamiliaBeneficiadas, responsavel);

        Assert.assertTrue(!listaFamiliaBeneficiadas.isEmpty());
    }



}
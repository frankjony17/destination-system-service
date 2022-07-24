package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Encargo;
import br.com.company.fks.destinacao.repository.EncargoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 18/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class EncargoServiceTest {


    @InjectMocks
    private EncargoService encargoService;

    @Mock
    private EncargoRepository encargoRepository;

    @Mock
    private Encargo encargo;

    @Before
    public void setUp(){
        when(encargoRepository.save(any(Encargo.class))).thenReturn(encargo);
    }

    @Test
    public void salvar(){
        Encargo encargoTeste = encargoService.salvar(encargo);
        Assert.assertNotNull(encargoTeste);
    }

    @Test
    public void salvarListaEncargosNull() {
        List<Encargo> listaEncargosTeste = null;
        List<Encargo> listaEncargos = encargoService.salvar(listaEncargosTeste);
        assertNull(null);
    }

    @Test
    public void salvarListaEncargosNotNull() {
        List<Encargo> listaEncargosTeste = new ArrayList<>();
        Encargo encargoTeste = new Encargo();

        encargo.setId(Long.valueOf(1));
        encargo.setDataCumprimento(new Date());
        encargo.setNome("Teste");
        encargo.setCumprimentoEncargo(true);

        listaEncargosTeste.add(encargoTeste);

        List<Encargo> listaEncargos = encargoService.salvar(listaEncargosTeste);
        Assert.assertTrue(!listaEncargos.isEmpty());
    }

    @Test
    public void listaEncargos(){
        List<Encargo> encargoList = new ArrayList<>();
        encargoList.add(encargo);
        when(encargoRepository.listaEncargos(anyLong())).thenReturn(encargoList);
        encargoService.listaEncargos(1L);
    }
}
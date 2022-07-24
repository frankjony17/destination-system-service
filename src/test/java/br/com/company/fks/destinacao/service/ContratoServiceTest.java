package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.repository.ContratoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContratoServiceTest {

    @InjectMocks
    private ContratoService contratoService;

    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private Contrato contrato;


    @Before
    public  void setUp(){
        when(contratoRepository.save(any(Contrato.class))).thenReturn(contrato);
    }

    @Test
    public void salvar() throws Exception {
        Contrato contratoTeste = contratoService.salvar(contrato, Boolean.TRUE);
        assertNotNull(contratoTeste);
    }

    @Test
    public void salvar2() throws Exception {
        Contrato contratoTeste = contratoService.salvar(contrato, Boolean.FALSE);
        assertNotNull(contratoTeste);
    }

    @Test
    public void salvarContratoNull() throws Exception {
        Contrato contratoTeste = contratoService.salvar(null, Boolean.FALSE);
        assertNull(contratoTeste);
    }

}
package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.AtoAutorizativo;
import br.com.company.fks.destinacao.repository.AtoAutorizativoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class AtoAutorizativoServiceTest {

    @Mock
    private AtoAutorizativoRepository atoAutorizativoRepository;

    @InjectMocks
    private AtoAutorizativoService service;

    @Mock
    private AtoAutorizativo atoAutorizativo;

    @Before
    public void setUp() throws Exception {
        when(atoAutorizativoRepository.save(any(AtoAutorizativo.class))).thenReturn(atoAutorizativo);
    }

    @Test
    public void salvar() throws Exception {
        AtoAutorizativo salvo = service.salvar(new AtoAutorizativo(), Boolean.TRUE);
        assertEquals(atoAutorizativo, salvo);
    }

    @Test
    public void salvar2() throws Exception {
        AtoAutorizativo salvo = service.salvar(new AtoAutorizativo(), Boolean.FALSE);
        assertEquals(atoAutorizativo, salvo);
    }

    @Test
    public void salvarNullo() throws Exception {
        AtoAutorizativo salvo = service.salvar(null, Boolean.FALSE);
        assertNull(salvo);
    }

}
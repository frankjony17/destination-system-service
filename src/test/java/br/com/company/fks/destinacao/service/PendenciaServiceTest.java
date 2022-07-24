package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Pendencia;
import br.com.company.fks.destinacao.repository.PendenciaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by diego on 17/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PendenciaServiceTest {

    private static final Long ID_PENDENCIA = 1L;
    private static final String DS_PENDENCIA = "Pendencia teste";

    @InjectMocks
    private PendenciaService pendenciaService;

    @Mock
    private PendenciaRepository pendenciaRepository;

    @Mock
    private Pendencia pendencia;

    @Before
    public void setUp () {
    }

    @Test
    public void findById() throws Exception {
        when(pendenciaRepository.findOne(anyLong())).thenReturn(pendencia);
        Pendencia pendenciaRecuperada = pendenciaService.findById(1L);
        assertNotNull(pendenciaRecuperada);
    }

    @Test
    public void getMapPendencias(){
        when(pendenciaRepository.findAll()).thenReturn(Collections.emptyList());
        Map<String, Pendencia> retorno = pendenciaService.getMapPendencias();
        assertNotNull(retorno);
    }
}

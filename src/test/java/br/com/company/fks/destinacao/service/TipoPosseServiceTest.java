package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import br.com.company.fks.destinacao.repository.TipoPosseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(PowerMockRunner.class)
public class TipoPosseServiceTest {

    @InjectMocks
    private TipoPosseService tipoPosseService;

    @Mock
    private TipoPosseRepository tipoPosseRepository;

    @Before
    public void setup() {
        given(tipoPosseRepository.findAll()).willReturn(Arrays.asList());
    }

    @Test
    public void findAll() {
        List<TipoPosse > test = tipoPosseService.findAll();
        assertNotNull(test);
    }
}

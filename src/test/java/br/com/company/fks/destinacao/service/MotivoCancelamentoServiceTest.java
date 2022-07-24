package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.MotivoCancelamento;
import br.com.company.fks.destinacao.repository.MotivoCancelamentoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
public class MotivoCancelamentoServiceTest {

    @InjectMocks
    private MotivoCancelamentoService motivoCancelamentoService;

    @Mock
    private MotivoCancelamentoRepository motivoCancelamentoRepository;

    @Before
    public void setUp(){ given(motivoCancelamentoRepository.findAll()).willReturn(Arrays.asList()); }

    @Test
    public void findAll(){
        List<MotivoCancelamento> test = motivoCancelamentoService.findAll();
        assertNotNull(test);
    }
}

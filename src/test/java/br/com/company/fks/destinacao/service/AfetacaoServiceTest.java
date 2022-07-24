package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Afetacao;
import br.com.company.fks.destinacao.repository.AfetacaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class AfetacaoServiceTest {

    @InjectMocks
    private AfetacaoService afetacaoService;

    @Mock
    private Afetacao afetacao;

    @Mock
    private AfetacaoRepository afetacaoRepository;

    @Test
    public void salvar(){
        when(afetacaoRepository.save(afetacao)).thenReturn(afetacao);
        assertNotNull(afetacaoService.salvar(afetacao));
    }
}

package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Financeiro;
import br.com.company.fks.destinacao.repository.FinanceiroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 17/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FinanceiroServiceTest {

    @InjectMocks
    private FinanceiroService financeiroService;

    @Mock
    private FinanceiroRepository financeiroRepository;

    @Mock
    private Financeiro financeiro;

    @Before
    public void setUp(){
        when(financeiroRepository.save(any(Financeiro.class))).thenReturn(financeiro);
    }


    @Test
    public void salvar() throws Exception {
        Financeiro financeiroTeste = financeiroService.salvar(financeiro, Boolean.TRUE);
        Assert.assertNotNull(financeiroTeste);
    }

    @Test
    public void salvar2() throws Exception {
        Financeiro financeiroTeste = financeiroService.salvar(financeiro, Boolean.FALSE);
        Assert.assertNotNull(financeiroTeste);
    }

    @Test
    public void salvarFinanceiroNull() throws Exception {
        Financeiro financeiroNull = null;

        Financeiro financeiroTeste = financeiroService.salvar(financeiroNull, Boolean.FALSE);
        Assert.assertNull(financeiroTeste);
    }


}
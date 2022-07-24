package br.com.company.fks.destinacao.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 03/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MensagemNegocioTest {

    @InjectMocks
    private MensagemNegocio mensagemNegocio;

    @Mock
    private MessageSource messageSource;

    @Mock
    private MessageSourceAccessor accessor;

    @Test
    public void getMensagem() {
        when(accessor.getMessage(anyString())).thenReturn("teste");
        String mensagem = mensagemNegocio.get("teste");
        assertEquals("teste", mensagem);
    }

    @Test
    public void init() {
        mensagemNegocio.init();
        assertNotNull(accessor);
    }
}

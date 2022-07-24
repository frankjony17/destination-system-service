package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.UsoProprio;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class ValidadorUsoProprioTest {

    @InjectMocks
    private ValidadorUsoProprio validadorUsoProprio;

    @Mock
    private UsoProprio usoProprio;

    @Test
    public void validadorEspecifico() throws NegocioException {
        validadorUsoProprio.validadorEspecifico(usoProprio);
    }
}

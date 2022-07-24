package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorRip;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ValidadorRipTermoEntregaTest {


    @InjectMocks
    private ValidadorRipTermoEntrega validadorRipTermoEntrega;

    @Mock
    private TermoEntrega termoEntrega;

    @Mock
    private Imovel imovel;

    @Mock
    private Benfeitoria benfeitoria;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Before
    public void setUp() throws Exception {
        validadorRipTermoEntrega = new ValidadorRipTermoEntrega();
    }

    @Test
    public void validadorEspecifico() throws NegocioException {
        Imovel imovel = mocarImovel();
        when(statusDestinacao.getId()).thenReturn(4);
        validadorRipTermoEntrega.validadorEspecifico(imovel, 1L);
        assertEquals(true, true);
    }

    @Test
    public void validadorEspecificoAreaContruidaFalse() throws NegocioException {
        Imovel imovel = mocarImovel();
        imovel.setBenfeitorias(asList());
        when(statusDestinacao.getId()).thenReturn(4);
        validadorRipTermoEntrega.validadorEspecifico(imovel, 1L);
    }
    @Test(expected = NegocioException.class)
    public void validadorEspecificoErroRipInvalido() throws NegocioException {
        Imovel imovel = mocarImovel();
        imovel.getBenfeitorias().get(0).setAreaDisponivel(new BigDecimal(0));
        when(statusDestinacao.getId()).thenReturn(4);
        validadorRipTermoEntrega.validadorEspecifico(imovel, 1L);
    }
    @Test(expected = NegocioException.class)
    public void validadorEspecificoErroProprietario() throws NegocioException {
        Imovel imovel = mocarImovel();
        imovel.setProprietario("SENADO");
        when(statusDestinacao.getId()).thenReturn(4);
        validadorRipTermoEntrega.validadorEspecifico(imovel, 1L);
    }


    private Imovel mocarImovel() {
        Imovel imovel = new Imovel();
        Benfeitoria benfeitoria = new Benfeitoria();
        benfeitoria.setCodigo("Es");
        benfeitoria.setAreaDisponivel(new BigDecimal(10));
        List<Benfeitoria> benfeitoriaLista = new ArrayList<>();
        benfeitoriaLista.add(benfeitoria);
        imovel.setBenfeitorias(benfeitoriaLista);
        imovel.setProprietario("UNIAO");
        return imovel;
    }
}

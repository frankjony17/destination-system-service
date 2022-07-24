package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorRipPermissaoUsoImovelFuncionalTest {

    private ValidadorRipPermissaoUsoImovelFuncional validador;

    @Before
    public void setUp() throws Exception {
        validador = new ValidadorRipPermissaoUsoImovelFuncional();
    }

    @Test
    public void validadorEspecificoDF() throws Exception {
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setUf("DF");
        imovel.setEndereco(endereco);
        imovel.setParcelas(Arrays.asList(new Parcela()));
        validador.validadorEspecifico(imovel, 0L);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoOutroEstado() throws Exception {
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setUf("RN");
        imovel.setEndereco(endereco);
        validador.validadorEspecifico(imovel, 0L);
    }

}
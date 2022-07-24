package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Basis Tecnologia on 21/10/2016.
 */
@RunWith(PowerMockRunner.class)
public class CNPJUtilsTest {

    private static final String CNPJ_VALIDO = "04240370000157";
    private static final String CNPJ_INVALIDO = "22222222223311";
    private static final String CNPJ_BLACK_LIST= "00000000000000";

    @Test
    @SneakyThrows
    public void isCNPJValido() {
        Boolean cnpjValido = CNPJUtils.isCNPJ(CNPJ_VALIDO);
        assertTrue(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void CNPJInvalidoNumerosSequenciais() {
        Boolean cnpjValido = CNPJUtils.isCNPJ(CNPJ_BLACK_LIST);
        assertFalse(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void CNPJInvalidoQuantidadedigitos() {
        Boolean cnpjValido = CNPJUtils.isCNPJ("0000000");
        assertFalse(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void isCNPJInvalido() {
        Boolean cnpjValido = CNPJUtils.isCNPJ(CNPJ_INVALIDO);
        assertTrue(!cnpjValido);
    }

    @Test
    @SneakyThrows
    public void isCNPJInvalidoDigito13() {
        Boolean cnpjValido = CNPJUtils.isCNPJ("46049971000128");
        assertTrue(!cnpjValido);
    }

    @Test
    @SneakyThrows
    public void isCNPJInvalidoDigito12() {
        Boolean cnpjValido = CNPJUtils.isCNPJ("35049971000127");
        assertTrue(!cnpjValido);
    }

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<CNPJUtils> constructor = CNPJUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 17/05/17.
 */
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
public class CPFUtilsIT {

    private static final String CPF_VALIDO = "11344601200";
    private static final String CPF_INVALIDO = "11344601201";
    private static final String CPF_INVALIDO_ALFA_NUMERICO = "0000000000A";
    private static final String CPF_PADRAO = "11111111111";
    private static final String CPF_INVALIDO_SOMA_DIGITOS = "11111111122";

    @Test
    @SneakyThrows
    public void isCPFalido() {
        Boolean cnpjValido = CPFUtils.validar(CPF_VALIDO);
        assertTrue(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void isCPFInvalido() {
        Boolean cnpjValido = CPFUtils.validar(CPF_INVALIDO);
        assertFalse(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void isCPFInvalidoCpfNull() {
        Boolean cnpjValido = CPFUtils.validar(null);
        assertFalse(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void isCPFInvalidoCpfAlfanumerico() {
        Boolean cnpjValido = CPFUtils.validar(CPF_INVALIDO_ALFA_NUMERICO);
        assertFalse(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void isCPFInvalidoCpfPadrao() {
        Boolean cnpjValido = CPFUtils.validar(CPF_PADRAO);
        assertFalse(cnpjValido);
    }

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<CPFUtils> constructor = CPFUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    @SneakyThrows
    public void cpfInvalidoSomaDigitos() {
        Boolean cnpjValido = CPFUtils.validar(CPF_INVALIDO_SOMA_DIGITOS);
        assertFalse(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void cpfInvalidoSomaDigitosModUm() {
        Boolean cnpjValido = CPFUtils.validar("08810374607");
        assertFalse(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void cpfInvalidoSomaDigitosModZero() {
        Boolean cnpjValido = CPFUtils.validar("48810374606");
        assertFalse(cnpjValido);
    }

    @Test
    @SneakyThrows
    public void isCPFInvalidoQtdDigitosDiferentes() {
        Boolean cnpjValido = CPFUtils.validar("123");
        assertFalse(cnpjValido);
    }
}

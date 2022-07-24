package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 17/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class EmailUtilsIT extends BaseIntegrationTestCofig {

    private static final String EMAIL_VALIDO = "teste@teste.com.br";
    private static final String EMAIL_INVALIDO = "teste@teste";

    @Autowired
    private EmailUtils emailUtils;

    @Test
    public void validarEmailValido() {
        Boolean emailValido = emailUtils.validarEmail(EMAIL_VALIDO);
        assertTrue(emailValido);
    }

    @Test
    public void validarEmailInvalido() {
        Boolean emailValido = emailUtils.validarEmail(EMAIL_INVALIDO);
        assertTrue(!emailValido);
    }

}

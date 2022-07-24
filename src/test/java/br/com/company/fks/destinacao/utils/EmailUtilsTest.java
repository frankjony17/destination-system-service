package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.enums.EmailEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 24/11/16.
 */
@RunWith(PowerMockRunner.class)
public class EmailUtilsTest {

    private static final String EMAIL_VALIDO = "teste@teste.com.br";
    private static final String EMAIL_INVALIDO = "teste@teste";

    @InjectMocks
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

    @Test
    public void substituirParamentrosTemplate() {
        String template = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n" +
                "    <title></title>\n</head>\n<body>\n<p>Caro Requerente,</p>\n<br />\n\n" +
                "<p>Seu requerimento foi cancelado. Para visualizar os motivos acesse seu requerimento no <a href=\"http://e-spu.planejamento.gov.br\">e-spu.planejamento.gov.br</a> informando o seu CPF/CNPJ e o Número do Atendimento.</p>\n" +
                "\n<br />\n\n<p><b>teste</b></p>\n<p><b>Número do Atendimento:</b> 123</p>\n" +
                "<p><b>Requerente:</b> desconhecido</p>\n\n<br>\n\n" +
                "<p><b>Este e-mail está sendo enviado de forma automática. Favor não responder.</b></p>\n" +
                "</body>\n</html>";
        String resultado = emailUtils.getEmail(EmailEnum.CANCELAMENTO, "teste", "123", "desconhecido");
        assertEquals(template, resultado);
    }
}

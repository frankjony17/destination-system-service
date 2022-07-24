package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.CessaoGratuita;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Created by diego on 22/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ValidadorCessaoGratuitaIT extends BaseIntegrationTestCofig {

    @Autowired
    private ValidadorCessaoGratuita validadorCessaoGratuita;

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoGerandoErro() {
        CessaoGratuita cessaoGratuita = new CessaoGratuita();
        validadorCessaoGratuita.validadorEspecifico(cessaoGratuita);
    }

}

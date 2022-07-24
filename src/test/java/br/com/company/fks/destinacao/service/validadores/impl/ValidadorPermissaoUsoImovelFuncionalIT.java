package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.PermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ValidadorPermissaoUsoImovelFuncionalIT extends BaseIntegrationTestCofig {

    @Autowired
    private ValidadorPermissaoUsoImovelFuncional validadorPermissaoUsoImovelFuncional;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoGerandoErro(){
        PermissaoUsoImovelFuncional permissaoUsoImovelFuncional = new PermissaoUsoImovelFuncional();
        validadorPermissaoUsoImovelFuncional.validar(permissaoUsoImovelFuncional);


    }


}

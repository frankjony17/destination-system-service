package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ValidadorRipPermissaoUsoImovelFuncionalIT extends BaseIntegrationTestCofig {

    @Autowired
    private  ValidadorRipPermissaoUsoImovelFuncional validadorRipPermissaoUsoImovelFuncional;

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoGerandoErros() throws NegocioException {
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setUf("DF");
        imovel.setEndereco(endereco);
        imovel.setParcelas(Arrays.asList(new Parcela()));
        validadorRipPermissaoUsoImovelFuncional.validadorEspecifico(imovel, 1L);
        endereco.setUf("MG");
        imovel.setEndereco(endereco);
        validadorRipPermissaoUsoImovelFuncional.validadorEspecifico(imovel, 1L);
    }
}

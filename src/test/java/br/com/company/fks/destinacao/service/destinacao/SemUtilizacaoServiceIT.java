package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.apache.commons.lang.NotImplementedException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Created by diego on 19/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class SemUtilizacaoServiceIT extends BaseIntegrationTestCofig {

    @Autowired
    private SemUtilizacaoService semUtilizacaoService;

    @Autowired
    private EntityConverter entityConverter;

    @Test(expected = NotImplementedException.class)
    public void salvarDadosEspecificosSemImplementacao() {
        semUtilizacaoService.salvarDadosEspecificos(null);
    }

}

package br.com.company.fks.destinacao.service.analiseTecnica;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 18/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class StatusAnaliseTecnicaStrategyIT extends BaseIntegrationTestCofig {

    @Test
    public void buscarStatusAnalisePorDespachoPorPermisao() {
        StatusAnaliseDespacho statusAnaliseDespacho =
                StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(PermissaoAnaliseEnum.EXEC_ANALISE_SECRETARIO);
        assertTrue(statusAnaliseDespacho instanceof StatusAnaliseDespachoSecretario);
    }

    @Test
    public void buscarStatusAnalisePorDespachoPorUsuario() {

        Set<String> permissoes = new HashSet<>();
        permissoes.add("DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO");
        usuarioLogadoDTO.setPermissoes(permissoes);

        StatusAnaliseDespacho statusAnaliseDespacho =
                StatusAnaliseTecnicaStrategy.buscarStatusAnalisePorDespachoPorPermisao(usuarioLogadoDTO);
        assertTrue(statusAnaliseDespacho instanceof StatusAnaliseDespachoSecretario);
    }

}

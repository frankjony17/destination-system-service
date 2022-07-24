package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.Destinatario;
import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by diego on 23/01/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class TransferenciaTitularidadeServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private TransferenciaTitularidadeService transferenciaTitularidadeService;

    @Ignore
    @Test
    @SneakyThrows
    public void salvarSemAtosComplementares(){
        TransferenciaTitularidade transferenciaTitularidade = new TransferenciaTitularidade();
        Destinatario destinatario = new Destinatario();
        destinatario.setId(1L);
        transferenciaTitularidade.setId(1L);
        transferenciaTitularidade.setDestinatario(destinatario);
        TransferenciaTitularidade retorno = transferenciaTitularidadeService.salvar(transferenciaTitularidade);
        assertNotNull(retorno.getId());
    }

}
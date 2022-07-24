package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by samuel on 12/05/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")

public class UtilizacaoDTOIT {

    private static final String VALOR_DEFAULT = "123";
    private static final String RIP = "123";
    private static final String CODIGO_UTILIZACAO = "123";
    private static final String SEQUENCIAL = "0000";
    private static final String CNPJ = "123";
    private static final String RAZAO_SOCIAL = "TESTE";


    @Test
    public void testaConstrutor() {
        UtilizacaoDTO utilizacaoDTO = new UtilizacaoDTO (RIP, CODIGO_UTILIZACAO, SEQUENCIAL, VALOR_DEFAULT, RAZAO_SOCIAL, CNPJ);
        String codigoUtilizacaoEsperado = RIP + "/" + CODIGO_UTILIZACAO + SEQUENCIAL;
        String destinatarioEsperado = CNPJ + "-" + RAZAO_SOCIAL;

        assertEquals(codigoUtilizacaoEsperado, utilizacaoDTO.getCodigoUtilizacao());
        assertEquals(VALOR_DEFAULT, utilizacaoDTO.getInstrumento());
        assertEquals(destinatarioEsperado, utilizacaoDTO.getResponsavelDestinatario());
    }

}

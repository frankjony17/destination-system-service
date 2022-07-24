package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by samuel on 12/05/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class HistoricoAnaliseTecnicaDTOIT {

    private static final String JUSTIFICATIVA = "testa";
    private static final String NOME_USUARIO = "usuario";
    private static final long ID_HISTORICO = 1L;

    @Test
    public void testaInstancia() {
        Date date = new Date();
        HistoricoAnaliseTecnicaDTO historicoDeAnaliseTecnicaDTO = new HistoricoAnaliseTecnicaDTO(ID_HISTORICO, date, new StatusAnaliseTecnica(), new StatusAnaliseTecnica(), JUSTIFICATIVA, NOME_USUARIO);
        assertEquals(NOME_USUARIO, historicoDeAnaliseTecnicaDTO.getNomeUsuario());
        assertEquals(ID_HISTORICO, historicoDeAnaliseTecnicaDTO.getId().longValue());
        assertEquals(JUSTIFICATIVA, historicoDeAnaliseTecnicaDTO.getJustificativa());
    }

    @Test
    public void getDataAlteracaoNula() {
        HistoricoAnaliseTecnicaDTO historicoDeAnaliseTecnicaDTO = new HistoricoAnaliseTecnicaDTO();
        historicoDeAnaliseTecnicaDTO.setDataAlteracao(null);
        assertNull(historicoDeAnaliseTecnicaDTO.getDataAlteracao());
    }

    @Test
    public void getDataAlteracaoNaoNula() {
        HistoricoAnaliseTecnicaDTO historicoDeAnaliseTecnicaDTO = new HistoricoAnaliseTecnicaDTO();
        Date data = new Date();
        historicoDeAnaliseTecnicaDTO.setDataAlteracao(data);
        assertEquals(data, historicoDeAnaliseTecnicaDTO.getDataAlteracao());
    }
}

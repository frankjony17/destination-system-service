package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import org.junit.Assert;
import org.junit.Test;
import org.opensaml.xml.signature.J;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by diego on 04/01/17.
 */
public class HistoricoAnaliseTecnicaDTOTest {

    private static final String JUSTIFICATIVA = "teste";
    private static final String NOME_USUARIO = "usuario";
    private static final long ID_HISTORICO = 1L;

    @Test
    public void testaInstancia() {
        Date data = new Date();
        HistoricoAnaliseTecnicaDTO historicoAnaliseTecnicaDTO =
                new HistoricoAnaliseTecnicaDTO(ID_HISTORICO, data, new StatusAnaliseTecnica(), new StatusAnaliseTecnica(), JUSTIFICATIVA, NOME_USUARIO);
        assertEquals(ID_HISTORICO, historicoAnaliseTecnicaDTO.getId().longValue());
        assertEquals(NOME_USUARIO, historicoAnaliseTecnicaDTO.getNomeUsuario());
        assertEquals(JUSTIFICATIVA, historicoAnaliseTecnicaDTO.getJustificativa());
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

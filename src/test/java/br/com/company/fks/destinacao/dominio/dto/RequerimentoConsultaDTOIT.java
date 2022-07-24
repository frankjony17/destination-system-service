package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by samuel on 12/05/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")

public class RequerimentoConsultaDTOIT {

    private RequerimentoConsultaDTO requerimentoConsultaDTO;
    private String dataString;
    private Date data;

    @Before
    public void setUp() throws ParseException {
        requerimentoConsultaDTO = new RequerimentoConsultaDTO();
        dataString = "2017-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        data = sdf.parse(dataString);
    }

    @Test
    public void setDataSolicitacaoInicio() throws ParseException {
        requerimentoConsultaDTO.setDataSolicitacaoInicio(dataString);
        assertEquals(data, requerimentoConsultaDTO.getDataSolicitacaoInicio());
    }

    @Test
    public void setDataSolicitacaoFinal() throws ParseException {
        requerimentoConsultaDTO.setDataSolicitacaoFinal(dataString);
        assertEquals(data, requerimentoConsultaDTO.getDataSolicitacaoFinal());
    }

    @Test
    public void setDataEnvioAnaliseInicio() throws ParseException {
        requerimentoConsultaDTO.setDataEnvioAnaliseInicio(dataString);
        assertEquals(data, requerimentoConsultaDTO.getDataEnvioAnaliseInicio());
    }

    @Test
    public void setDataEnvioAnaliseFinal() throws ParseException {
        requerimentoConsultaDTO.setDataEnvioAnaliseFinal(dataString);
        assertEquals(data, requerimentoConsultaDTO.getDataEnvioAnaliseFinal());
    }

    @Test
    public void setDataSolicitacaoInicioNula() throws ParseException {
        requerimentoConsultaDTO.setDataSolicitacaoInicio(null);
        assertNull(requerimentoConsultaDTO.getDataSolicitacaoInicio());
    }

    @Test
    public void setDataSolicitacaoFinalNula() throws ParseException {
        requerimentoConsultaDTO.setDataSolicitacaoFinal(null);
        assertNull(requerimentoConsultaDTO.getDataSolicitacaoFinal());
    }

    @Test
    public void setDataEnvioAnaliseInicioNula() throws ParseException {
        requerimentoConsultaDTO.setDataEnvioAnaliseInicio(null);
        assertNull(requerimentoConsultaDTO.getDataEnvioAnaliseInicio());
    }

    @Test
    public void setDataEnvioAnaliseFinalNula() throws ParseException {
        requerimentoConsultaDTO.setDataEnvioAnaliseFinal(null);
        assertNull(requerimentoConsultaDTO.getDataEnvioAnaliseFinal());
    }

}

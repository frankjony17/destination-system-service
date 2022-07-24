package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNull;

/**
 * Created by diego on 04/01/17.
 */
public class RequerimentoConsultaDTOTest {

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
        Assert.assertEquals(data, requerimentoConsultaDTO.getDataSolicitacaoInicio());
    }

    @Test
    public void setDataSolicitacaoFinal() throws ParseException {
        requerimentoConsultaDTO.setDataSolicitacaoFinal(dataString);
        Assert.assertEquals(data, requerimentoConsultaDTO.getDataSolicitacaoFinal());
    }

    @Test
    public void setDataEnvioAnaliseInicio() throws ParseException {
        requerimentoConsultaDTO.setDataEnvioAnaliseInicio(dataString);
        Assert.assertEquals(data, requerimentoConsultaDTO.getDataEnvioAnaliseInicio());
    }

    @Test
    public void setDataEnvioAnaliseFinal() throws ParseException {
        requerimentoConsultaDTO.setDataEnvioAnaliseFinal(dataString);
        Assert.assertEquals(data, requerimentoConsultaDTO.getDataEnvioAnaliseFinal());
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

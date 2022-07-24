package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DadosPessoaHistoricoCampoDTOIT {

    DadosPessoaHistoricoCampoDTO dadosPessoaHistoricoCampoDTO;

    @Before
    public void setUp(){
        dadosPessoaHistoricoCampoDTO = new DadosPessoaHistoricoCampoDTO();}


    @Test
    public void getData(){
        Date data = new Date();
        dadosPessoaHistoricoCampoDTO.setData(data);
        Assert.assertEquals(data, dadosPessoaHistoricoCampoDTO.getData());
    }

    @Test
    public void dataNull(){
        dadosPessoaHistoricoCampoDTO.setData(null);
        Assert.assertEquals(null, dadosPessoaHistoricoCampoDTO.getData());
    }

    @Test
    public void fonteNull(){
        dadosPessoaHistoricoCampoDTO.setFonte(null);
        Assert.assertEquals(null , dadosPessoaHistoricoCampoDTO.getFonte());
    }

    @Test
    public void valorNull(){
        dadosPessoaHistoricoCampoDTO.setValor(null);
        Assert.assertEquals(null, dadosPessoaHistoricoCampoDTO.getValor());
    }

}

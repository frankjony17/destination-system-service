package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DadosPessoaConsultaDTOTest {

    @Test
    public void buildQueryStringTest() throws Exception {
        DadosPessoaConsultaDTO dto = new DadosPessoaConsultaDTO("0123456789", "0123456789", "Funcionalidade", 24, "127.0.0.1", new FundamentoLegalDTO(), "teste");

    }

}
package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


/**
 * Created by diego on 06/04/17.
 */
public class UtilizacaoDTOTest {

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

    @Test
    public void setDataEfetivacaoNull() {
        Date data = null;
        UtilizacaoDTO dto = new UtilizacaoDTO();
        dto.setDataEfetivacaoUtilizacao(data);
    }

    @Test
    public void setDataEfetivacao() {
        Date data = new Date();
        UtilizacaoDTO dto = new UtilizacaoDTO();
        dto.setDataEfetivacaoUtilizacao(data);
    }

    @Test
    public void getDataEfetivacaoNull() {
        UtilizacaoDTO dto = new UtilizacaoDTO();
        dto.getDataEfetivacaoUtilizacao();
    }

    @Test
    public void getDataEfetivacao() {
        Date date = new Date();
        UtilizacaoDTO dto = new UtilizacaoDTO();
        dto.setDataEfetivacaoUtilizacao(date);
        dto.getDataEfetivacaoUtilizacao();
    }

    @Test
    public void setDataUtilizacaoNull(){
        Date data = null;
        UtilizacaoDTO dto = new UtilizacaoDTO();
        dto.setDataUtilizacao(data);
    }

    @Test
    public void setDataUtilizacao(){
        Date data = new Date();
        UtilizacaoDTO dto = new UtilizacaoDTO();
        dto.setDataUtilizacao(data);
    }

    @Test
    public void getDataUtilizacaoNull(){
        UtilizacaoDTO dto = new UtilizacaoDTO();
        dto.getDataUtilizacao();
    }

    @Test
    public void getDataUtilizacao(){
        Date date = new Date();
        UtilizacaoDTO dto = new UtilizacaoDTO();
        dto.setDataUtilizacao(date);
        dto.getDataUtilizacao();
    }
}
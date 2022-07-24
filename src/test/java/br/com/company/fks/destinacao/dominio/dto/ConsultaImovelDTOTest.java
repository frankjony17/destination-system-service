package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

public class ConsultaImovelDTOTest {

    @Test
    public void testa_uso_dos_getters_e_setters() {
        ConsultaImovelDTO consultaImovelDTO = new ConsultaImovelDTO();
        FundamentoLegalDTO fundamentoLegal = new FundamentoLegalDTO();
        Long idModalidade = new Long(1L);
        consultaImovelDTO.setRip("teste");
        consultaImovelDTO.setSequencialParcela("sequencialParcela");
        consultaImovelDTO.setTipoDestinacao("tipoDestinacao");
        consultaImovelDTO.setFundamentoLegal(fundamentoLegal);
        consultaImovelDTO.setIdModalidade(1L);

        Assert.assertEquals("teste", consultaImovelDTO.getRip());
        Assert.assertEquals("sequencialParcela", consultaImovelDTO.getSequencialParcela());
        Assert.assertEquals("tipoDestinacao", consultaImovelDTO.getTipoDestinacao());
        Assert.assertEquals(fundamentoLegal, consultaImovelDTO.getFundamentoLegal());
        Assert.assertEquals(idModalidade, consultaImovelDTO.getIdModalidade());

    }
}

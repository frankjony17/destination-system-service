package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MotivacaoSuspensaoImovelDTOTest {

    private MotivacaoSuspensaoImovelDTO motivacaoSuspensaoImovelDTO;
    private static final long ID = 1;
    private static final String descricao = "descricao";
    private static final long ORDEM = 1;
    private static final String codigoSistemico = "codigoSistemico";

    @Test
    public void testaConstrutor(){
        MotivacaoSuspensaoImovelDTO motivacaoSuspensaoImovelDTO = new MotivacaoSuspensaoImovelDTO(1L, descricao, 1L, codigoSistemico);
        assertEquals(descricao, motivacaoSuspensaoImovelDTO.getDescricao());
        assertEquals(codigoSistemico, motivacaoSuspensaoImovelDTO.getCodigoSistemico());
    }
}

package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class RipSuspensaoImovelDTOTest {

    @InjectMocks
    private RipSuspensaoImovelDTO ripSuspensaoImovelDTO;

    @Mock
    private SuspensaoImovelDTO suspensaoImovelDTO;

    @Test
    public void ripSuspensao(){
        ripSuspensaoImovelDTO.getRip();
    }
}

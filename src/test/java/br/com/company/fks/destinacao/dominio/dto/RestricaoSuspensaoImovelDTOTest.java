package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class RestricaoSuspensaoImovelDTOTest {

    @InjectMocks
    private RestricaoSuspensaoImovelDTO restricaoSuspensaoImovelDTO;

    @Test
    public void RestricaoSuspensaoImovelDTO(){
        restricaoSuspensaoImovelDTO.getId();
        restricaoSuspensaoImovelDTO.getDescricao();
        restricaoSuspensaoImovelDTO.getPermissao();
        restricaoSuspensaoImovelDTO.getOrdem();
        restricaoSuspensaoImovelDTO.getCodigoSistemico();
    }
}

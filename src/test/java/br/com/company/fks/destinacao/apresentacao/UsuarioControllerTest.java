package br.com.company.fks.destinacao.apresentacao;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.builder.ServicosResposta;
import br.com.company.fks.destinacao.service.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by guilherme on 02/03/17.
 */


@RunWith(PowerMockRunner.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getUsuarioLogado() throws Exception {
        when(usuarioService.getUsuarioLogado()).thenReturn(new UsuarioLogadoDTO());
        ResponseEntity<ServicosResposta> resposta =
                usuarioController.getUsuarioLogado();
        assertNotNull(resposta);
    }

}
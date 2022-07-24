package br.com.company.fks.destinacao.service;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.gov.mpog.acessos.cliente.servico.AcessosClienteService;
import br.com.company.fks.destinacao.dominio.enums.PerfilEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 23/11/16.
 */
@RunWith(PowerMockRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Mock
    private AcessosClienteService acessosClienteService;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(usuarioService, "idSistema", "1");
        when(usuarioLogadoDTO.getJurisdicoes()).thenReturn(new HashSet<>(asList("DF")));
        when(usuarioLogadoDTO.getId()).thenReturn(1L);
        when(acessosClienteService.buscarUsuarioLogado()).thenReturn(usuarioLogadoDTO);
    }

    @Test
    public void getUsuarioLogadoComJurisdicoes() {
        UsuarioLogadoDTO usuarioLogadoDTO = usuarioService.getUsuarioLogado();
        assertNotNull(usuarioLogadoDTO);
        assertTrue(!usuarioLogadoDTO.getJurisdicoes().isEmpty());
    }

    @Test
    public void getUsuarioLogadoSemJurisdicoes() {
        when(usuarioLogadoDTO.getJurisdicoes()).thenReturn(new HashSet<>());
        UsuarioLogadoDTO usuarioLogadoDTO = usuarioService.getUsuarioLogado();
        assertNotNull(usuarioLogadoDTO);
        assertTrue(usuarioLogadoDTO.getJurisdicoes().isEmpty());
    }

    @Test
    public void getPerfil() {
        when(usuarioLogadoDTO.getPerfil()).thenReturn(PerfilEnum.TECNICO.getNome());
        PerfilEnum perfilEnum = usuarioService.getPerfil();
        assertNotNull(perfilEnum);
        assertEquals(PerfilEnum.TECNICO, perfilEnum);
    }


}

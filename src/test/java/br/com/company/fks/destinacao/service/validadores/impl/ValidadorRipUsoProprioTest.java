package br.com.company.fks.destinacao.service.validadores.impl;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ValidadorRipUsoProprioTest {

    @InjectMocks
    private ValidadorRipUsoProprio validadorRipUsoProprio;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private Imovel imovel;

    private UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();

    @Before
    public void setUp(){
        imovel = new Imovel();

    }

    @Test(expected = NegocioException.class)
    public void validadorEspecifico() throws NegocioException {

        usuarioLogadoDTO.setCpf("2");
        imovel.setProprietario(";1");

        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        validadorRipUsoProprio.validadorEspecifico(imovel, 1L);
    }

    @Test
    public void validadorEspecificoFalse() throws NegocioException {

        usuarioLogadoDTO.setCpf("");
        imovel.setProprietario(";");

        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        validadorRipUsoProprio.validadorEspecifico(imovel, 1L);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecifico2() throws NegocioException {

        usuarioLogadoDTO.setIdOrganizacao(1L);
        usuarioLogadoDTO.setOrganizacao("3");
        imovel.setProprietario("-");

        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        validadorRipUsoProprio.validadorEspecifico(imovel, 1L);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecifico3() throws NegocioException {

        usuarioLogadoDTO.setOrganizacao("3");
        imovel.setProprietario("");

        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        validadorRipUsoProprio.validadorEspecifico(imovel, 1L);
    }

    @Test
    public void validadorEspecifico4() throws NegocioException {

        usuarioLogadoDTO.setOrganizacao("3");
        imovel.setProprietario("3");

        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        validadorRipUsoProprio.validadorEspecifico(imovel, 1L);
    }
}

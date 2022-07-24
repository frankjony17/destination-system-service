package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Destinatario;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.TipoDestinatario;
import br.com.company.fks.destinacao.repository.DestinatarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DestinatarioServiceTest {

    @InjectMocks
    private DestinatarioService destinatarioService;

    @Mock
    private DestinatarioRepository destinatarioRepository;

    @Mock
    private Destinatario destinatario;

    @Mock
    private TipoDestinatario tipoDestinatario;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private Endereco endereco;

    @Mock
    private EnderecoCorrespondenciaService enderecoCorrespondenciaService;

    @Before
    public void setUp(){
        given(enderecoService.salvar(any(Endereco.class))).willReturn(endereco);
        given(destinatarioRepository.save(any(Destinatario.class))).willReturn(destinatario);
    }

    @Test
    public void salvar() {
        when(tipoDestinatario.getDescricao()).thenReturn("Estatal");
        assertNotNull(destinatarioService.salvar(destinatario, tipoDestinatario));
    }

    @Test
    public void salvarFalse() {
        when(tipoDestinatario.getDescricao()).thenReturn("teste");
        assertNotNull(destinatarioService.salvar(destinatario, tipoDestinatario));
    }

    @Test
    public void salvarNulo() {
        when(enderecoService.salvar(endereco)).thenReturn(endereco);
        assertNull(destinatarioService.salvar(null, null));
    }

}

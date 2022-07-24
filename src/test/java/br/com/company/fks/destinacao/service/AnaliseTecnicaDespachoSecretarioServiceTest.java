package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSecretario;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoSecretarioRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 30/12/16.
 */
@RunWith(PowerMockRunner.class)
public class AnaliseTecnicaDespachoSecretarioServiceTest {

    @InjectMocks
    private AnaliseTecnicaDespachoSecretarioService analiseTecnicaDespachoSecretarioService;

    @Mock
    private AnaliseTecnicaDespachoSecretarioRepository analiseTecnicaDespachoSecretarioRepository;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private AnaliseTecnicaDespachoSecretario analiseTecnicaDespachoSecretario;

    @Mock
    private DespachoDTO despachoDTO;

    @Mock
    private AnaliseTecnicaDespachoID analiseTecnicaDespachoID;

    @Mock
    private AnaliseTecnica analiseTecnica;

    @Mock
    private Despacho despacho;

    @Before
    public void setUp() {
        when(analiseTecnicaDespachoSecretario.getAnaliseTecnicaDespachoID()).thenReturn(analiseTecnicaDespachoID);
        when(analiseTecnicaDespachoSecretarioRepository.save(any(AnaliseTecnicaDespachoSecretario.class))).thenReturn(analiseTecnicaDespachoSecretario);
        when(entityConverter.converterStrict(any(DespachoDTO.class), eq(Despacho.class))).thenReturn(despacho);
        when(despacho.getJustificativa()).thenReturn("teste");
    }

    @Test
    public void salvar() {
        AnaliseTecnicaDespachoSecretario analiseSalvar =
                analiseTecnicaDespachoSecretarioService.salvar(analiseTecnicaDespachoSecretario);
        assertNotNull(analiseSalvar);
    }

    @Test
    public void salvarLista() {
        List<AnaliseTecnicaDespachoSecretario> analises =
                analiseTecnicaDespachoSecretarioService.salvar(asList(despachoDTO), analiseTecnica);
        assertNotNull(analises);
        assertFalse(analises.isEmpty());
    }

    @Test
    public void salvarListaNull() {
        List<AnaliseTecnicaDespachoSecretario> analises =
                analiseTecnicaDespachoSecretarioService.salvar(null , analiseTecnica);
        assertNotNull(analises);
    }

}

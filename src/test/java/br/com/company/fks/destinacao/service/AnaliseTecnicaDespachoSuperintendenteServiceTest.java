package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoChefiaRepository;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoSuperintendenteRepository;
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
public class AnaliseTecnicaDespachoSuperintendenteServiceTest {

    @InjectMocks
    private AnaliseTecnicaDespachoSuperintendenteService analiseTecnicaDespachoSuperintendenteService;

    @Mock
    private AnaliseTecnicaDespachoSuperintendenteRepository analiseTecnicaDespachoSuperintendenteRepository;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private AnaliseTecnicaDespachoSuperintendente analiseTecnicaDespachoSuperintendente;

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
        when(analiseTecnicaDespachoSuperintendente.getAnaliseTecnicaDespachoID()).thenReturn(analiseTecnicaDespachoID);
        when(analiseTecnicaDespachoSuperintendenteRepository.save(any(AnaliseTecnicaDespachoSuperintendente.class))).thenReturn(analiseTecnicaDespachoSuperintendente);
        when(entityConverter.converterStrict(any(DespachoDTO.class), eq(Despacho.class))).thenReturn(despacho);
        when(despacho.getJustificativa()).thenReturn("teste");
    }

    @Test
    public void salvar() {
        AnaliseTecnicaDespachoSuperintendente analiseSalva =
                analiseTecnicaDespachoSuperintendenteService.salvar(analiseTecnicaDespachoSuperintendente);
        assertNotNull(analiseSalva);
    }

    @Test
    public void salvarLista() {
        List<AnaliseTecnicaDespachoSuperintendente> analises =
                analiseTecnicaDespachoSuperintendenteService.salvar(asList(despachoDTO), analiseTecnica);
        assertNotNull(analises);
        assertFalse(analises.isEmpty());
    }

    @Test
    public void salvarListaNull() {
        List<AnaliseTecnicaDespachoSuperintendente> analise = analiseTecnicaDespachoSuperintendenteService.salvar(null, analiseTecnica);
        assertNotNull(analise);
    }
}

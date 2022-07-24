package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoTecnico;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoTecnicoRepository;
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
public class AnaliseTecnicaDespachoTecnicoServiceTest {

    @InjectMocks
    private AnaliseTecnicaDespachoTecnicoService analiseTecnicaDespachoTecnicoService;

    @Mock
    private AnaliseTecnicaDespachoTecnicoRepository analiseTecnicaDespachoTecnicoRepository;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private AnaliseTecnicaDespachoTecnico analiseTecnicaDespachoTecnico;

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
        when(analiseTecnicaDespachoTecnico.getAnaliseTecnicaDespachoID()).thenReturn(analiseTecnicaDespachoID);
        when(analiseTecnicaDespachoTecnicoRepository.save(any(AnaliseTecnicaDespachoTecnico.class))).thenReturn(analiseTecnicaDespachoTecnico);
        when(entityConverter.converterStrict(any(DespachoDTO.class), eq(Despacho.class))).thenReturn(despacho);
        when(despacho.getJustificativa()).thenReturn("teste");
    }

    @Test
    public void salvar() {
        AnaliseTecnicaDespachoTecnico analiseSalva =
                analiseTecnicaDespachoTecnicoService.salvar(analiseTecnicaDespachoTecnico);
        assertNotNull(analiseSalva);
    }

    @Test
    public void salvarLista() {
        List<AnaliseTecnicaDespachoTecnico> analises =
                analiseTecnicaDespachoTecnicoService.salvar(asList(despachoDTO), analiseTecnica);
        assertNotNull(analises);
        assertFalse(analises.isEmpty());
    }

    @Test
    public void salvarListaNull() {
        List<AnaliseTecnicaDespachoTecnico> analises = analiseTecnicaDespachoTecnicoService.salvar(null, analiseTecnica);
        assertNotNull(analises);
    }

}

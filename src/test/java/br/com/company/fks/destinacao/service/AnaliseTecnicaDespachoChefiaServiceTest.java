package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoChefia;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.repository.AnaliseTecnicaDespachoChefiaRepository;
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
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 30/12/16.
 */
@RunWith(PowerMockRunner.class)
public class AnaliseTecnicaDespachoChefiaServiceTest {

    @InjectMocks
    private AnaliseTecnicaDespachoChefiaService analiseTecnicaDespachoChefiaService;

    @Mock
    private AnaliseTecnicaDespachoChefiaRepository analiseTecnicaDespachoChefiaRepository;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private AnaliseTecnicaDespachoChefia analiseTecnicaDespachoChefia;

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
        when(analiseTecnicaDespachoChefia.getAnaliseTecnicaDespachoID()).thenReturn(analiseTecnicaDespachoID);
        when(analiseTecnicaDespachoChefiaRepository.save(any(AnaliseTecnicaDespachoChefia.class))).thenReturn(analiseTecnicaDespachoChefia);
        when(entityConverter.converterStrict(any(DespachoDTO.class), eq(Despacho.class))).thenReturn(despacho);
        when(despacho.getJustificativa()).thenReturn("teste");
    }

    @Test
    public void salvar() {
        AnaliseTecnicaDespachoChefia analiseTecnicaDespachoChefiaSalva =
                analiseTecnicaDespachoChefiaService.salvar(analiseTecnicaDespachoChefia);
        assertNotNull(analiseTecnicaDespachoChefiaSalva);
    }

    @Test
    public void salvarLista() {
        List<AnaliseTecnicaDespachoChefia> analises =
                analiseTecnicaDespachoChefiaService.salvar(asList(despachoDTO), analiseTecnica);
        assertNotNull(analises);
        assertFalse(analises.isEmpty());
    }

    @Test
    public void salvarListaNull() {
        List<AnaliseTecnicaDespachoChefia> analises =
                analiseTecnicaDespachoChefiaService.salvar(null, analiseTecnica);
        assertNotNull(analises);
    }
}

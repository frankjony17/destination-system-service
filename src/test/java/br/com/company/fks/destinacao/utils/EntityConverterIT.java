package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.entidades.TipoMoeda;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Type;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 17/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class EntityConverterIT extends BaseIntegrationTestCofig {

    @Autowired
    private EntityConverter entityConverter;

    @Mock
    private TipoMoeda tipoMoeda;

    @Mock
    private DominioDTO dominioEsperado;

    private Type type;

    @Before
    public void setUp() {
        type = new TypeToken<DominioDTO>() {}.getType();
        ReflectionTestUtils.invokeMethod(entityConverter, "init", null);
        tipoMoeda = new TipoMoeda();
        dominioEsperado = new DominioDTO();
        tipoMoeda.setId(1);
        tipoMoeda.setDescricao("Real");
        dominioEsperado.setId(1);
        dominioEsperado.setDescricao("Real");
    }

    @Test
    public void converter() {
        DominioDTO dominio = entityConverter.converter(tipoMoeda, DominioDTO.class);
        assertEquals(dominioEsperado.getId(), dominio.getId());
        assertEquals(dominioEsperado.getDescricao(), dominio.getDescricao());
    }

    @Test
    public void converterStrict() {
        DominioDTO dominio = entityConverter.converterStrict(tipoMoeda, DominioDTO.class);
        assertEquals(dominioEsperado.getId(), dominio.getId());
        assertEquals(dominioEsperado.getDescricao(), dominio.getDescricao());
    }

    @Test
    public void converterStrictNull() {
        DominioDTO dominio = entityConverter.converterStrict(null, DominioDTO.class);
        assertNull(dominio);
    }

    @Test
    public void converterStrictType() {
        DominioDTO dominio = entityConverter.converterStrict(tipoMoeda, type);
        assertEquals(dominioEsperado.getId(), dominio.getId());
        assertEquals(dominioEsperado.getDescricao(), dominio.getDescricao());
    }

    @Test
    public void converterLazyLoading() {
        DominioDTO dominio = entityConverter.converterLazyLoading(tipoMoeda, DominioDTO.class);
        assertEquals(dominioEsperado.getId(), dominio.getId());
        assertEquals(dominioEsperado.getDescricao(), dominio.getDescricao());
    }

    @Test
    public void converterStrictLazyLoading() {
        DominioDTO dominio = entityConverter.converterStrictLazyLoading(tipoMoeda, DominioDTO.class);
        assertEquals(dominioEsperado.getId(), dominio.getId());
        assertEquals(dominioEsperado.getDescricao(), dominio.getDescricao());
    }

    @Test
    public void converterListaStrictLazyLoading() {
        type = new TypeToken<List<DominioDTO>>(){}.getType();
        List<DominioDTO> dominios = entityConverter.converterListaStrictLazyLoading(asList(tipoMoeda), type);
        assertNotNull(dominios);
        assertFalse(dominios.isEmpty());
        assertEquals(dominioEsperado.getId(), dominios.get(0).getId());
        assertEquals(dominioEsperado.getDescricao(), dominios.get(0).getDescricao());
    }

}

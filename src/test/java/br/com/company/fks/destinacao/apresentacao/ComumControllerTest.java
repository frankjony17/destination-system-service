package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.ServicosResposta;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 02/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ComumControllerTest {

    private static final String URL_ACESSOS = "cadastroimoves.basis.com.br/";
    private static final String idSistema = "idSistema";
    private static final String URL_HOME = "urlHome";

    @InjectMocks
    private ComumController comumController;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private UriComponentsBuilder uriComponentsBuilder;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private ResponseEntity listResponseEntity;

    @Mock
    private List<Object> lista;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(urlIntegracaoUtils, "urlAcessos", URL_ACESSOS);
        ReflectionTestUtils.setField(comumController, "idSistema", idSistema);
        ReflectionTestUtils.setField(comumController, "urlHome", URL_HOME);
    }

    @Test
    public void getUrlAmbiente(){
        ResponseEntity<Map<String, Object>> mapResponseEntity = comumController.getUrlAmbiente();
        assertNotNull(mapResponseEntity);
    }

    @Test
    public void buscarIdSistema(){
        ResponseEntity<ServicosResposta> servicosRespostaResponseEntity = comumController.buscarIdSistema();
        assertNotNull(servicosRespostaResponseEntity);
        assertTrue(servicosRespostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void getUrlHome(){
        ResponseEntity<ServicosResposta> servicosRespostaResponseEntity = comumController.getUrlHome();
        assertNotNull(servicosRespostaResponseEntity);
        assertTrue(servicosRespostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }
}
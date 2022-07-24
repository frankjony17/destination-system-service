package br.com.company.fks.destinacao.utils;

import br.gov.mpog.acessos.cliente.utils.HeadersUtils;
import br.com.company.fks.destinacao.builder.ServicosResposta;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.HttpsURL;
import org.hibernate.validator.constraints.URL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.opensaml.xacml.ctx.ResponseType;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.transport.http.HttpUrlConnection;

import javax.persistence.ManyToOne;

import java.net.URI;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class RequestUtilsTest {

    @InjectMocks
    private RequestUtils requestUtils;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private HeadersUtils headersUtils;

    @Mock
    private HttpHeaders httpHeaders;

    @Mock
    private URI uri;

    @Mock
    private UriComponentsBuilder uriComponentsBuilder;

    @Mock
    private UriComponents uriComponents;

    @Mock
    private Object object;

    @Mock
    private RequestEntity requestEntity;

    @Mock
    private ResponseType responseType;

    @Mock
    private HttpURL httpURL;

    @Test
    public void doPostBetweenModules(){
        when(headersUtils.getHeadersDefaults()).thenReturn(httpHeaders);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestUtils.doPostBetweenModules("url", object);
    }

    @Test
    public void getRestTemplate(){
        RestTemplate restTemplate = requestUtils.getRestTemplate();
    }

    @Test
    public void getForEntity(){
        when(headersUtils.getHeadersDefaults()).thenReturn(httpHeaders);
        requestUtils.getForEntity(uri, HeadersUtils.class);
    }

    @Test
    public void doDeleteBetweenModules(){
        when(headersUtils.getHeadersDefaults()).thenReturn(httpHeaders);
        requestUtils.doDeleteBetweenModules("url");
    }
}

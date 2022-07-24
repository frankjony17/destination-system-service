package br.com.company.fks.destinacao.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Created by diego on 03/01/17.
 */
@RunWith(PowerMockRunner.class)
public class RequestsServicoUtilsTest {

    @Test
    public void doGet() {
        /*RestTemplate restTemplate = new RestTemplate();
        ReflectionTestUtils.invokeSetterMethod(restTemplate, "getForEntity", "teste");
        ResponseEntity responseEntity = RequestsServicoUtils.doGet("http://www.google.com.br", String.class);
        responseEntity.getBody();*/
    }

    @Test
    public void doPut() {
        /*ResponseEntity responseEntity = RequestsServicoUtils.doGet("http://www.google.com.br", String.class);
        responseEntity.getBody();*/
    }

    @Test
    public void getForEntity() {

    }
}

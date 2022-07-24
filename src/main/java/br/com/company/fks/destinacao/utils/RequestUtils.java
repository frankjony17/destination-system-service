package br.com.company.fks.destinacao.utils;

import br.gov.mpog.acessos.cliente.utils.HeadersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by diego on 21/02/17.
 */
@Component
public class RequestUtils {

    @Autowired
    private HeadersUtils headersUtils;


    private RestTemplate restTemplate = new RestTemplate();

    /**
     * O metodo efetua requisições HTTP do tipo GET.
     * @param url
     * @param clazz
     * @return
     */
    public ResponseEntity<?> doGet(String url, Class<?> clazz) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders headers = headersUtils.getHeadersDefaults();
        return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(headers), clazz);
    }

    public <T> ResponseEntity<?> doGetBetweenDomain(String url, Class<?> clazz, T t) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders headers = headersUtils.getHeadersDefaults();

        final HttpEntity<T> entity = new HttpEntity<T>(t ,
                headers);

        return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, clazz);
    }

    /**
     * Efetua requisições HTTP do tipo POST entre os módulos Basis passando o token no header
     *
     * @param url
     * @return
     */
    public ResponseEntity<?> doPostBetweenModules(String url, Object objeto) {
        HttpHeaders headers = headersUtils.getHeadersDefaults();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(objeto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, objeto.getClass());
    }

    /**
     * Método Put
     * @param url
     * @param clazz
     */
    public void doPut(String url, Class<?> clazz) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders headers = headersUtils.getHeadersDefaults();
        restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.PUT, new HttpEntity<>(headers), clazz);
    }

    /**
     *
     * @param uri
     * @param clazz
     * @return
     */
    public ResponseEntity<?> getForEntity(URI uri, Class<?> clazz) {
        HttpHeaders headers = headersUtils.getHeadersDefaults();
        return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), clazz);
    }

    /**
     * Retorna instancia RestTemplate.
     * @return
     */
    public RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    /**
     * Efetua requisições HTTP do tipo DELETE entre os módulos Basis passando o token no header
     *
     * @param url
     * @return
     */
    public ResponseEntity<?> doDeleteBetweenModules(String url) {
        HttpHeaders headers = headersUtils.getHeadersDefaults();
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);
    }

}

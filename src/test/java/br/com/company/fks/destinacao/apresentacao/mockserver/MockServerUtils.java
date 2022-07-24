package br.com.company.fks.destinacao.apresentacao.mockserver;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 * Created by diego on 02/03/17.
 */
public class MockServerUtils {

    private MockRestServiceServer mockServer;

    public MockServerUtils iniciarRestTemplate(RestTemplate restTemplate) {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        return this;
    }

    public MockServerUtils mockServe(String url, HttpMethod httpMethod, String json, MediaType mediaType) {
        mockServer.expect(requestTo(url))
                .andExpect(method(httpMethod))
                .andRespond(MockRestResponseCreators.withSuccess(json, mediaType));
        return this;
    }

    public MockServerUtils mockServeServerError(String url, HttpMethod httpMethod) {
        mockServer.expect(requestTo(url))
                .andExpect(method(httpMethod))
                .andRespond(MockRestResponseCreators.withServerError());
        return this;
    }

    public MockServerUtils mockServe(String url, HttpMethod httpMethod, byte[] dados, MediaType mediaType) {
        mockServer.expect(requestTo(url))
                .andExpect(method(httpMethod))
                .andRespond(MockRestResponseCreators.withSuccess(dados, mediaType));
        return this;
    }

    public MockServerUtils mockServe(String url, HttpMethod httpMethod, String json, MediaType mediaType, int qtdRequests) {

        for (int i = 0; i < qtdRequests; i++) {
            mockServe(url, httpMethod, json, mediaType);
        }

        return this;
    }

}

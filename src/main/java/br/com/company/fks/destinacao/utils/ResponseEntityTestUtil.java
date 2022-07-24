package br.com.company.fks.destinacao.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ResponseEntityTestUtil {

    public static void entityNaoNulaEStatusOk(ResponseEntity entity) {
        assertNotNull(entity);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}

package br.com.company.fks.destinacao.apresentacao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
public class EnderecoCorrespondenciaControllerTest {

    @InjectMocks
    private EnderecoCorrespondenciaController controller;

    @Test
    public void buscarAdotarEndereco() throws Exception {
        ResponseEntity<Map<String, String>> entity = controller.buscarAdotarEndereco();
        assertNotNull(entity);
        assertFalse(entity.getBody().isEmpty());
    }

}
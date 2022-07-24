package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO;
import br.com.company.fks.destinacao.service.DestinacaoImovelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 23/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DestinacaoImovelControllerTest {

    @InjectMocks
    private DestinacaoImovelController destinacaoImovelController;

    @Mock
    private DestinacaoImovelService destinacaoImovelService;

    @Mock
    private DestinacaoImovelDTO destinacaoImovelDTO;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void buscarDadosUtilizacao() throws Exception {
        when(destinacaoImovelService.buscarCodigoUtilizacao(anyString())).thenReturn(destinacaoImovelDTO);
        Resposta <DestinacaoImovelDTO> resposta = RespostaBuilder.getBuilder().setResultado(destinacaoImovelDTO).build();
        ResponseEntity<Resposta<DestinacaoImovelDTO>> retorno = destinacaoImovelController.buscarDadosUtilizacao(anyString());
        assertNotNull(resposta);
        assertNotNull(retorno);
    }

}
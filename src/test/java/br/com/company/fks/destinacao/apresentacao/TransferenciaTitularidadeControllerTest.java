package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.entidades.BaseLegal;
import br.com.company.fks.destinacao.dominio.entidades.TipoDestinatario;
import br.com.company.fks.destinacao.dominio.entidades.TipoTransferencia;
import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import br.com.company.fks.destinacao.service.DominioService;
import br.com.company.fks.destinacao.service.TransferenciaTitularidadeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by guilherme on 02/03/17.
 */

@RunWith(PowerMockRunner.class)
public class TransferenciaTitularidadeControllerTest {

    @InjectMocks
    private TransferenciaTitularidadeController transferenciaTitularidadeController;

    @Mock
    private TransferenciaTitularidadeService transferenciaTitularidadeService;

    @Mock
    private TransferenciaTitularidade transferenciaTitularidade;

    @Mock
    private DominioService dominioService;

    @Mock
    private DominioDTO dominioDTO;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAllTipoTransferencia() throws Exception {
        when(dominioService.buscarTodos(eq(TipoTransferencia.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<List<TipoTransferencia>>> resposta =
                transferenciaTitularidadeController.findAllTipoTransferencia();
        assertNotNull(resposta);
    }


    @Test
    public void findAllTipoDestinatario() throws Exception {
        when(dominioService.buscarTodos(eq(TipoDestinatario.class))).thenReturn(asList(dominioDTO));
        ResponseEntity<Resposta<List<TipoDestinatario>>> resposta =
                transferenciaTitularidadeController.findAllTipoDestinatario();
        assertNotNull(resposta);
    }

    @Test
    public void buscarNaturezaJuridicaPermitida() throws Exception{
        assertNotNull(transferenciaTitularidadeController.buscarNaturezaJuridicaPermitida());
    }


}
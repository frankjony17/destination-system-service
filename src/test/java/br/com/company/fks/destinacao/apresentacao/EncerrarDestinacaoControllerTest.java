package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DominioDTO;
import br.com.company.fks.destinacao.dominio.dto.EncerramentoDestinacaoDTO;
import br.com.company.fks.destinacao.dominio.dto.OrdenacaoEnumDTO;
import br.com.company.fks.destinacao.dominio.entidades.TipoDocumento;
import br.com.company.fks.destinacao.dominio.enums.MotivoEncerrarDestinacaoEnum;
import br.com.company.fks.destinacao.service.EncerramentoDestinacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class EncerrarDestinacaoControllerTest {

    @InjectMocks
    private EncerrarDestinacaoController encerrarDestinacaoController;

    @Mock
    private EncerramentoDestinacaoService encerramentoDestinacaoService;

    @Mock
    private List<OrdenacaoEnumDTO> ordenacaoEnumDTOList;

    @Mock
    private EncerramentoDestinacaoDTO encerramentoDestinacaoDTO;

    @Mock
    private ResponseEntity responseEntity;

    @Test
    public void buscarMotivosEncerrarDestinacao(){
        responseEntity = encerrarDestinacaoController.buscarMotivosEncerrarDestinacao();
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void buscarDespachosCancelarEncerrar(){
        responseEntity = encerrarDestinacaoController.buscarDespachosCancelarEncerrar();
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void submeterSuperIntendente(){
        responseEntity = encerrarDestinacaoController.submeterSuperIntendente(1L, encerramentoDestinacaoDTO);
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void confirmarCancelamento(){
        responseEntity = encerrarDestinacaoController.confirmarCancelamento(1L, encerramentoDestinacaoDTO);
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void buscarPorIdDestinacao(){
        responseEntity = encerrarDestinacaoController.buscarPorIdDestinacao(1L);
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }
}

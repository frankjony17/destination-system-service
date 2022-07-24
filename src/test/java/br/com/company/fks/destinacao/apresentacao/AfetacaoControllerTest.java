package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.AfetacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Afetacao;
import br.com.company.fks.destinacao.service.AfetacaoService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class AfetacaoControllerTest {

    @InjectMocks
    private AfetacaoController afetacaoController;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private AfetacaoService afetacaoService;

    @Mock
    private AfetacaoDTO afetacaoDTO;

    @Mock
    private Afetacao afetacao;

    @Test
    public void salvarDoacao (){
        when(entityConverter.converterStrict(any(AfetacaoDTO.class), eq(Afetacao.class))).thenReturn(afetacao);
        when(afetacaoService.salvar(any(Afetacao.class))).thenReturn(afetacao);
        afetacaoController.salvarDoacao(afetacaoDTO);
    }
}

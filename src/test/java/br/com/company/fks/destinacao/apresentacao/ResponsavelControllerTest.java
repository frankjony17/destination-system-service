package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoResponsavelDTO;
import br.com.company.fks.destinacao.dominio.dto.OrdenacaoEnumDTO;
import br.com.company.fks.destinacao.dominio.dto.ResponsavelDTO;
import br.com.company.fks.destinacao.dominio.enums.DescricaoParentescoEnum;
import br.com.company.fks.destinacao.dominio.enums.EstadoCivilEnum;
import br.com.company.fks.destinacao.dominio.enums.OpcoesPadraoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoRepresentacaoEnum;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.service.ResponsavelService;
import br.com.company.fks.destinacao.utils.EnumUtils;
import br.com.company.fks.destinacao.utils.ResponseEntityTestUtil;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 02/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ResponsavelControllerTest {

    @InjectMocks
    private ResponsavelController responsavelController;

    @Mock
    private ResponsavelDTO responsavelDTO;

    @Mock
    private DestinacaoResponsavelDTO destinacaoResponsavelDTO;

    @Mock
    private ResponsavelService responsavelService;


    @Test
    @SneakyThrows
    public void buscarResponsavelImovel(){
        when(responsavelService.buscarResponsavelImovel(anyLong())).thenReturn(responsavelDTO);

        ResponseEntity<Resposta<ResponsavelDTO>> respostaResponseEntity = responsavelController.buscarResponsavelImovel(1L);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    @SneakyThrows
    public void buscarResponsavelImovelDisparandoException() {
        when(responsavelService.buscarResponsavelImovel(anyLong())).thenThrow(IntegracaoException.class);

        ResponseEntity<Resposta<ResponsavelDTO>> respostaResponseEntity = responsavelController.buscarResponsavelImovel(1L);
        assertNotNull(respostaResponseEntity);
        assertTrue(respostaResponseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void buscarPorResponsavel(){
        when(responsavelService.buscarResponsavelDestinacao(anyString())).thenReturn(singletonList(destinacaoResponsavelDTO));
        ResponseEntity<List<DestinacaoResponsavelDTO>> entity = responsavelController.buscarPorResponsavel("0123456789");
        ResponseEntityTestUtil.entityNaoNulaEStatusOk(entity);
    }

    @Test
    public void buscarTipoPosseOcupacao(){
        ResponseEntity<List<OrdenacaoEnumDTO>> entity = responsavelController.buscarTipoPosseOcupacao();
        ResponseEntityTestUtil.entityNaoNulaEStatusOk(entity);
        assertFalse(entity.getBody().isEmpty());
    }

    @Test
    public void buscarTipoRepresentacao(){
        ResponseEntity<List<OrdenacaoEnumDTO>> entity = responsavelController.buscarTipoRepresentacao();
        ResponseEntityTestUtil.entityNaoNulaEStatusOk(entity);
        assertFalse(entity.getBody().isEmpty());
    }

    @Test
    public void buscarEstadoCivil(){
        ResponseEntity<List<OrdenacaoEnumDTO>> entity = responsavelController.buscarEstadoCivil();
        ResponseEntityTestUtil.entityNaoNulaEStatusOk(entity);
        assertFalse(entity.getBody().isEmpty());
    }

    @Test
    public void buscarOpcoesPadrao(){
        ResponseEntity<List<OrdenacaoEnumDTO>> entity = responsavelController.buscarOpcoesPadrao();
        ResponseEntityTestUtil.entityNaoNulaEStatusOk(entity);
        assertFalse(entity.getBody().isEmpty());
    }

    @Test
    public void buscarDescricoesParentesco(){
        ResponseEntity<List<OrdenacaoEnumDTO>> entity = responsavelController.buscarDescricoesParentesco();
        ResponseEntityTestUtil.entityNaoNulaEStatusOk(entity);
        assertFalse(entity.getBody().isEmpty());
    }

}
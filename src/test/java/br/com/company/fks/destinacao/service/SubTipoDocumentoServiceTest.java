package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.SubTipoDocumento;
import br.com.company.fks.destinacao.repository.SubTipoDocumentoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 19/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class SubTipoDocumentoServiceTest {

    @InjectMocks
    private SubTipoDocumentoService subTipoDocumentoService;

    @Mock
    private SubTipoDocumentoRepository subTipoDocumentoRepository;

    @Mock
    private List<SubTipoDocumento> subTipoDocumentoList;

    @Test
    public void buscarSubTipoDocumento(){
        when(subTipoDocumentoRepository.buscarTipoDocumento(anyInt())).thenReturn(subTipoDocumentoList);
        List<SubTipoDocumento> retorno = subTipoDocumentoService.buscarSubTipoDocumento(1);
        assertNotNull(retorno);
    }

}
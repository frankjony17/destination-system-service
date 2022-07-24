package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.CessaoOnerosaDTO;
import br.com.company.fks.destinacao.dominio.entidades.CessaoOnerosa;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.repository.CessaoOnerosaRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;

@RunWith(PowerMockRunner.class)
public class CessaoOnerosaServiceTest {

    @InjectMocks
    private CessaoOnerosaService cessaoOnerosaService;

    @Mock
    private CessaoOnerosa cessaoOnerosa;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private CessaoOnerosaRepository cessaoOnerosaRepository;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private CessaoOnerosaDTO cessaoOnerosaDTO;

    @Before
    public void setup() {
//      given(dadosResponsavelService.salvar(any(DadosResponsavel.class), Boolean.FALSE)).willReturn(dadosResponsavel);
        given(cessaoOnerosaRepository.save(any(CessaoOnerosa.class))).willReturn(cessaoOnerosa);
        given(entityConverter.converterStrict(any(CessaoOnerosa.class), eq(CessaoOnerosaDTO.class))).willReturn(cessaoOnerosaDTO);
        given(cessaoOnerosaRepository.findOne(anyLong())).willReturn(cessaoOnerosa);
    }

    @Test
    public void salvarDadosEspecificos() {
        CessaoOnerosa cessaoOnerosaTest = cessaoOnerosaService.salvarDadosEspecificos(cessaoOnerosa);
        assertNotNull(cessaoOnerosaTest);
    }

    @Test
    public void findOne() throws Exception {
        Resposta<CessaoOnerosa> test = cessaoOnerosaService.findOne(1l);
        assertNotNull(test);
    }
}


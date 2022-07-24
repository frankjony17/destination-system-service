package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.PermissaoUsoImovelFuncionalDTO;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.PermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.repository.PermissaoUsoImovelFuncionalRepository;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;

@PrepareForTest
@RunWith(PowerMockRunner.class)
public class PermissaoUsoImovelFuncionalServiceTest {

    @InjectMocks
    private PermissaoUsoImovelFuncionalService permissaoUsoImovelFuncionalService;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private PermissaoUsoImovelFuncional permissaoUsoImovelFuncional;

    @Mock
    private PermissaoUsoImovelFuncionalDTO permissaoUsoImovelFuncionalDTO;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private PermissaoUsoImovelFuncionalRepository permissaoUsoImovelFuncionalRepository;

    @Before
    public void setup() {
    //    given(dadosResponsavelService.salvar(any(DadosResponsavel.class), Boolean.FALSE)).willReturn(dadosResponsavel);
        given(permissaoUsoImovelFuncionalRepository.save(any(PermissaoUsoImovelFuncional.class))).willReturn(permissaoUsoImovelFuncional);
        given(entityConverter.converterStrict(any(PermissaoUsoImovelFuncional.class), eq(PermissaoUsoImovelFuncionalDTO.class))).willReturn(permissaoUsoImovelFuncionalDTO);
        given(permissaoUsoImovelFuncionalRepository.findOne(anyLong())).willReturn(permissaoUsoImovelFuncional);

    }

    @Test
    public void salvarDadosEspecificos() {
        PermissaoUsoImovelFuncional test = permissaoUsoImovelFuncionalService.salvarDadosEspecificos(permissaoUsoImovelFuncional);
        assertNotNull(test);
    }

    @Test
    public void findOne() throws Exception {
        Resposta<PermissaoUsoImovelFuncionalDTO> test = permissaoUsoImovelFuncionalService.findOne(1l);
        assertNotNull(test);
    }
}

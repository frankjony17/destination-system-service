package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.CuemDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.Cuem;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Financeiro;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.CuemRepository;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.EnderecoRepository;
import br.com.company.fks.destinacao.service.*;
import br.com.company.fks.destinacao.service.validadores.ValidadorStrategy;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorCdru;
import br.com.company.fks.destinacao.utils.EntityConverter;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 23/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuemServiceTest {

    @InjectMocks
    private CuemService cuemService;

    @Mock
    private CuemRepository cuemRepository;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private Cuem cuem;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private CuemDTO cuemDTO;

    @Before
    public void setup() {
//        given(dadosResponsavelService.salvar(any(DadosResponsavel.class), Boolean.FALSE)).willReturn(dadosResponsavel);
        given(cuemRepository.save(any(Cuem.class))).willReturn(cuem);
        given(entityConverter.converterStrict(any(Cuem.class), eq(CuemDTO.class))).willReturn(cuemDTO);
        given(cuemRepository.findOne(anyLong())).willReturn(cuem);
    }

    @Test
    public void salvarDadosEspecificos() {
        Cuem test = cuemService.salvarDadosEspecificos(cuem);
        assertNotNull(test);
    }

    @Test
    public void findOne() throws Exception{
        Resposta<CuemDTO> test = cuemService.findOne(1l);
        assertNotNull(test);
    }

}

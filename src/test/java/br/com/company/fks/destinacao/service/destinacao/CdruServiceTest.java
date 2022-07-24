package br.com.company.fks.destinacao.service.destinacao;


import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.CdruDTO;
import br.com.company.fks.destinacao.dominio.dto.DestinacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Cdru;
import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Financeiro;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.dominio.enums.StatusDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.CdruRepository;
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

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;


/**
 * Created by gustavodias on 16/11/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class CdruServiceTest {

    @InjectMocks
    private CdruService cdruService;

    @Mock
    private CdruRepository cdruRepository;

    @Mock
    private Cdru cdru;

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private CdruDTO cdruDTO;

    @Before
    public void setUp() throws NegocioException {
        mockDependenciasDestinacao();
    }

    private void mockDependenciasDestinacao() throws NegocioException {
        when(cdruRepository.save(any(Cdru.class))).thenReturn(cdru);
        given(entityConverter.converterStrict(any(Cdru.class), eq(CdruDTO.class))).willReturn(cdruDTO);
        given(cdruRepository.findOne(anyLong())).willReturn(cdru);
    }

    @Test
    public void salvarDadosEspecificos() {
        Cdru teste = cdruService.salvarDadosEspecificos(cdru);
        assertNotNull(teste);
    }

    @Test
    public void findOne() throws Exception {
        Resposta<CdruDTO> test = cdruService.findOne(1l);
        assertNotNull(test);
    }

}
package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.dominio.dto.DestinacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Financeiro;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import br.com.company.fks.destinacao.dominio.entidades.Venda;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import br.com.company.fks.destinacao.repository.EnderecoRepository;
import br.com.company.fks.destinacao.repository.VendaRepository;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 23/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class VendaServiceTest {

    @InjectMocks
    private VendaService vendaService;

    @Mock
    private VendaRepository vendaRepository;

    @Mock
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private Venda venda;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private CancelarImovelService cancelarImovelService;

    @Mock
    private Destinacao destinacao;

    @Before
    public void setup() {
        given(vendaRepository.save(any(Venda.class))).willReturn(venda);
    }

    @Test
    public void salvarDadosEspecificos() {
        cancelarImovelService.cancelarImovel(destinacao);
        Venda vendaSalva = vendaService.salvarDadosEspecificos(venda);
        assertNotNull(vendaSalva);
    }


}

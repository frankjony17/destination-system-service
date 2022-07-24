package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.EnderecoCorrespondencia;
import br.com.company.fks.destinacao.dominio.entidades.Interveniente;
import br.com.company.fks.destinacao.dominio.enums.TipoPosseOcupacaoEnum;
import br.com.company.fks.destinacao.repository.DadosResponsavelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;

@RunWith(PowerMockRunner.class)
public class DadosResponsavelServiceTest {

    @InjectMocks
    private DadosResponsavelService dadosResponsavelService;

    @Mock
    private EnderecoCorrespondenciaService enderecoService;

    @Mock
    private IntervenienteService intervenienteService;

    @Mock
    private DadosResponsavelRepository dadosResponsavelRepository;

    @Mock
    private ResponsavelService responsavelService;

    @Mock
    private Interveniente interveniente;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private EnderecoCorrespondencia enderecoCorrespondencia;

    @Before
    public void setup() {
        given(enderecoService.salvar(any(EnderecoCorrespondencia.class))).willReturn(enderecoCorrespondencia);
        given(dadosResponsavelRepository.save(any(DadosResponsavel.class))).willReturn(dadosResponsavel);
        given(responsavelService.salvar(anyList(), any(DadosResponsavel.class))).willReturn(Arrays.asList());
        given(dadosResponsavel.getTipoPosseOcupacao()).willReturn(TipoPosseOcupacaoEnum.COLETIVO);

    }

    @Test
    public void salvar() {
        DadosResponsavel test = dadosResponsavelService.salvar(dadosResponsavel, Boolean.FALSE);
        assertNotNull(test);
    }

    @Test
    public void salvarNulo(){
        DadosResponsavel nulo = dadosResponsavelService.salvar(null, null);
        assertNull(null);
    }

    @Test
    public void salvarIf(){
        given(dadosResponsavel.getTipoPosseOcupacao()).willReturn(TipoPosseOcupacaoEnum.INDIVIDUAL);
        DadosResponsavel test = dadosResponsavelService.salvar(dadosResponsavel, Boolean.FALSE);
        assertNotNull(test);
    }

}

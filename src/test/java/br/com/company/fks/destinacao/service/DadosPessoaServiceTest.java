package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.RespostaDadosPessoaFisicaHistoricoCampo;
import br.com.company.fks.destinacao.builder.RespostaDadosPessoaJuridica;
import br.com.company.fks.destinacao.dominio.dto.*;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class DadosPessoaServiceTest {

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private RequestUtils requestUtils;

    @InjectMocks
    private DadosPessoaService service;

    private DadosPessoaConsultaDTO dadosPessoaConsultaDTO = new DadosPessoaConsultaDTO();

    @Mock
    private ResponseEntity entity;

    @Mock
    private RespostaDadosPessoaFisicaHistoricoCampo respostaDadosPessoaFisicaHistoricoCampo;

    @Mock
    private DadosPessoaHistoricoCampoDTO dadosPessoaHistoricoCampoDTO;
    @Mock
    private FundamentoLegalDTO fundamentoLegalDTO;

    @Mock
    private RespostaDadosPessoaJuridica respostaDadosPessoaJuridica;

    @Mock
    private DadosPessoaJuridicaDTO dadosPessoaJuridicaDTO;

    @Test
    public void buscarPessoaFisicaHistoricaCampo() throws Exception {
        when(entity.getBody()).thenReturn(respostaDadosPessoaFisicaHistoricoCampo);
        when(respostaDadosPessoaFisicaHistoricoCampo.getResultado()).thenReturn(Collections.singletonList(dadosPessoaHistoricoCampoDTO));
        when(requestUtils.doGet(anyString(), eq(RespostaDadosPessoaFisicaHistoricoCampo.class))).thenReturn(entity);
        List<DadosPessoaHistoricoCampoDTO> campo = service.buscarPessoaFisicaHistoricaCampo("0123456789", "campo");
        assertNotNull(campo);
    }

    @Test
    public void buscarPessoaJuridicaHistoricaCampo() throws Exception{
        when(entity.getBody()).thenReturn(respostaDadosPessoaFisicaHistoricoCampo);
        when(respostaDadosPessoaFisicaHistoricoCampo.getResultado()).thenReturn(Collections.singletonList(dadosPessoaHistoricoCampoDTO));
        when(requestUtils.doGet(anyString(), eq(RespostaDadosPessoaFisicaHistoricoCampo.class))).thenReturn(entity);
        List<DadosPessoaHistoricoCampoDTO> campo = service.buscarPessoaJuridicaHistoricaCampo("cnpj", "campo");
        assertNotNull(campo);
    }

    @Test
    public void buscarPessoaFisica() throws NegocioException {
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        List<NaturezaJuridicaPermitidaDTO> lista = new ArrayList<>();
        NaturezaJuridicaPermitidaDTO naturezaJuridicaPermitidaDTO = new NaturezaJuridicaPermitidaDTO();
        naturezaJuridicaPermitidaDTO.setNome("PESSOA_FISICA");
        lista.add(naturezaJuridicaPermitidaDTO);
        fundamentoLegalDTO.setNaturezaJuridicaPermitidas(lista);
        dadosPessoaConsultaDTO.setTipoDestinacao("1");
        service.buscarPessoaFisica(dadosPessoaConsultaDTO, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    public void buscarPessoaFisicaException() throws NegocioException {
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        List<NaturezaJuridicaPermitidaDTO> lista = new ArrayList<>();
        NaturezaJuridicaPermitidaDTO naturezaJuridicaPermitidaDTO = new NaturezaJuridicaPermitidaDTO();
        naturezaJuridicaPermitidaDTO.setNome("nao passara");
        lista.add(naturezaJuridicaPermitidaDTO);
        fundamentoLegalDTO.setNaturezaJuridicaPermitidas(lista);

        dadosPessoaConsultaDTO.setTipoDestinacao("1");
        service.buscarPessoaFisica(dadosPessoaConsultaDTO, fundamentoLegalDTO);
    }

    @Test
    public void buscarPessoaJuridica() throws NegocioException {
        dadosPessoaConsultaDTO = new DadosPessoaConsultaDTO();
        dadosPessoaConsultaDTO.setTipoDestinacao("POSSE_INFORMAL");
        assertNotNull(service.buscarPessoaJuridica(dadosPessoaConsultaDTO, fundamentoLegalDTO));
    }

    @Test(expected = NegocioException.class)
    public void validarPessoaJuridica() throws NegocioException {
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        DadosPessoaConsultaDTO dadosPessoaConsultaDTO = new DadosPessoaConsultaDTO();
        dadosPessoaConsultaDTO.setTipoDestinacao("dados");
        List<NaturezaJuridicaPermitidaDTO> lista = new ArrayList<>();
        NaturezaJuridicaPermitidaDTO naturezaJuridicaPermitidaDTO = new NaturezaJuridicaPermitidaDTO();
        naturezaJuridicaPermitidaDTO.setDescricao("PESSOA_FISICA");
        lista.add(naturezaJuridicaPermitidaDTO);
        fundamentoLegalDTO.setNaturezaJuridicaPermitidas(lista);
        dadosPessoaJuridicaDTO.setNaturezaJuridica("PESSOA_FISICA");
        assertNotNull(service.buscarPessoaJuridica(dadosPessoaConsultaDTO, fundamentoLegalDTO));
    }

    @Test(expected = NegocioException.class)
    public void validarPessoaJuridica2() throws NegocioException {
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        DadosPessoaConsultaDTO dadosPessoaConsultaDTO = new DadosPessoaConsultaDTO();
        dadosPessoaConsultaDTO.setTipoDestinacao("dados");
        List<NaturezaJuridicaPermitidaDTO> lista = new ArrayList<>();
        NaturezaJuridicaPermitidaDTO naturezaJuridicaPermitidaDTO = new NaturezaJuridicaPermitidaDTO();
        naturezaJuridicaPermitidaDTO.setDescricao("PESSOA_FISICA");
        lista.add(naturezaJuridicaPermitidaDTO);
        fundamentoLegalDTO.setNaturezaJuridicaPermitidas(lista);
        dadosPessoaJuridicaDTO.setNaturezaJuridica("PESSOA_FISICA");
        service.validarPessoaJuridica(fundamentoLegalDTO, dadosPessoaJuridicaDTO);
    }

    @Test
    public void validarPessoaJuridica3() throws NegocioException {
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        DadosPessoaJuridicaDTO dados = new DadosPessoaJuridicaDTO();
        List<NaturezaJuridicaPermitidaDTO> lista = new ArrayList<>();
        NaturezaJuridicaPermitidaDTO naturezaJuridicaPermitidaDTO = new NaturezaJuridicaPermitidaDTO();
        naturezaJuridicaPermitidaDTO.setDescricao("abc");
        lista.add(naturezaJuridicaPermitidaDTO);
        fundamentoLegalDTO.setNaturezaJuridicaPermitidas(lista);
        dados.setNaturezaJuridica("abc");
        service.validarPessoaJuridica(fundamentoLegalDTO, dados);
    }
}
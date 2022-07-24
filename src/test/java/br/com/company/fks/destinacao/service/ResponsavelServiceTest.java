package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.DestinacaoResponsavelDTO;
import br.com.company.fks.destinacao.dominio.dto.ResponsavelDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.FamiliaBeneficiadaRepository;
import br.com.company.fks.destinacao.repository.ResponsavelRepository;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.br.CPF;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

/**
 * Created by Basis Tecnologia on 21/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResponsavelServiceTest {

    @InjectMocks
    private ResponsavelService responsavelService;

    @Mock
    private FamiliaBeneficiadaService familiaBeneficiadaService;

    @Mock
    private FamiliaBeneficiadaRepository familiaBeneficiadaRepository;

    @Mock
    private ResponsavelRepository responsavelRepository;

    @Mock
    private Responsavel responsavel;

    @Mock
    private FamiliaBeneficiada familiaBeneficiada;

    @Mock
    private Destinacao destinacao;

    @Mock
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Mock
    private RequestUtils requestUtils;

    @Mock
    private ResponseEntity responseEntity;

    @Mock
    private ResponsavelDTO responsavelDTO;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private TelefoneService telefoneService;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private EnderecoCorrespondenciaService enderecoCorrespondenciaService;

    @Mock
    private ResidenteService residenteService;

    @Mock
    private EnderecoCorrespondencia enderecoCorrespondencia;


    @Before
    public void setUp(){
        when(responsavelRepository.save(any(Responsavel.class))).thenReturn(responsavel);
        when(familiaBeneficiadaRepository.save(any(FamiliaBeneficiada.class))).thenReturn(familiaBeneficiada);
        when(urlIntegracaoUtils.getUrlBuscarResponsavelImovel(anyLong())).thenReturn("www.google.com");
        when(telefoneService.salvar(anyList(), any(Responsavel.class))).thenReturn(Arrays.asList());
        when(enderecoCorrespondenciaService.salvar(any(EnderecoCorrespondencia.class))).thenReturn(enderecoCorrespondencia);
        when(residenteService.salvar(anyList(),any(Responsavel.class))).thenReturn(Arrays.asList());
    }

    @Test
    public void salvar() throws Exception {
        Responsavel responsavelTeste = responsavelService.salvar(responsavel);
        assertNotNull(responsavelTeste);
    }

    @Test
    public void salvarListaResponsavelDestinacao() throws Exception {
        List<Responsavel> listaResponsavel = new ArrayList<>();
        List<Responsavel> listaResponsavelTeste = responsavelService.salvar(listaResponsavel, dadosResponsavel);
        assertTrue(listaResponsavelTeste.isEmpty());
    }

    @Test
    public void salvarListaResponsavelDestinacao2() throws Exception{
        responsavelService.salvar(null, null);
        assertFalse(false);
    }

    @Test
    public void salvarListaResponsavelDestinacaoFamiliaNotNull() {
        List<Responsavel> listaResponsavel = new ArrayList<>();
        Responsavel responsavelTeste = new Responsavel();
        Destinacao destinacao = new Destinacao();
        FamiliaBeneficiada familiaBeneficiadaTeste = new FamiliaBeneficiada();
        List<FamiliaBeneficiada> listaFamiliaBeneficiadas = new ArrayList<>();

        responsavelTeste.setCpfCnpj("03247831169");
        responsavelTeste.setId(Long.valueOf(01));
        responsavelTeste.setNome("Teste");
        responsavelTeste.setCpfCnpj("03247831169");


        familiaBeneficiadaTeste.setId(Long.valueOf(01));
        familiaBeneficiadaTeste.setSequencial(1);
        familiaBeneficiadaTeste.setNomeConjuge("Teste");
        familiaBeneficiadaTeste.setNomeResponsavel("Teste");
        familiaBeneficiadaTeste.setCpfConjuge("03247831169");
        familiaBeneficiadaTeste.setCpfResponsavel("03247831169");
        familiaBeneficiadaTeste.setAreaUtilizada(Double.valueOf(01));
        familiaBeneficiadaTeste.setResponsavel(responsavelTeste);

        listaFamiliaBeneficiadas.add(familiaBeneficiadaTeste);

        responsavelTeste.setFamiliasBeneficiadas(listaFamiliaBeneficiadas);

        listaResponsavel.add(responsavelTeste);

        List<Responsavel> listaResponsavelTeste = responsavelService.salvar(listaResponsavel, dadosResponsavel);

        assertTrue(!listaResponsavelTeste.isEmpty());

    }

    @Test
    public void salvarListaResponsavelFamiliasBeneficiadasNull() {
        when(responsavel.getFamiliasBeneficiadas()).thenReturn(null);
        List<Responsavel> listaResponsavelTeste = responsavelService.salvar(asList(responsavel), dadosResponsavel);
        assertNotNull(listaResponsavelTeste);
        assertTrue(!listaResponsavelTeste.isEmpty());
    }

    @Test
    public void salvarListaResponsavelFamiliasBeneficiadasVazio() {
        when(responsavel.getFamiliasBeneficiadas()).thenReturn(new ArrayList<>());
        List<Responsavel> listaResponsavelTeste = responsavelService.salvar(asList(responsavel), dadosResponsavel);
        assertNotNull(listaResponsavelTeste);
        assertTrue(!listaResponsavelTeste.isEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarResponsavelImovel(){
        when(requestUtils.doGet(anyString(), eq(ResponsavelDTO.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(responsavelDTO);
        ResponsavelDTO responsavelDTO = responsavelService.buscarResponsavelImovel(1L);
        assertNotNull(responsavelDTO);
    }

    @Test(expected = IntegracaoException.class)
    @SneakyThrows
    public void buscarResponsavelImovelComErro() throws RuntimeException {
        when(requestUtils.doGet(anyString(),eq(ResponsavelDTO.class))).thenThrow(RuntimeException.class);
        ResponsavelDTO responsavelDTO = responsavelService.buscarResponsavelImovel(1L);
    }

    @Test
    public void buscarResponsavelDestinacao()  {
       when(responsavelRepository.buscarDestinacaoesResponsavel(anyString())).thenReturn(Arrays.asList());
       List<DestinacaoResponsavelDTO> buscarResponsavelDestinacao = responsavelService.buscarResponsavelDestinacao("11111111111");
       assertNotNull(buscarResponsavelDestinacao);
    }
}
package br.com.company.fks.destinacao.service.validadores.impl;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import br.com.company.fks.destinacao.dominio.entidades.Encargo;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.UsuarioService;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by diego on 21/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidadorDoacaoTest {

    private static final String ERRO_AO_SALVAR = "Erro ao salvar";
    private static final String CPF = "00000000191";

    @InjectMocks
    private ValidadorDoacao validadorDoacao;

    @Mock
    private Doacao doacao;

    @Mock
    private MensagemNegocio mensagemNegocio;

    @Mock
    private Contrato contrato;

    @Mock
    private Encargo encargo;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Mock
    private DestinacaoImovel destinacaoImovel;

    @Mock
    private Imovel imovel;

    @Mock
    private Endereco endereco;

    @Mock
    private DadosResponsavel responsavel;

    @Mock
    private StatusDestinacao statusDestinacao;

    @Before
    public void setUp() {
        Calendar calendarDataContratro = Calendar.getInstance();
        calendarDataContratro.set(2016, Calendar.NOVEMBER, 16);
        Calendar calendarDataEncargo = Calendar.getInstance();
        calendarDataEncargo.set(2016, Calendar.NOVEMBER, 15);

        when(statusDestinacao.getId()).thenReturn(1);
        when(statusDestinacao.getDescricao()).thenReturn("Ativo");
        when(contrato.getDataInicio()).thenReturn(calendarDataContratro.getTime());
        when(encargo.getDataCumprimento()).thenReturn(calendarDataEncargo.getTime());
        when(doacao.getContrato()).thenReturn(contrato);
        when(doacao.getExisteEncargo()).thenReturn(Boolean.TRUE);
        when(doacao.getEncargos()).thenReturn(asList(encargo));
        when(doacao.getDestinacaoImoveis()).thenReturn(asList(destinacaoImovel));
        when(doacao.getDadosResponsavel()).thenReturn(responsavel);
        when(doacao.getCodFundamentoLegal()).thenReturn(1L);
        when(doacao.getStatusDestinacao()).thenReturn(statusDestinacao);

        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        when(usuarioLogadoDTO.getJurisdicoes()).thenReturn(new HashSet<>(asList("MG", "DF", "SP")));

        when(destinacaoImovel.getImovel()).thenReturn(imovel);
        when(imovel.getEndereco()).thenReturn(endereco);
        when(endereco.getUf()).thenReturn("DF");
    }


    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarComSucesso() {
        validadorDoacao.validar(doacao);
    }


    @Test(expected = NegocioException.class)
    public void validarJurisdicaoUsuarioDiferenteEnderecoImovel() throws NegocioException {
        when(endereco.getUf()).thenReturn("RJ");
        validadorDoacao.validar(doacao);
    }


    @Test(expected = NegocioException.class)
    public void validarImoveisEStadosDiferentes() throws NegocioException {
        DestinacaoImovel destinacaoImovelMinas = mock(DestinacaoImovel.class);
        Imovel imovelMinas = mock(Imovel.class);
        Endereco enderecoMinas = mock(Endereco.class);
        when(destinacaoImovelMinas.getImovel()).thenReturn(imovelMinas);
        when(enderecoMinas.getUf()).thenReturn("MG");
        when(imovelMinas.getEndereco()).thenReturn(enderecoMinas);
        when(doacao.getDestinacaoImoveis()).thenReturn(asList(destinacaoImovelMinas, destinacaoImovel));
        validadorDoacao.validar(doacao);
    }


    @Test(expected = NegocioException.class)
    public void validarDestinacaoImoveisVazio() throws NegocioException {
        when(doacao.getDestinacaoImoveis()).thenReturn(new ArrayList<>());
        validadorDoacao.validar(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validarCpfInvalido() throws NegocioException {
        when(responsavel.getCnpj()).thenReturn("00000000001");
        validadorDoacao.validar(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validarCnpjInvalido() throws NegocioException {
        when(responsavel.getCnpj()).thenReturn("00000000000001");
        validadorDoacao.validar(doacao);
    }
    @Test(expected = NegocioException.class)
    public void validarSemEncatgo() throws NegocioException {
        when(doacao.getExisteEncargo()).thenReturn(Boolean.FALSE);
        validadorDoacao.validar(doacao);
    }
    @Test
    public void validarCnpjValido() throws NegocioException {
        Calendar calendarDataContratro = Calendar.getInstance();
        calendarDataContratro.set(2016, Calendar.NOVEMBER, 16);
        Calendar calendarDataEncargo = Calendar.getInstance();
        calendarDataEncargo.set(2016, Calendar.NOVEMBER, 17);
        when(contrato.getDataInicio()).thenReturn(calendarDataContratro.getTime());
        when(encargo.getDataCumprimento()).thenReturn(calendarDataEncargo.getTime());
        when(responsavel.getCnpj()).thenReturn("00000000000191");
        validadorDoacao.validar(doacao);
    }

    @Test(expected = NegocioException.class)
    @Ignore
    public void validarResponsavelNulo() throws NegocioException {
        when(doacao.getDadosResponsavel()).thenReturn(null);
        validadorDoacao.validar(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validarImovelInformadoNulo() throws NegocioException {
        when(doacao.getDestinacaoImoveis()).thenReturn(null);
        validadorDoacao.validar(doacao);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validadorEspecificoSucesso() {
        when(statusDestinacao.getId()).thenReturn(4);
        validadorDoacao.validadorEspecifico(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoErroContratoNulo() throws NegocioException {
        when(doacao.getContrato()).thenReturn(null);
        when(statusDestinacao.getId()).thenReturn(1);
        validadorDoacao.validadorEspecifico(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoErroContratoDataNulo() throws NegocioException {
        when(doacao.getContrato()).thenReturn(contrato);
        when(contrato.getDataInicio()).thenReturn(null);
        when(statusDestinacao.getId()).thenReturn(2);
        validadorDoacao.validadorEspecifico(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoErroEncargoNulo() throws NegocioException {
        when(doacao.getEncargos()).thenReturn(null);
        when(statusDestinacao.getId()).thenReturn(5);
        validadorDoacao.validadorEspecifico(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoErroEncargoVazio() throws NegocioException {
        when(doacao.getEncargos()).thenReturn(new ArrayList<>());
        when(statusDestinacao.getId()).thenReturn(6);
        validadorDoacao.validadorEspecifico(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoErroEncargoAssinaturaMenorDataContrato() throws NegocioException {

        Calendar calendarDataContratro = Calendar.getInstance();
        calendarDataContratro.set(2016, Calendar.NOVEMBER, 15);
        Calendar calendarDataEncargo = Calendar.getInstance();
        calendarDataEncargo.set(2016, Calendar.NOVEMBER, 14);

        when(contrato.getDataInicio()).thenReturn(calendarDataContratro.getTime());
        when(encargo.getDataCumprimento()).thenReturn(calendarDataEncargo.getTime());
        when(statusDestinacao.getId()).thenReturn(9);

        validadorDoacao.validadorEspecifico(doacao);
    }

    @Test(expected = NegocioException.class)
    public void validadorEspecificoFundamentoLegalNulo() throws NegocioException {
        when(doacao.getCodFundamentoLegal()).thenReturn(null);
        when(statusDestinacao.getId()).thenReturn(8);
        validadorDoacao.validar(doacao);
    }


}

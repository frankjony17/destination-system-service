package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.entidades.TipoDestinacao;
import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

/**
 * Created by diego on 03/01/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest
public class URLIntegracaoUtilsTest {

    private static final String URL_CADASTRO_IMOVEIS = "cadastroimoves.basis.com.br/";
    private static final String URL_SERVICOS = "servicos.basis.com.br/";
    private static final String URL_ACESSOS = "cadastroimoves.basis.com.br/";
    private static final String URL_INTEGRADOR = "integrador.basis.com.br/";
    private static final String URL_INFOCONVWS = "infoconvws.basis.com.br/";

    @InjectMocks
    private URLIntegracaoUtils urlIntegracaoUtils;


    @Before
    public void setUp() {
        ReflectionTestUtils.setField(urlIntegracaoUtils, "urlCadastroImoveis", URL_CADASTRO_IMOVEIS);
        ReflectionTestUtils.setField(urlIntegracaoUtils, "urlServicos", URL_SERVICOS);
        ReflectionTestUtils.setField(urlIntegracaoUtils, "urlAcessos", URL_ACESSOS);
        ReflectionTestUtils.setField(urlIntegracaoUtils, "urlIntegrador", URL_INTEGRADOR);
        ReflectionTestUtils.setField(urlIntegracaoUtils, "urlInfoConvWS", URL_INFOCONVWS);
    }

    @Test
    public void getUrlGetValidarImovelPorRip() {
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/imovel/getImovelDestinacao/123";
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveis("123");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlImovelCadastroImoveisSuspensao(){
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/imovel/buscarSuspensaoPeloRip/123";
        String url = urlIntegracaoUtils.getUrlImovelCadastroImoveisSuspensao("123");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlImovelSuspensoMotivo(){
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/imovel/buscarSuspensaoPeloRipMotivacao/123/1";
        String url = urlIntegracaoUtils.getUrlImovelSuspensoMotivo("123", 1L);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlDesfazerCancelamento(){
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/imovel/desfazer-cancelamento/123";
        String url = urlIntegracaoUtils.getUrlDesfazerCancelamento("123");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetRequerimentoByID() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/requerimento/1";
        String url = urlIntegracaoUtils.getUrlGetRequerimentoByID(1L);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getBuscarRequerimentoAnaliseTecnica() {
        String urlEsperada = URL_SERVICOS + "servicos/api/requerimento/consultar/destinacao";
        String url = urlIntegracaoUtils.getBuscarRequerimentoAnaliseTecnica();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetPendenciasRequerimento() {
        String urlEsperada = URL_SERVICOS + "servicos/api/historico/findByRequerimento/1?offset=1&limit=10";
        String url = urlIntegracaoUtils.getUrlGetPendenciasRequerimento(1L, 1, 10);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetAlterarStatus() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/requerimento/alterar-status/CANCELADO/1";
        String url = urlIntegracaoUtils.getUrlGetAlterarStatus("CANCELADO", 1L);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetDonwloadGestaoPraia() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/comum/download/1";
        String url = urlIntegracaoUtils.getUrlGetDonwloadGestaoPraia("1");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetRequerimentoAnaliseTecnicaByID() {
        String urlEsperada = URL_SERVICOS + "servicos/api/analiseRequerimento/findByAnaliseRequerimento/1";
        String url = urlIntegracaoUtils.getUrlGetRequerimentoAnaliseTecnicaByID(1L);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetRequerimentoByNumeroAtendimento() {
        String urlEsperada = URL_SERVICOS + "servicos/api/comum/buscar-requerimento-atendimento?numeroAtendimento=1";
        String url = urlIntegracaoUtils.getUrlGetRequerimentoByNumeroAtendimento("1");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetRequerimentoByNumeroProcesso() {
        String urlEsperada = URL_SERVICOS + "servicos/api/comum/buscar-requerimento-numero-processo?numeroProcesso=1";
        String url = urlIntegracaoUtils.getUrlGetRequerimentoByNumeroProcesso("1");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUsosImoveis() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/comum/imovel/usoPrincipal";
        String url = urlIntegracaoUtils.getUsosImoveis();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getVerificarNumeroProcesso() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/sei/verificarNumeroSEI/1/2";
        String url = urlIntegracaoUtils.getVerificarNumeroProcesso(1L, "2");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlDownlodaArquivoServico() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/arquivo/download/1";
        String url = urlIntegracaoUtils.getUrlDownlodaArquivoServico(1L);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlListarArquivosServicos() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/arquivo-requerimento/destinacacao/documentos/1/1";
        String url = urlIntegracaoUtils.getUrlListarArquivosServicos(1L, 1L);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlListaTituloServico() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/servico/lista/destinacao";
        String url = urlIntegracaoUtils.getUrlListaTituloServico();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlBuscarRestricoes(){
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/dominio/restricoes-suspensao/all";
        String url = urlIntegracaoUtils.getUrlBuscarRestricoes();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlSuspenderImovel(){
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/imovel/suspensao/cadastro";
        String url = urlIntegracaoUtils.getUrlSuspenderImovel();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlCancelarImovel(){
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/imovel/cancelamento-imovel";
        String url = urlIntegracaoUtils.getUrlCancelarImovel();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlCancelarSuspensaoImovel(){
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/imovel/suspensao/cancelar";
        String url = urlIntegracaoUtils.getUrlCancelarSuspensaoImovel();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlAlterarStatusAnaliseTecnica() {
        String urlEsperada = URL_SERVICOS + "servicos/api/public/requerimento/alterarStatus/analiseTecnica/1/CANCELADO";
        String url = urlIntegracaoUtils.getUrlAlterarStatusAnaliseTecnica(1L, StatusAnaliseTecnicaEnum.CANCELADO);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlBuscarEnderecoByCep(){
        String urlEsperada = URL_INTEGRADOR + "integrador/api/servico/busca-endereco-cep/00000000";
        String url = urlIntegracaoUtils.getUrlBuscarEnderecoByCep("00000000");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getBuscarFundamentoLegalIntegradorByTipoDestinacaoEnum(){
        String urlEsperada = URL_INTEGRADOR + "integrador/api/fundamento-legal/pesquisar-por-tipo/CESSAO_ONEROSA";
        String url = urlIntegracaoUtils.getBuscarFundamentoLegalIntegradorByTipoDestinacaoEnum(TipoDestinacaoEnum.CESSAO_ONEROSA);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetBuscarMunicipioPorUf() {
        String urlEsperada = URL_INTEGRADOR + "integrador/api/servico/busca-municipios/DF";
        String url = urlIntegracaoUtils.getUrlGetBuscarMunicipioPorUf("DF");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetBuscarDadosPessoaFisicaHistoricoCampo(){
        String urlEsperada = URL_INFOCONVWS + "dados-pessoa/fisica/historico?cpf=00000000000&campo=campo";
        String url = urlIntegracaoUtils.getUrlGetBuscarDadosPessoaFisicaHistoricoCampo("00000000000", "campo");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetBuscarDadosPessoaJuridicaHistoricoCampo(){
        String urlEsperada = URL_INFOCONVWS + "dados-pessoa/juridica/historico?cnpj=00000000000&campo=campo";
        String url = urlIntegracaoUtils.getUrlGetBuscarDadosPessoaJuridicaHistoricoCampo("00000000000", "campo");
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlBuscarResponsavelImovel(){
        String urlEsperada = URL_CADASTRO_IMOVEIS + "incorporacao/api/proprietario/buscarPorIdImovel/1";
        String url = urlIntegracaoUtils.getUrlBuscarResponsavelImovel(1L);
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetBuscarDadosPessoa() {
        String urlEsperada = URL_INFOCONVWS + "dados-pessoa/fisica";
        String url = urlIntegracaoUtils.getUrlGetBuscarDadosPessoaFisica();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetDocumentosPessoaFisica(){
        String urlEsperada = URL_INTEGRADOR + "integrador/api/documento/pessoa-fisica/";
        String url = urlIntegracaoUtils.getUrlGetDocumentosPessoaFisica();
        assertEquals(urlEsperada, url);
    }

    @Test
    public void getUrlGetBuscarDadosPessoaJuridica() {
        String urlEsperada = URL_INFOCONVWS + "dados-pessoa/juridica";
        String url = urlIntegracaoUtils.getUrlGetBuscarDadosPessoaJuridica();
        assertEquals(urlEsperada, url);
    }
}

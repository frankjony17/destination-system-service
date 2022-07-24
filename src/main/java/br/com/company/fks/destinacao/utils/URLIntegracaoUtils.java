package br.com.company.fks.destinacao.utils;


import br.com.company.fks.destinacao.dominio.enums.StatusAnaliseTecnicaEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por urls do integração
 * Created by Basis Tecnologia on 27/05/2016.
 */

@Component
public class URLIntegracaoUtils {


    /* urls */
    @Value("${url.cadastro.imovel}")
    private String urlCadastroImoveis;

    @Value("${url.servicos}")
    private String urlServicos;

    @Value("${url.acessos}")
    private String urlAcessos;

    @Value("${url.integrador}")
    private String urlIntegrador;

    @Value("${url.infoconvws}")
    private String urlInfoConvWS;

    private static final String ALTERAR_STATUS_REQUERIMENTO = "servicos/api/public/requerimento/alterar-status/%S/%S";

    private static final String BUSCAR_REQUERIMENTO = "servicos/api/public/requerimento/%S";

    private static final String BUSCAR_REQUERIMENTO_ANALISE_TECNICA = "servicos/api/requerimento/consultar/destinacao";

    private static final String BUSCAR_PENDENCIAS_REQUERIMENTO = "servicos/api/historico/findByRequerimento/%S?offset=%S&limit=%S";

    private static final String BUSCAR_ANALISE_TEECNICA_REQUERIMENTO = "servicos/api/analiseRequerimento/findByAnaliseRequerimento/%S";

    private static final String RESOURCE_CONSULTAR_IMOVEL_RIP = "incorporacao/api/imovel/getImovelDestinacao/%S";

    private static final String RESOURCE_CONSULTAR_REQUERIMENTO_ATENDIMENTO = "servicos/api/comum/buscar-requerimento-atendimento?numeroAtendimento=%S";

    private static final String RESOURCE_CONSULTAR_REQUERIMENTO_PROCESSO = "servicos/api/comum/buscar-requerimento-numero-processo?numeroProcesso=%S";

    private static final String RESOURCE_USOS_REGIMES = "servicos/api/public/comum/imovel/usoPrincipal";

    private static final String RESOURCE_VALIDA_NUMERO_SEI = "servicos/api/public/sei/verificarNumeroSEI/%S/%S";

    private static final String RESOURCE_DOWNLOAD_ARQUIVO_SERVICO = "servicos/api/public/arquivo/download/%S";

    private static final String RESOURCE_DOWNLOAD_GESTAO_PRAIA = "servicos/api/public/comum/download/%s";

    private static final String RESOURCE_LISTAR_ARQUIVOS_SERVICOS = "servicos/api/public/arquivo-requerimento/destinacacao/documentos/%S/%S";

    private static final String LISTA_TITULOS_SERVICO = "servicos/api/public/servico/lista/destinacao";

    private static final String ALTERAR_STATUS_REQ_ANALISE_TECNICA = "servicos/api/public/requerimento/alterarStatus/analiseTecnica/%S/%S";

    private static final String RESOURCE_ENDERECO_BY_CEP = "integrador/api/servico/busca-endereco-cep/";

    private static final String BUSCAR_FUNDAMENTO_LEGAL_INTEGRADOR = "integrador/api/fundamento-legal/pesquisar-por-tipo/%S";

    private static final String BUSCAR_DADOS_DE_SUSPENSAO= "incorporacao/api/imovel/buscarSuspensaoPeloRip/%S";

    private static final String BUSCAR_DADOS_DE_SUSPENSAO_MOTIVO = "incorporacao/api/imovel/buscarSuspensaoPeloRipMotivacao/%S/%S";

    private static final String BUSCAR_MUNICIPIO_UF = "integrador/api/servico/busca-municipios/%S";

    private static final String BUSCAR_DOCUMENTOS_PESSOA_FISICA = "integrador/api/documento/pessoa-fisica/";

    private static final String BUSCAR_DADOS_PESSOA_FISICA = "dados-pessoa/fisica";

    private static final String BUSCAR_DADOS_PESSOA_JURIDICA = "dados-pessoa/juridica";

    private static final String BUSCAR_DADOS_PESSOA_FISICA_HISTORICO_CAMPO = "dados-pessoa/fisica/historico?cpf=%s&campo=%s";

    private static final String BUSCAR_DADOS_PESSOA_JURIDICA_HISTORICO_CAMPO = "dados-pessoa/juridica/historico?cnpj=%s&campo=%s";

    private static final String BUSCAR_RESPONSAVEL = "incorporacao/api/proprietario/buscarPorIdImovel/%S";

    private static final String SUSPENDER_IMOVEL = "incorporacao/api/imovel/suspensao/cadastro";

    private static final String CANCELAR_SUSPESAO_IMOVEL = "incorporacao/api/imovel/suspensao/cancelar";

    private static final String CANCELAR_IMOVEL = "incorporacao/api/imovel/cancelamento-imovel";

    private static final String BUSCAR_RESTRICOES = "incorporacao/api/dominio/restricoes-suspensao/all";

    private static final String DESFAZER_CANCELAMENTO = "incorporacao/api/imovel/desfazer-cancelamento/%S";
    /**
     *
     * @param rip
     * @return
     */
    public String getUrlImovelCadastroImoveis(String rip){
        String resources = urlCadastroImoveis + RESOURCE_CONSULTAR_IMOVEL_RIP;
        return String.format(resources, rip);
    }



    /**
     *
     */
    public String getUrlImovelCadastroImoveisSuspensao(String rip){
        String url = montaUrl(urlCadastroImoveis, BUSCAR_DADOS_DE_SUSPENSAO);
        return String.format(url, rip);

    }

    public String getUrlImovelSuspensoMotivo(String rip, Long idMotivacao){
        String url = montaUrl(urlCadastroImoveis, BUSCAR_DADOS_DE_SUSPENSAO_MOTIVO);
        return String.format(url, rip, idMotivacao);
    }

    public String getUrlDesfazerCancelamento(String rip){
        String url = montaUrl(urlCadastroImoveis, DESFAZER_CANCELAMENTO);
        return String.format(url, rip);
    }
    /**
     *
     * @param id
     * @return
     */
    public String getUrlGetRequerimentoByID(Long id){
        String url = montaUrl(urlServicos, BUSCAR_REQUERIMENTO);
        return String.format(url, id);
    }

    /**
     *
     * @return
     */
    public String getUrlBuscarRestricoes(){
        return montaUrl(urlCadastroImoveis, BUSCAR_RESTRICOES);
    }

    /**
     *
     * @return
     */
    public String getUrlSuspenderImovel(){
        return montaUrl(urlCadastroImoveis, SUSPENDER_IMOVEL);
    }
    /**
     *
     * @return
     */
    public String getUrlCancelarImovel(){
        return montaUrl(urlCadastroImoveis, CANCELAR_IMOVEL);
    }

    /**
     *
     * @return
     */
    public String getUrlCancelarSuspensaoImovel(){
        return montaUrl(urlCadastroImoveis, CANCELAR_SUSPESAO_IMOVEL);
    }

    /**
     *
     * @return
     */
    public String getBuscarRequerimentoAnaliseTecnica(){
        return  urlServicos + BUSCAR_REQUERIMENTO_ANALISE_TECNICA;

    }

    /**
     *
     * @param idRequerimento
     * @param offset
     * @param limit
     * @return
     */
    public String getUrlGetPendenciasRequerimento(Long idRequerimento, int offset, int limit){
        String url = montaUrl(urlServicos, BUSCAR_PENDENCIAS_REQUERIMENTO);
        return String.format(url, idRequerimento, offset, limit);
    }

    /**
     *
     * @param status
     * @param idRequerimento
     * @return
     */
    public String getUrlGetAlterarStatus(String status, Long idRequerimento) {
        String url = montaUrl(urlServicos, ALTERAR_STATUS_REQUERIMENTO);
        return String.format(url, status, idRequerimento);
    }

    /**
     *
     * @param arquivo
     * @return
     */
    public String getUrlGetDonwloadGestaoPraia(String arquivo) {
        String url = montaUrl(urlServicos, RESOURCE_DOWNLOAD_GESTAO_PRAIA);
        return String.format(url, arquivo);
    }

    /**
     *
     * @param id
     * @return
     */
    public String getUrlGetRequerimentoAnaliseTecnicaByID(Long id) {
        String url = montaUrl(urlServicos, BUSCAR_ANALISE_TEECNICA_REQUERIMENTO);
        return String.format(url, id);
    }

    /**
     *
     * @param numeroAtendimento
     * @return
     */
    public String getUrlGetRequerimentoByNumeroAtendimento(String numeroAtendimento){
        String resources = urlServicos + RESOURCE_CONSULTAR_REQUERIMENTO_ATENDIMENTO;
        return String.format(resources, numeroAtendimento);
    }

    /**
     *
     * @param numeroProcesso
     * @return
     */
    public String getUrlGetRequerimentoByNumeroProcesso(String numeroProcesso){
        String resources = urlServicos + RESOURCE_CONSULTAR_REQUERIMENTO_PROCESSO;
        return String.format(resources, numeroProcesso);
    }

    /**
     *
     * @return
     */
    public String getUsosImoveis() {
        String url = montaUrl(urlServicos, RESOURCE_USOS_REGIMES);
        return String.format(url);
    }

    /**
     *
     * @param idRequerimento
     * @param idProcedimento
     * @return
     */
    public String getVerificarNumeroProcesso(Long idRequerimento, String idProcedimento) {
        String url = montaUrl(urlServicos, RESOURCE_VALIDA_NUMERO_SEI);
        return String.format(url, idRequerimento, idProcedimento);
    }

    /**
     *
     * @param id
     * @return
     */
    public String getUrlDownlodaArquivoServico(Long id) {
        String url = montaUrl(urlServicos, RESOURCE_DOWNLOAD_ARQUIVO_SERVICO);
        return String.format(url, id);
    }

    /**
     *
     * @param idRequerimento
     * @param idDocumento
     * @return
     */
    public String getUrlListarArquivosServicos(Long idRequerimento, Long idDocumento) {
        String url = montaUrl(urlServicos, RESOURCE_LISTAR_ARQUIVOS_SERVICOS);
        return String.format(url, idRequerimento,idDocumento);
    }

    /**
     *
     * @return
     */
    public String getUrlListaTituloServico() {
        return urlServicos + LISTA_TITULOS_SERVICO;
    }

    /**
     *
     * @param idRequerimento
     * @param statusAnaliseTecnicaEnum
     * @return
     */
    public String getUrlAlterarStatusAnaliseTecnica(Long idRequerimento, StatusAnaliseTecnicaEnum statusAnaliseTecnicaEnum) {
        String url = montaUrl(urlServicos, ALTERAR_STATUS_REQ_ANALISE_TECNICA);
        return String.format(url, idRequerimento, statusAnaliseTecnicaEnum);
    }

    /**
     *
     * @param cep
     * @return
     */
    public String getUrlBuscarEnderecoByCep(String cep) {
        String url = montaUrl(urlIntegrador, RESOURCE_ENDERECO_BY_CEP) + cep;
        return String.format(url);
    }

    /**
     * Montar url de busca de fundamento Legal do integrador
     *@return
     */
    public String getBuscarFundamentoLegalIntegradorByTipoDestinacaoEnum(TipoDestinacaoEnum tipoDestinacaoEnum){
        String url = montaUrl(urlIntegrador, BUSCAR_FUNDAMENTO_LEGAL_INTEGRADOR);
        return String.format(url, tipoDestinacaoEnum);

    }



    /**
     *
     * @param uf
     * @return
     */
    public String getUrlGetBuscarMunicipioPorUf(String uf){
        String url = montaUrl(urlIntegrador, BUSCAR_MUNICIPIO_UF);
        return String.format(url, uf);
    }

    /**
     *
     * @return
     */
    public String getUrlGetBuscarDadosPessoaFisica(){
        return montaUrl(urlInfoConvWS, BUSCAR_DADOS_PESSOA_FISICA);
    }

    /**
     *
     * @return
     */
    public String getUrlGetDocumentosPessoaFisica(){
        return montaUrl(urlIntegrador, BUSCAR_DOCUMENTOS_PESSOA_FISICA);
    }

    /**
     *
     * @return
     */
    public String getUrlGetBuscarDadosPessoaJuridica(){
        return montaUrl(urlInfoConvWS, BUSCAR_DADOS_PESSOA_JURIDICA);
    }

    /**
     *
     * @return
     */
    public String getUrlGetBuscarDadosPessoaFisicaHistoricoCampo(String cpf, String campo){
        String baseUrl = montaUrl(urlInfoConvWS, BUSCAR_DADOS_PESSOA_FISICA_HISTORICO_CAMPO);
        return String.format(baseUrl, cpf, campo);
    }

    /**
     *
     * @return
     */
    public String getUrlGetBuscarDadosPessoaJuridicaHistoricoCampo(String cnpj, String campo){
        String baseUrl = montaUrl(urlInfoConvWS, BUSCAR_DADOS_PESSOA_JURIDICA_HISTORICO_CAMPO);
        return String.format(baseUrl, cnpj, campo);
    }


    /**
     * Método que monta a url para buscar o responsável do imóvel pelo id no cadastro de imóveis
     * @param id
     * @return url montada
     */
    public String getUrlBuscarResponsavelImovel(Long id){
        String url = montaUrl(urlCadastroImoveis, BUSCAR_RESPONSAVEL);
        return String.format(url,id);
    }

    private String montaUrl(String urlPadrao, String urlServico) {
        StringBuilder url = new StringBuilder(urlPadrao);
        url.append(urlServico);
        return url.toString();
    }

}

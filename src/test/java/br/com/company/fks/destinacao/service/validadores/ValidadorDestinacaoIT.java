package br.com.company.fks.destinacao.service.validadores;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.apresentacao.builder.StatusDestinacaoStatusBuilder;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorCdru;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorUsoProprio;
import br.com.company.fks.destinacao.utils.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by haillanderson on 19/04/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ValidadorDestinacaoIT extends BaseIntegrationTestCofig{

    @Autowired
    private ValidadorCdru validadorCdru;

    @Autowired
    private ValidadorUsoProprio validadorUsoProprio;

    @Test
    public void validaImovelJurisdicaoUsuarioLogadoDestinacaoImoveisNull(){
        validadorCdru.validaImovelJurisdicaoUsuarioLogado(null, null);
    }

    @Test
    public void validaImovelJurisdicaoUsuarioLogadoDestinacaoImoveisUfDiferente(){
        List<String> erros = new ArrayList<>();
        validadorCdru.validaImovelJurisdicaoUsuarioLogado(asList(criaDestinacaoImovel()), erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "O RIP informado não está sob jurisdição da sua unidade de lotação");
    }

    @Test
    public void validarImoveisEstadoDiferenteDestinacaoImoveisVazio(){
        validadorCdru.validarImoveisEstadoDiferente(Collections.EMPTY_LIST, new ArrayList<>());
    }

    @Test
    public void validarImoveisEstadoDiferenteDestinacaoImoveis(){
        List<String> erros = new ArrayList<>();
        List<DestinacaoImovel> destinacoes = new ArrayList<>();
        DestinacaoImovel destinacao1 = criaDestinacaoImovel();
        DestinacaoImovel destinacao2 = criaDestinacaoImovel();
        destinacao2.getImovel().getEndereco().setUf("DF");
        destinacoes.add(destinacao1);
        destinacoes.add(destinacao2);
        validadorCdru.validarImoveisEstadoDiferente(destinacoes,erros);
        assertEquals(erros.get(Constants.ZERO), "Não é possóvel adicionar imóveis localizados em UFs diferentes");
        assertTrue(!erros.isEmpty());
    }

    /*@Test
    public void validarCpfCnpjResponsaveisNull(){
        List<Responsavel> responsaveis = null;
        validadorCdru.validarCpfCnpj(responsaveis, new ArrayList<>());
    }

    @Test
    public void validarCpfCnpjResponsaveisVazio(){
        validadorCdru.validarCpfCnpj(Collections.emptyList(), new ArrayList<>());
    }

    @Test
    public void validarCpfCnpjCPFValido(){
        List<String> erros = new ArrayList<>();
        validadorCdru.validarCpfCnpj(criarListaResponsavel("00000000191"), erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarCpfCnpjCPFInvalido(){
        List<String> erros = new ArrayList<>();
        validadorCdru.validarCpfCnpj(criarListaResponsavel("00000000192"), erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "O CPF informado não é válido");
    }

    @Test
    public void validarCpfCnpjCNPJValido(){
        List<String> erros = new ArrayList<>();
        validadorCdru.validarCpfCnpj(criarListaResponsavel("00000000000191"), erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarCpfCnpjCNPJInvalido(){
        List<String> erros = new ArrayList<>();
        validadorCdru.validarCpfCnpj(criarListaResponsavel("00000000000192"), erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "O CNPJ informado não é válido");
    }*/

    @Test
    public void validarCamposObrigatorioContratoComDataInicio() {
        List<String> erros = new ArrayList<>();
        Destinacao destinacao = new Destinacao();
        destinacao.setStatusDestinacao(StatusDestinacaoStatusBuilder.getBuilder().setId(1).build());
        destinacao.setContrato(criaContrato());
        validadorCdru.validarCamposObrigatorioContrato(destinacao, erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarCamposObrigatorioContratoNull(){
        List<String> erros = new ArrayList<>();
        Destinacao destinacao = new Destinacao();
        destinacao.setStatusDestinacao(StatusDestinacaoStatusBuilder.getBuilder().setId(1).build());
        validadorCdru.validarCamposObrigatorioContrato(destinacao, erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "O campo Data ínicio da Vigência não pode ser vazio");
    }

    @Test
    public void validarCamposObrigatorioContratoDataInicioNull(){
        List<String> erros = new ArrayList<>();
        Destinacao destinacao = new Destinacao();
        destinacao.setStatusDestinacao(StatusDestinacaoStatusBuilder.getBuilder().setId(1).build());
        destinacao.setContrato(new Contrato());
        validadorCdru.validarCamposObrigatorioContrato(destinacao, erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "O campo Data ínicio da Vigência não pode ser vazio");
    }

    @Test
    public void validarDatasEntreEncargoEAssinaturaContratoNull(){
        List<String> erros = new ArrayList<>();
        Encargo encargo = new Encargo();
        validadorCdru.validarDatasEntreEncargoEAssinatura(null, asList(encargo),erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "É obrigatório informar um contrato");
    }

    @Test
    public void validarDatasEntreEncargoEAssinaturaDataInicioNull(){
        List<String> erros = new ArrayList<>();
        Encargo encargo = new Encargo();
        Contrato contrato = criaContrato();
        contrato.setDataInicio(null);
        validadorCdru.validarDatasEntreEncargoEAssinatura(contrato, asList(encargo),erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "O campo Data ínicio da Vigência não pode ser vazio");
    }

    @Test
    public void validaExisteDestinacaoImovelInformadoDestinacaoImovelVazio(){
        List<String> erros = new ArrayList<>();
        validadorCdru.validaExisteDestinacaoImovelInformado(Collections.EMPTY_LIST, erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "É obrigatorio informar um imóvel");
    }

    @Test
    public void validarCampoTipoLicitacaoNull(){
        List<String> erros = new ArrayList<>();
        Licitacao licitacao = new Licitacao();
        licitacao.setTipoLicitacao(null);
        validadorCdru.validarCampoTipoLicitacao(licitacao,erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "O campo Tipo/Modalidade não pode ser vazio");
    }

    @Test
    public void validarCampoTipoLicitacaoIdNull(){
        List<String> erros = new ArrayList<>();
        Licitacao licitacao = new Licitacao();
        TipoLicitacao tipoLicitacao = new TipoLicitacao();
        licitacao.setTipoLicitacao(tipoLicitacao);
        validadorCdru.validarCampoTipoLicitacao(licitacao,erros);
        assertTrue(!erros.isEmpty());
        assertEquals(erros.get(Constants.ZERO), "O campo Tipo/Modalidade não pode ser vazio");
    }

    @Test
    public void validarCampoTipoLicitacao(){
        List<String> erros = new ArrayList<>();
        Licitacao licitacao = new Licitacao();
        TipoLicitacao tipoLicitacao = new TipoLicitacao();
        tipoLicitacao.setId(1);
        licitacao.setTipoLicitacao(tipoLicitacao);
        validadorCdru.validarCampoTipoLicitacao(licitacao,erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarDatasEntreEncargoEAssinaturaDataEncargoNaoNulaMaiorDataContrato() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2030, 1, 1);
        List<String> erros = new ArrayList<>();
        Contrato contrato = new Contrato();
        contrato.setDataInicio(new Date());
        Encargo encargo = new Encargo();
        encargo.setDataCumprimento(calendar.getTime());
        List<Encargo> encargos = asList(encargo);
        validadorCdru.validarDatasEntreEncargoEAssinatura(contrato, encargos, erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarDatasEntreEncargoEAssinaturaDataEncargoNulaMaiorDataContrato() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2030, 1, 1);
        List<String> erros = new ArrayList<>();
        Contrato contrato = new Contrato();
        contrato.setDataInicio(new Date());
        Encargo encargo = new Encargo();
        List<Encargo> encargos = asList(encargo);
        validadorCdru.validarDatasEntreEncargoEAssinatura(contrato, encargos, erros);
        assertTrue(erros.isEmpty());
    }

    @Test
    public void validarDatasEntreEncargoEAssinaturaDataEncargoNaoNulaMenorDataContrato() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 1, 1);
        List<String> erros = new ArrayList<>();
        Contrato contrato = new Contrato();
        contrato.setDataInicio(new Date());
        Encargo encargo = new Encargo();
        encargo.setDataCumprimento(calendar.getTime());
        List<Encargo> encargos = asList(encargo);
        validadorCdru.validarDatasEntreEncargoEAssinatura(contrato, encargos, erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void validarDatasEntreEncargoEAssinaturaDataEncargoIgualDataContrato() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 1, 1);
        Date data = (Date) calendar.getTime().clone();
        List<String> erros = new ArrayList<>();
        Contrato contrato = new Contrato();
        contrato.setDataInicio(data);
        Encargo encargo = new Encargo();
        encargo.setDataCumprimento(data);
        List<Encargo> encargos = asList(encargo);
        validadorCdru.validarDatasEntreEncargoEAssinatura(contrato, encargos, erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void validarExisteFotos(){
        List<Foto> fotos = new ArrayList<>();
        List<String> erros = new ArrayList<>();
        validadorUsoProprio.validarExisteFotos(fotos, erros);
        assertFalse(erros.isEmpty());
    }

    @Test
    public void validarNaoExisteFotos(){
        List<Foto> fotos = new ArrayList<>();
        List<String> erros = new ArrayList<>();
        Foto foto = new Foto();
        foto.setId(1L);
        fotos.add(foto);
        validadorUsoProprio.validarExisteFotos(fotos, erros);
        assertTrue(erros.isEmpty());
    }

    private DestinacaoImovel criaDestinacaoImovel(){
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(criaImovel());
        return destinacaoImovel;
    }


    private Imovel criaImovel(){
        Imovel imovel = new Imovel();
        imovel.setEndereco(criaEndereco());
        return imovel;
    }

    private Endereco criaEndereco(){
        Endereco endereco = new Endereco();
        endereco.setUf("SE");
        return endereco;
    }

    /*private Responsavel criaResponsavel(){
        Responsavel responsavel = new Responsavel();
        responsavel.setId(1L);
        responsavel.setRegimeColetivoCnpj(false);
        return responsavel;
    }*/
    private List<Responsavel> criarListaResponsavel(String cpfCnpj){
        List<Responsavel> lista = new ArrayList<>();
       /* Responsavel responsavel = criaResponsavel();
        responsavel.setCpfCnpj(cpfCnpj);*/
    /*    lista.add(responsavel);*/
        return lista;
    }

    private Contrato criaContrato(){
        Contrato contrato = new Contrato();
        contrato.setId(1L);
        contrato.setDataInicio(new Date());
        return contrato;
    }
}
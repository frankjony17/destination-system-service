package br.com.company.fks.destinacao.service.validadores;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.EntidadeExtintaDTO;
import br.com.company.fks.destinacao.dominio.dto.FormaDeIncorporacaoPermitidaDTO;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoAquisicaoPermitidaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.UsuarioService;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipCategoriaCessao;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipCuem;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipGenerico;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipLocacao;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by haillanderson on 19/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ValidadorRipTest {

    @InjectMocks
    private ValidadorRipGenerico validadorRipGenerico;

    @InjectMocks
    private ValidadorRipCuem validadorRipCuem;

    @InjectMocks
    private ValidadorRipLocacao validadorRipLocacao;

    @InjectMocks
    private ValidadorRipCategoriaCessao validadorRipCategoriaCessao;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private Parcela parcela;

    private UsuarioLogadoDTO usuarioLogadoDTO = criarUsuarioLogadoJurisdicaoDF();

    @Before
    public void setup(){
        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);

    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarErroJurisdicao(){
        Endereco endereco = new Endereco();
        endereco.setUf("DF");
        Set<String> jurisdicoes = new HashSet<>();
        jurisdicoes.add("GO");
        Imovel imovel = new Imovel();
        imovel.setEndereco(endereco);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setJurisdicoes(jurisdicoes);
        when(usuarioService.getUsuarioLogado()).thenReturn(usuarioLogadoDTO);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test
    @SneakyThrows
    public void validarDoacaoCdruVendaSucesso(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroImovelParcelado(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        fundamentoLegalDTO.setIsPermitirImovelParcelado(false);
        imovel.setParcelas(asList(parcela, parcela));
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test
    @SneakyThrows
    public void validarDoacaoCdruVendaImovelParcelado(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        fundamentoLegalDTO.setIsPermitirImovelParcelado(false);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroTipoDeImovel(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoTipoImovel(5L);
        fundamentoLegalDTO.setIsPermitirImoveisCavidadeNatural(false);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroTipoDeImovelNull(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoTipoImovel(5L);
        fundamentoLegalDTO.setIsPermitirImoveisCavidadeNatural(null);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test
    @SneakyThrows
    public void validarDoacaoCdruVendaTipoDeImovel(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoTipoImovel(5L);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroTipoDeImovelEspelho(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoTipoImovel(3L);
        fundamentoLegalDTO.setIsPermitirImoveisEspelhoDAgua(false);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test
    @SneakyThrows
    public void validarDoacaoCdruVendaTipoDeImovelEspelho(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoTipoImovel(3L);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroNaturezaRural(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoNaturezaImovel(2L);
        fundamentoLegalDTO.setIsNaturezaRural(false);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroNaturezaUrbana(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoNaturezaImovel(1L);
        fundamentoLegalDTO.setIsNaturezaUrbana(false);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroSituacaoIncorporado(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        fundamentoLegalDTO.setIsincorporado(false);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroSituacaoEmProcesso(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoSituacaoIncorporacao(2L);
        fundamentoLegalDTO.setIsEmProcessoIncorporacao(false);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroSituacaoNSeAplica(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoSituacaoIncorporacao(3L);
        fundamentoLegalDTO.setIsNaoSeAplica(false);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroFormaIncorporacao(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        List<FormaDeIncorporacaoPermitidaDTO> list = fundamentoLegalDTO.getFormaDeIncorporacaoPermitidas();
        list.remove(0);
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test
    @SneakyThrows
    public void validarDoacaoCdruVendaErroFormaIncorporacaoPorAquisicao(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoFormaIncorporacao(2L);
        fundamentoLegalDTO.setTipoAquisicaoPermitidas(criarTipoAquisicao(1L, "Reversão"));
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroFormaIncorporacaoPorAquisicaoErro(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoFormaIncorporacao(2L);
        fundamentoLegalDTO.setTipoAquisicaoPermitidas(criarTipoAquisicao(2L, ""));
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test
    @SneakyThrows
    public void validarDoacaoCdruVendaErroFormaIncorporacaoPorEntidadeExtinta(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoFormaIncorporacao(2L);
        imovel.setCodigoTipoAquisicao(9L);
        imovel.setCodigoEntidadeExtinta(1L);
        fundamentoLegalDTO.setTipoAquisicaoPermitidas(criarTipoAquisicao(9L, "Sucessão por Entidades da APF"));
        fundamentoLegalDTO.setEntidadeExtintas(criarEntidadeExtinta(1L, "Ministério da Adm. Federal e Reforma do Estado"));
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErroFormaIncorporacaoPorEntidadeExtintaErro(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = criarFundamentoLegal();
        imovel.setCodigoFormaIncorporacao(2L);
        imovel.setCodigoTipoAquisicao(9L);
        imovel.setCodigoEntidadeExtinta(1L);
        fundamentoLegalDTO.setTipoAquisicaoPermitidas(criarTipoAquisicao(9L, "Sucessão por Entidades da APF"));
        fundamentoLegalDTO.setEntidadeExtintas(criarEntidadeExtinta(3L, ""));
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Ignore
    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarDoacaoCdruVendaErro(){
        Imovel imovel = criarImovelDFIncorporadoOuCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipGenerico.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarCUEMSucesso(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        imovel.setCodigoNaturezaImovel(1L);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipGenerico.validar(imovel, 2L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarCUEMErro(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        imovel.setCodigoNaturezaImovel(1L);
        imovel.setAreaTerreno(new BigDecimal(251));
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipCuem.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarCUEMErroArea(){
        Imovel imovel = criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua();
        imovel.setCodigoNaturezaImovel(1L);
        imovel.setAreaTerreno(new BigDecimal(249));
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipCuem.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarLocacaoSucesso(){
        Imovel imovel = criarImovelDFIsImovelCavidadeOuEspelhoDagua();
        imovel.setCodigoTipoImovel(5L);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipLocacao.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarLocacaoErro(){
        Imovel imovel = criarImovelDFIsImovelCavidadeOuEspelhoDagua();
        imovel.setCodigoTipoImovel(1L);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipLocacao.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarCessaoEntregaSucesso(){
        Imovel imovel = criarImovelDFIsImovelCavidadeOuEspelhoDagua();
        imovel.setBenfeitorias(criarListaBenfeitorias());
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipCategoriaCessao.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Ignore
    @Test
    @SneakyThrows
    public void validarCessaoEntregaBenfeitoriasNull(){
        Imovel imovel = criarImovelDFIsImovelCavidadeOuEspelhoDagua();
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipCategoriaCessao.validar(imovel, 1L, fundamentoLegalDTO);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void validarCessaoEntregaErro(){
        Imovel imovel = criarImovelDFIsImovelCavidadeOuEspelhoDagua();
        Benfeitoria benfeitoria = new Benfeitoria();
        benfeitoria.setCodigo("E1");
        benfeitoria.setAreaDisponivel(new BigDecimal(0));
        List<Benfeitoria> benfeitorias = new ArrayList<>();
        benfeitoria.setAreaDisponivel(null);
        benfeitorias.add(benfeitoria);
        benfeitorias.add(benfeitoria);
        imovel.setBenfeitorias(benfeitorias);
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        validadorRipCategoriaCessao.validar(imovel, 1L, fundamentoLegalDTO);
    }

    private FundamentoLegalDTO criarFundamentoLegal(){
        FundamentoLegalDTO fundamentoLegalDTO = new FundamentoLegalDTO();
        fundamentoLegalDTO.setDescricao("Descrição");
        fundamentoLegalDTO.setIsPermitirImoveisEspelhoDAgua(true);
        fundamentoLegalDTO.setIsPermitirImoveisCavidadeNatural(true);
        fundamentoLegalDTO.setIsPermitirImovelParcelado(true);
        fundamentoLegalDTO.setIsNaturezaRural(true);
        fundamentoLegalDTO.setIsNaturezaUrbana(true);
        fundamentoLegalDTO.setIsEmProcessoIncorporacao(true);
        fundamentoLegalDTO.setIsincorporado(true);
        fundamentoLegalDTO.setFormaDeIncorporacaoPermitidas(criarFormaDeIncorpora());
        return fundamentoLegalDTO;
    }

    private List<FormaDeIncorporacaoPermitidaDTO> criarFormaDeIncorpora(){
        FormaDeIncorporacaoPermitidaDTO formaDeIncorporacaoPermitidaDTO1 = new FormaDeIncorporacaoPermitidaDTO(1L, "Originalmente da União");
        FormaDeIncorporacaoPermitidaDTO formaDeIncorporacaoPermitidaDTO2 = new FormaDeIncorporacaoPermitidaDTO(2L, "Por Aquisição");
        FormaDeIncorporacaoPermitidaDTO formaDeIncorporacaoPermitidaDTO3 = new FormaDeIncorporacaoPermitidaDTO(3L, "Por Fracionamento/parcelamento");
        FormaDeIncorporacaoPermitidaDTO formaDeIncorporacaoPermitidaDTO4 = new FormaDeIncorporacaoPermitidaDTO(4L, "Por Fusão/Unificação");
        FormaDeIncorporacaoPermitidaDTO formaDeIncorporacaoPermitidaDTO5 = new FormaDeIncorporacaoPermitidaDTO(5L, "Imóveis de Terceiros");
        List<FormaDeIncorporacaoPermitidaDTO> list = new ArrayList<>();
        list.add(formaDeIncorporacaoPermitidaDTO1);
        list.add(formaDeIncorporacaoPermitidaDTO2);
        list.add(formaDeIncorporacaoPermitidaDTO3);
        list.add(formaDeIncorporacaoPermitidaDTO4);
        list.add(formaDeIncorporacaoPermitidaDTO5);
        return list;
    }

    private List<TipoAquisicaoPermitidaDTO> criarTipoAquisicao(Long id, String descricao){
        TipoAquisicaoPermitidaDTO tipoAquisicaoPermitidaDTO = new TipoAquisicaoPermitidaDTO(id, descricao);
        List<TipoAquisicaoPermitidaDTO> list = new ArrayList<>();
        list.add(tipoAquisicaoPermitidaDTO);
        return list;
    }
    private List<EntidadeExtintaDTO> criarEntidadeExtinta(Long id, String descricao){
        EntidadeExtintaDTO entidadeExtintaDTO = new EntidadeExtintaDTO(id, descricao);
        List<EntidadeExtintaDTO> list = new ArrayList<>();
        list.add(entidadeExtintaDTO);
        return list;
    }
    private UsuarioLogadoDTO criarUsuarioLogadoJurisdicaoDF(){
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        Set<String> jurisdicoes = new HashSet<>();
        jurisdicoes.add("DF");
        usuarioLogadoDTO.setJurisdicoes(jurisdicoes);
        return usuarioLogadoDTO;
    }

    private Imovel criarImovelDFNaoIncorporadoOuCavidadeOuEspelhoDagua(){
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setUf("DF");
        imovel.setEndereco(endereco);
        imovel.setCodigoSituacaoIncorporacao(1L);
        imovel.setCodigoTipoImovel(1L);
        imovel.setAtivo(true);
        imovel.setCodigoFormaIncorporacao(1L);
        imovel.setCodigoNaturezaImovel(1L);
        imovel.setParcelas(asList(parcela));
        imovel.setCodigoTipoAquisicao(1L);
        return imovel;
    }

    private Imovel criarImovelDFIncorporadoOuCavidadeOuEspelhoDagua(){
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setUf("DF");
        imovel.setEndereco(endereco);
        imovel.setCodigoSituacaoIncorporacao(2L);
        imovel.setCodigoTipoImovel(1L);
        return imovel;
    }

    private Imovel criarImovelDFIsImovelCavidadeOuEspelhoDagua(){
        Imovel imovel = new Imovel();
        Endereco endereco = new Endereco();
        endereco.setUf("DF");
        imovel.setEndereco(endereco);
        imovel.setCodigoSituacaoIncorporacao(2L);
        return imovel;
    }

    private List<Benfeitoria> criarListaBenfeitorias(){
        Benfeitoria benfeitoria = new Benfeitoria();
        benfeitoria.setCodigo("E1");
        benfeitoria.setAreaDisponivel(new BigDecimal(20));
        List<Benfeitoria> benfeitorias = new ArrayList<>();
        benfeitorias.add(benfeitoria);
        benfeitorias.add(benfeitoria);
        return benfeitorias;
    }
}
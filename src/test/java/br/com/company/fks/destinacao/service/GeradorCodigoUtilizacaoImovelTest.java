package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by diego on 01/02/17.
 */
@RunWith(PowerMockRunner.class)
public class GeradorCodigoUtilizacaoImovelTest {

    @InjectMocks
    private GeradorCodigoUtilizacaoImovel geradorCodigoUtilizacaoImovel;

    @Mock
    private DestinacaoImovelService destinacaoImovelService;

    @Before
    public void setUp() {
        when(destinacaoImovelService.getUltimaUtilizadaoImovel(anyLong())).thenReturn("0000");
    }

    @Test
    public void gerarCodigoUtilizacao() {
        List<DestinacaoImovel> destinacaoImoveis = criarListaDestinacaoImoveis();
        geradorCodigoUtilizacaoImovel.gerarCodigoUtilizacao(destinacaoImoveis);
        assertEquals("0001", destinacaoImoveis.get(0).getCodigoUtilizacao());
        assertEquals("0002", destinacaoImoveis.get(1).getCodigoUtilizacao());
        assertEquals("0001", destinacaoImoveis.get(2).getCodigoUtilizacao());
    }

    private List<DestinacaoImovel> criarListaDestinacaoImoveis() {
        List<DestinacaoImovel> destinacaoImoveis = new ArrayList<>();
        destinacaoImoveis.add(criarDestiancaoImovel(1L, "00000007"));
        destinacaoImoveis.add(criarDestiancaoImovel(1L, "00000007"));
        destinacaoImoveis.add(criarDestiancaoImovel(2L, "00000008"));
        return destinacaoImoveis;
    }

    private DestinacaoImovel criarDestiancaoImovel(Long idImovel, String rip) {
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        Imovel imovel = new Imovel();
        imovel.setId(idImovel);
        imovel.setIdCadastroImovel(idImovel);
        imovel.setRip(rip);
        destinacaoImovel.setImovel(imovel);
        return destinacaoImovel;
    }


}
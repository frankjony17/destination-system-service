package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.BenfeitoriaDestinada;
import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.ObjetoUtils;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 03/02/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class BenfeitoriaServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private BenfeitoriaService benfeitoriaService;

    @Test
    @SneakyThrows
    public void filtrarBenfeitoriasEdificacao(){
        List<Benfeitoria> benfeitoriaList = benfeitoriaService.filtrarBenfeitoriasEdificacao(null);
        assertTrue(benfeitoriaList.isEmpty());
    }

    @Test
    @SneakyThrows
    public void atualizarAreaDisponivelZeroBenfeitorias(){
        List<Benfeitoria> benfeitorias = criarListaBenfeitorias();
        benfeitoriaService.atualizarAreaDisponivelZero(benfeitorias);
        assertTrue(benfeitorias.get(0).getAreaDisponivel() == BigDecimal.valueOf(Constants.ZERO));
    }

    @Test
    @SneakyThrows
    public void atualizarAreaDisponivelZeroBenfeitoriasVazio(){
        List<Benfeitoria> benfeitorias = new ArrayList<>();
        benfeitoriaService.atualizarAreaDisponivelZero(benfeitorias);
        assertTrue(benfeitorias.isEmpty());
    }

    @Test
    @SneakyThrows
    public void atualizarAreaDisponivelZeroBenfeitoriasNull(){
        List<Benfeitoria> benfeitorias = null;
        benfeitoriaService.atualizarAreaDisponivelZero(benfeitorias);
        assertNull(benfeitorias);
    }

    @Test
    @SneakyThrows
    public void atualizarAreaDisponivel(){
        benfeitoriaService.atualizarAreaDisponivel(criarListaBenfeitoriaDestinada());
    }

    @Test
    @SneakyThrows
    public void buscarBenfeitoriasPorImovelIdCadastro(){
        List<Benfeitoria> benfeitorias = benfeitoriaService.buscarBenfeitoriasPorImovelIdCadastro(10L);
        assertNotNull(benfeitorias);
        assertTrue(!benfeitorias.isEmpty());
        assertTrue(benfeitorias.size() == Constants.CINCO);
    }

    @Test
    @SneakyThrows
    public void extrairBenfeitoriasParcelasSalvas(){
        List<Benfeitoria> benfeitorias = benfeitoriaService.extrairBenfeitoriasParcelasSalvas(criarListaParcela());
        assertNotNull(benfeitorias);
        assertTrue(!benfeitorias.isEmpty());
    }

    @Test
    @SneakyThrows
    public void extrairBenfeitoriasParcelasSalvasBenfeitoriasNull(){
        List<Parcela> parcelas = criarListaParcela();
        parcelas.get(Constants.ZERO).setBenfeitorias(null);
        List<Benfeitoria> benfeitorias = benfeitoriaService.extrairBenfeitoriasParcelasSalvas(parcelas);
        assertNotNull(benfeitorias);
        assertTrue(benfeitorias.isEmpty());
    }

    @Test
    @SneakyThrows
    public void extrairBenfeitoriasParcelasSalvasBenfeitoriasVazio(){
        List<Parcela> parcelas = criarListaParcela();
        parcelas.get(Constants.ZERO).setBenfeitorias(Collections.emptyList());
        List<Benfeitoria> benfeitorias = benfeitoriaService.extrairBenfeitoriasParcelasSalvas(parcelas);
        assertNotNull(benfeitorias);
        assertTrue(benfeitorias.isEmpty());
    }

    @Test
    @SneakyThrows
    public void tratarAtualizacao(){
        Set<String> camposSeremValidados = ObjetoUtils.criarCamposValidar("areaConstruida");
        Benfeitoria benfeitoriaSalva = new Benfeitoria();
        Benfeitoria benfeitoriaNova = new Benfeitoria();
        List<Benfeitoria> benfeitoriasAtualizadas = new ArrayList<>();
        benfeitoriaSalva.setAreaConstruida(BigDecimal.valueOf(200));
        benfeitoriaNova.setAreaConstruida(BigDecimal.valueOf(200));

        Method metodo = BenfeitoriaService.class.getDeclaredMethod("tratarAtualizacaoBenfeitoriasJaExistentes",
                Set.class, List.class, Benfeitoria.class, Benfeitoria.class, Map.class);
        metodo.setAccessible(true);
        metodo.invoke(benfeitoriaService, camposSeremValidados, benfeitoriasAtualizadas, benfeitoriaSalva, benfeitoriaNova,
                null);
        assertTrue(benfeitoriasAtualizadas.get(0).getId()== benfeitoriaSalva.getId());
    }

    @Test
    @SneakyThrows
    public void deletarBenfeitoriaParcelaAtiva(){
        Benfeitoria benfeitoria = new Benfeitoria();
        benfeitoria.setAtiva(false);

        Method metodo = BenfeitoriaService.class.getDeclaredMethod("deletarBenfeitoriaParcelaAtiva",
                Benfeitoria.class);
        metodo.setAccessible(true);
        metodo.invoke(benfeitoriaService, benfeitoria);

        assertEquals(benfeitoria.getAtiva(), false);
    }


    private List<Benfeitoria> criarListaBenfeitorias(){
        List<Benfeitoria> benfeitorias = new ArrayList<>();
        benfeitorias.add(criarBenfeitoria());
        return benfeitorias;
    }

    private Benfeitoria criarBenfeitoria(){
        Benfeitoria benfeitoria = new Benfeitoria();
        benfeitoria.setIdBenfeitoriaCadImovel(1L);
        benfeitoria.setId(1L);
        benfeitoria.setCodigo("E1");
        return benfeitoria;
    }

    private List<BenfeitoriaDestinada> criarListaBenfeitoriaDestinada(){
        List<BenfeitoriaDestinada> benfeitoriaDestinadas = new ArrayList<>();
        benfeitoriaDestinadas.add(criarBenfeitoriaDestinada());
        return benfeitoriaDestinadas;
    }

    private BenfeitoriaDestinada criarBenfeitoriaDestinada(){
        BenfeitoriaDestinada benfeitoriaDestinada = new BenfeitoriaDestinada();
        benfeitoriaDestinada.setId(1L);
        benfeitoriaDestinada.setIdBenfeitoria(5L);
        benfeitoriaDestinada.setAreaUtilizar(BigDecimal.valueOf(100));
        return benfeitoriaDestinada;
    }

    private List<Parcela> criarListaParcela(){
        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(criarParcela());
        return parcelas;
    }

    private Parcela criarParcela(){
        Parcela parcela = new Parcela();
        parcela.setBenfeitorias(criarListaBenfeitorias());
        return parcela;
    }
}
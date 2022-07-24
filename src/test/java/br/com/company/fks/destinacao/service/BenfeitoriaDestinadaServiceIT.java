package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.BenfeitoriaDestinada;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Created by diego on 23/01/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class BenfeitoriaDestinadaServiceIT extends BaseIntegrationTestCofig {

    @Autowired
    private BenfeitoriaDestinadaService benfeitoriaDestinadaService;


    @Test
    @SneakyThrows
    public void salvar(){
        BenfeitoriaDestinada benfeitoriaDestinada = benfeitoriaDestinadaService.salvar(criarBenfeitoriaDestinada());
        assertNotNull(benfeitoriaDestinada);
        assertNotNull(benfeitoriaDestinada.getId());
    }

    @Test
    @SneakyThrows
    public void salvarListaBenfeitoriaDestinada(){
        List<BenfeitoriaDestinada> benfeitoriaDestinadaList = benfeitoriaDestinadaService.salvar(criarListaBenfeitoriasDestinadas(), criarDestinacaoImovel(), Boolean.FALSE);
        assertNotNull(benfeitoriaDestinadaList);
        assertNotNull(benfeitoriaDestinadaList.get(0).getId());
    }

    @Test
    @SneakyThrows
    public void salvarListaBenfeitoriaDestinadaNull(){
        List<BenfeitoriaDestinada> benfeitoriaDestinadaList = benfeitoriaDestinadaService.salvar(null, criarDestinacaoImovel(), Boolean.FALSE);
        assertNotNull(benfeitoriaDestinadaList);
        assertNotNull(benfeitoriaDestinadaList.isEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarBenfeitoriasRipImovel(){
        List<BenfeitoriaDestinada> benfeitoriaDestinadas = benfeitoriaDestinadaService.buscarBenfeitoriasRipImovel("00000009");
        assertNotNull(benfeitoriaDestinadas);
        assertNotNull(benfeitoriaDestinadas.get(0).getId());
    }

    @Test
    @SneakyThrows
    public void buscarMapaBenfeitoriasIdImovel(){
        Map<Long, BigDecimal> map = benfeitoriaDestinadaService.buscarMapaBenfeitoriasIdImovel("00000009");
        assertNotNull(map);
    }

    private BenfeitoriaDestinada criarBenfeitoriaDestinada(){
        BenfeitoriaDestinada benfeitoriaDestinada =  new BenfeitoriaDestinada();
        benfeitoriaDestinada.setId(1L);
        benfeitoriaDestinada.setIdBenfeitoria(5L);
        benfeitoriaDestinada.setAreaUtilizar(BigDecimal.valueOf(100));
        benfeitoriaDestinada.setDestinacaoImovel(criarDestinacaoImovel());
        return benfeitoriaDestinada;
    }

    private DestinacaoImovel criarDestinacaoImovel(){
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setId(3L);
        return destinacaoImovel;
    }

    private List<BenfeitoriaDestinada> criarListaBenfeitoriasDestinadas(){
        List<BenfeitoriaDestinada> benfeitoriaDestinadas = new ArrayList<>();
        benfeitoriaDestinadas.add(criarBenfeitoriaDestinada());
        return benfeitoriaDestinadas;
    }

}
package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.BenfeitoriaDestinada;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by diego on 24/11/16.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DestinacaoImovelServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private DestinacaoImovelService destinacaoImovelService;

    @Test
    @SneakyThrows
    public void salvar(){
        Imovel imovel = new Imovel();
        imovel.setId(3L);
        BenfeitoriaDestinada benfeitoriaDestinada = new BenfeitoriaDestinada();
        benfeitoriaDestinada.setId(1L);
        List<BenfeitoriaDestinada> benfeitoriaDestinadaList = new ArrayList<>();
        benfeitoriaDestinadaList.add(benfeitoriaDestinada);
        DestinacaoImovel destinacaoImovel = new DestinacaoImovel();
        destinacaoImovel.setImovel(imovel);
        destinacaoImovel.setBenfeitoriasDestinadas(benfeitoriaDestinadaList);
        destinacaoImovel.setId(3L);

        DestinacaoImovel retorno = destinacaoImovelService.salvar(destinacaoImovel, Boolean.FALSE);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void findById(){
        DestinacaoImovel destinacaoImovel = destinacaoImovelService.findById(3L);
        assertNotNull(destinacaoImovel);
    }
}

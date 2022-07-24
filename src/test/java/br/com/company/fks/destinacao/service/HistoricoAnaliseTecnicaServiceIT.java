package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoChefia;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSecretario;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSuperintendente;
import br.com.company.fks.destinacao.dominio.entidades.HistoricoAnaliseTecnica;
import br.com.company.fks.destinacao.dominio.entidades.StatusAnaliseTecnica;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

/**
 * Created by diego on 30/12/16.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class HistoricoAnaliseTecnicaServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private HistoricoAnaliseTecnicaService historicoAnaliseTecnicaService;

    @Test
    @SneakyThrows
    public void salvarChefia(){
        usuarioLogadoDTO.setPermissoes(new HashSet<>());
        usuarioLogadoDTO.getPermissoes().add("DESTINACAO_EXEC_ANALISE_TEC_CHEFIA");
        AnaliseTecnica analiseTecnica = criaAnaliseTecnica();
        AnaliseTecnicaDespachoChefia analiseTecnicaDespachoChefia = new AnaliseTecnicaDespachoChefia();
        analiseTecnicaDespachoChefia.setJustificativa(null);
        analiseTecnica.setDespachosAnaliseChefia(Arrays.asList(analiseTecnicaDespachoChefia));
        HistoricoAnaliseTecnica retorno = historicoAnaliseTecnicaService.salvar(criaStatusAnaliseTecnica(),
                                                                                criaStatusAnaliseTecnica(),
                                                                                analiseTecnica,
                                                                                usuarioLogadoDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void salvarSuperintendente(){
        usuarioLogadoDTO.setPermissoes(new HashSet<>());
        usuarioLogadoDTO.getPermissoes().add("DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE");
        AnaliseTecnica analiseTecnica = criaAnaliseTecnica();
        AnaliseTecnicaDespachoSuperintendente analiseTecnicaDespachoSuperintendente = new AnaliseTecnicaDespachoSuperintendente();
        analiseTecnicaDespachoSuperintendente.setJustificativa("");
        analiseTecnica.setDespachosAnaliseSuperintendente(Arrays.asList(analiseTecnicaDespachoSuperintendente));
        HistoricoAnaliseTecnica retorno = historicoAnaliseTecnicaService.salvar(criaStatusAnaliseTecnica(),
                                                                                criaStatusAnaliseTecnica(),
                                                                                analiseTecnica,
                usuarioLogadoDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void salvarSecretario(){
        usuarioLogadoDTO.setPermissoes(new HashSet<>());
        usuarioLogadoDTO.getPermissoes().add("DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO");
        AnaliseTecnica analiseTecnica = criaAnaliseTecnica();
        AnaliseTecnicaDespachoSecretario analiseTecnicaDespachoSecretario = new AnaliseTecnicaDespachoSecretario();
        analiseTecnicaDespachoSecretario.setJustificativa("");
        analiseTecnica.setDespachosAnaliseSecretario(Arrays.asList(analiseTecnicaDespachoSecretario));
        HistoricoAnaliseTecnica retorno = historicoAnaliseTecnicaService.salvar(criaStatusAnaliseTecnica(),
                                                                                criaStatusAnaliseTecnica(),
                                                                                analiseTecnica,
                                                                                usuarioLogadoDTO);
        assertNotNull(retorno);
    }

    @Test
    @SneakyThrows
    public void salvarNenhum(){
        usuarioLogadoDTO.setPermissoes(new HashSet<>());
        usuarioLogadoDTO.getPermissoes().add("TESTE");
        AnaliseTecnica analiseTecnica = criaAnaliseTecnica();
        AnaliseTecnicaDespachoSecretario analiseTecnicaDespachoSecretario = new AnaliseTecnicaDespachoSecretario();
        analiseTecnicaDespachoSecretario.setJustificativa("");
        analiseTecnica.setDespachosAnaliseSecretario(Arrays.asList(analiseTecnicaDespachoSecretario));
        HistoricoAnaliseTecnica retorno = historicoAnaliseTecnicaService.salvar(criaStatusAnaliseTecnica(),
                criaStatusAnaliseTecnica(),
                analiseTecnica,
                usuarioLogadoDTO);
        assertNotNull(retorno);
    }

    private StatusAnaliseTecnica criaStatusAnaliseTecnica(){
        StatusAnaliseTecnica statusAnaliseTecnica = new StatusAnaliseTecnica();
        statusAnaliseTecnica.setId(1);
        statusAnaliseTecnica.setDescricao("teste");
        return statusAnaliseTecnica;
    }

    private AnaliseTecnica criaAnaliseTecnica(){
        AnaliseTecnica analiseTecnica = new AnaliseTecnica();
        analiseTecnica.setId(125L);
        return analiseTecnica;
    }
}

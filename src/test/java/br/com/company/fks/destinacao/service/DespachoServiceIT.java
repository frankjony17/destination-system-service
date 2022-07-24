package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.dto.DespachoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import br.com.company.fks.destinacao.utils.Constants;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 29/12/16.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DespachoServiceIT extends BaseIntegrationTestCofig{
    @Autowired
    private DespachoService despachoService;

    @Test
    @SneakyThrows
    public void buscarPorTipoDespacho() {
        usuarioLogadoDTO.setPerfil("DESTINACAO.CHEFIA");
        List<Despacho> despachos = despachoService.buscarPorTipoDespacho(TipoDespachoEnum.CHEFIA);
        assertNotNull(despachos);
        assertTrue(despachos.size() == Constants.TRES);
    }

    @Test
    @SneakyThrows
    public void buscarPorTipoDespachoTecnico() {
        usuarioLogadoDTO.setPerfil("DESTINACAO.CHEFIA");
        List<Despacho> despachos = despachoService.buscarPorTipoDespacho(TipoDespachoEnum.SUPERINTENDENTE);
        assertNotNull(despachos);
        assertTrue(despachos.size() == Constants.TRES);
    }

    @Test
    @SneakyThrows
    public void filtrarDespachos() {
        DespachoDTO despachoDTO = new DespachoDTO();
        despachoDTO.setTipoDespacho(TipoDespachoEnum.SECRETARIO);
        List<DespachoDTO> despachos = new ArrayList<>();
        despachos.add(despachoDTO);
        List<DespachoDTO> despachosFiltrados = despachoService.filtrarDespachos(TipoDespachoEnum.SECRETARIO, despachos);
        assertNotNull(despachosFiltrados);
    }

    @Test
    @SneakyThrows
    public void filtrarDespachosSemResultado() {
        DespachoDTO despachoDTO = new DespachoDTO();
        despachoDTO.setTipoDespacho(TipoDespachoEnum.SECRETARIO);
        List<DespachoDTO> despachos = new ArrayList<>();
        despachos.add(despachoDTO);
        List<DespachoDTO> despachosFiltrados = despachoService.filtrarDespachos(TipoDespachoEnum.CHEFIA, despachos);
        assertTrue(despachosFiltrados.isEmpty());
    }
}

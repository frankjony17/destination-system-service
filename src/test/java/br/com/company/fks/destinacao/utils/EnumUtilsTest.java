package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.dto.OrdenacaoEnumDTO;
import br.com.company.fks.destinacao.dominio.enums.*;
import br.com.company.fks.destinacao.exceptions.EnumException;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by diego on 03/01/17.
 */
@RunWith(PowerMockRunner.class)
public class EnumUtilsTest {

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<EnumUtils> constructor = EnumUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void buscarEnumChaveValor() {
        Map<String, String> mapaEnum = EnumUtils.buscarEnumChaveValor(StatusAnaliseTecnicaEnum.class);
        assertEquals("Aguardando Análise Técnica", mapaEnum.get("AGUARDANDO_ANALISE_TECNICA"));
    }

    @Test
    public void buscarEnumCodigoDescricao() {
        List<Map<String, String>> resultado = EnumUtils.buscarEnumCodigoDescricao(TipoDestinacaoEnum.class);
        assertNotNull(resultado);
    }

    @Test(expected = EnumException.class)
    public void buscarEnumCodigoCodigoInexistenteNoEnum() {
        List<Map<String, String>> resultado = EnumUtils.buscarEnumCodigoDescricao(UFEnum.class);
        assertEquals("Doação", resultado.get(1));
    }

    @Test
    public void ordenarEnum(){
        List<OrdenacaoEnumDTO> ordenacaoEnumDTOList = EnumUtils.ordenarEnum(StatusAnaliseTecnicaEnum.class);
        assertNotNull(ordenacaoEnumDTOList);
    }

    @Test
    public void ordenarEnumMotivoPosseIformal(){
        List<OrdenacaoEnumDTO> ordenacaoEnumDTOList = EnumUtils.ordenarEnumMotivoPosseIformal(MotivoCancelarEncerrarEnum.class);
        assertNotNull(ordenacaoEnumDTOList);
    }

    @Test
    public void ordenarEnumMotivoPosseInformalparaEncerrarDestinacao(){
        List<OrdenacaoEnumDTO> ordenacaoEnumDTOList = EnumUtils.ordenarEnumMotivoPosseInformalparaEncerrarDestinacao(MotivoEncerrarDestinacaoEnum.class);
        assertNotNull(ordenacaoEnumDTOList);
    }
}

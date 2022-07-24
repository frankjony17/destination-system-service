package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

public class DadosdoUsoEnumTest {

    @Test
    public void getDescricao(){
        String descricao = DadosdoUsoEnum.SEMUSOVAGO.getDescricao();
        assertEquals(descricao, DadosdoUsoEnum.SEMUSOVAGO.getDescricao());
    }

    @Test
    public void getCodigo(){
        Integer codigo = DadosdoUsoEnum.SEMINFORMACAO.getCodigo();
        assertEquals(codigo, DadosdoUsoEnum.SEMINFORMACAO.getCodigo());
    }

    @Test
    public void getByDescricao(){
        DadosdoUsoEnum dadosdoUsoEnum = DadosdoUsoEnum.getByDescricao("Sem Uso/Vago");
        assertNotNull(dadosdoUsoEnum);
    }

    @Test
    public void getByDescricaoSemResultado(){
        DadosdoUsoEnum dadosdoUsoEnum = DadosdoUsoEnum.getByDescricao("descricao");
        assertNull(dadosdoUsoEnum);
    }
}

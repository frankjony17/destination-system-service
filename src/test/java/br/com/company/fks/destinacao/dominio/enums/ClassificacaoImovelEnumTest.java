package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ClassificacaoImovelEnumTest {

    @Test
    public void getCodigo(){
        Integer codigo = ClassificacaoImovelEnum.SEMINFORMACAO.getCodigo();
        assertEquals(codigo, ClassificacaoImovelEnum.SEMINFORMACAO.getCodigo());
    }

    @Test
    public void getDescricao(){
        String descricao = ClassificacaoImovelEnum.DOMINIAL.getDescricao();
        assertEquals(descricao, ClassificacaoImovelEnum.DOMINIAL.getDescricao());
    }

    @Test
    public void getByCodigo(){
        ClassificacaoImovelEnum classificacaoImovelEnum = ClassificacaoImovelEnum.getByCodigo(1L);
        assertEquals(ClassificacaoImovelEnum.SEMINFORMACAO, classificacaoImovelEnum);
    }

    @Test
    public void getByCodigoSemResultado(){
        ClassificacaoImovelEnum classificacaoImovelEnum = ClassificacaoImovelEnum.getByCodigo(8L);
        assertNull(classificacaoImovelEnum);
    }
}

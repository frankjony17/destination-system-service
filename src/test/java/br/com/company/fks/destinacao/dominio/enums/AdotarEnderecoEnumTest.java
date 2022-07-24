package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AdotarEnderecoEnumTest {

    @Test
    public void getDescricao(){
        String descricao = AdotarEnderecoEnum.IMOVEL.getDescricao();
        assertEquals(descricao, AdotarEnderecoEnum.IMOVEL.getDescricao());
    }

    @Test
    public void getCodigo(){
        Integer codigo = AdotarEnderecoEnum.CADUNICO.getCodigo();
        assertEquals(codigo, AdotarEnderecoEnum.CADUNICO.getCodigo());
    }
}

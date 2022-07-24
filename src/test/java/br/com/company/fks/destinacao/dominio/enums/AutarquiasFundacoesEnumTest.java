package br.com.company.fks.destinacao.dominio.enums;

import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.Assert.assertEquals;

public class AutarquiasFundacoesEnumTest {

    @Test
    public void getCodigo(){
        Integer codigo = AutarquiasFundacoesEnum.AGENCIA_ESPACIAL_BRASILEIRA.getCodigo();
        assertEquals(codigo, AutarquiasFundacoesEnum.AGENCIA_ESPACIAL_BRASILEIRA.getCodigo());
    }

    @Test
    public void setCodigo(){
        AutarquiasFundacoesEnum.AGENCIA_ESPACIAL_BRASILEIRA.setCodigo(1);
        Integer codigo = AutarquiasFundacoesEnum.AGENCIA_ESPACIAL_BRASILEIRA.getCodigo();
        assertEquals(codigo, AutarquiasFundacoesEnum.AGENCIA_ESPACIAL_BRASILEIRA.getCodigo());
    }

    @Test
    public void getCodigoUg(){
        String codigoUg = AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AVIACAO_CIVIL.getCodigoUg();
        assertEquals(codigoUg, AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AVIACAO_CIVIL.getCodigoUg());
    }

    @Test
    public void setCodigoUg(){
        AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AVIACAO_CIVIL.setCodigoUg("codigoUg");
        String codigoUg = AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AVIACAO_CIVIL.getCodigoUg();
        assertEquals(codigoUg, AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AVIACAO_CIVIL.getCodigoUg());
    }

    @Test
    public void getDescricao(){
        String descricao = AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AGUAS.getDescricao();
        assertEquals(descricao, AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AGUAS.getDescricao());
    }

    @Test
    public void setDescricao(){
        AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AGUAS.setDescricao("descricao");
        String descricao = AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AGUAS.getDescricao();
        assertEquals(descricao, AutarquiasFundacoesEnum.AGENCIA_NACIONAL_DE_AGUAS.getDescricao());
    }
}

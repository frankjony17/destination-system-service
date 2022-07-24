package br.com.company.fks.destinacao.dominio.entidades.auditoria;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
public class RevisionTest {
    private Revision revision;

    @Before
    public void setUp(){
        revision = new Revision();
    }

    @Test
    public void getDataOperacaoNull(){
        revision.setDataOperacao(null);
        assertEquals(null, revision.getDataOperacao());
    }
}

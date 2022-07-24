package br.com.company.fks.destinacao.dominio.entidades.auditoria;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by rogerio on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class RevisionIT {
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

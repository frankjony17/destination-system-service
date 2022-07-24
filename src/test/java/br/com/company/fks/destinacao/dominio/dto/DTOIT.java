package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.GetterAndSetterTester;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by diego on 12/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DTOIT {

    Set<Class<? extends Serializable>> allClasses;
    GetterAndSetterTester tester;

    @Before
    public void setUp(){
        tester = new GetterAndSetterTester();
        Reflections reflections = new Reflections("br.com.company.fks.destinacao.dominio.dto");
        allClasses = reflections.getSubTypesOf(Serializable.class);
    }

    @Test
    public void testarTodosDTO(){
        for (Class<? extends Object> clazz : allClasses) {
            tester.testClass(clazz);
        }
    }

}

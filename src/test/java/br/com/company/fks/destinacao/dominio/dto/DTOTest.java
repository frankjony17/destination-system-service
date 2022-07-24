package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.GetterAndSetterTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.Serializable;
import java.util.Set;

/**
 *
 *
 * Created by Basis on 23/11/16.
 */
public class DTOTest {

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

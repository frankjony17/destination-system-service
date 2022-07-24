package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.dominio.GetterAndSetterTester;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by diego on 04/01/17.
 */
public class EntidadeTest {

    Set<Class<? extends Serializable>> allClasses;
    GetterAndSetterTester tester;

    @Before
    public void setUp() {
        tester = new GetterAndSetterTester();
        Reflections reflections = new Reflections("br.com.company.fks.destinacao.dominio.entidades");
        allClasses = reflections.getSubTypesOf(Serializable.class);
    }

    @Test
    public void testarTodasEntidades() {
        for (Class<? extends Object> clazz : allClasses)
            tester.testClass(clazz);
    }
}

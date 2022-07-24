package br.com.company.fks.destinacao.configuracao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */
@Component
public final class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    //Essa classe não será instanciada
    private SpringContext(){}


    /**
     *
     * @param appContext Seta o context da aplicação
     */
    public static void setContext(ApplicationContext appContext) {
        SpringContext.applicationContext = appContext;
    }

    /**
     *
     * @param appContext Seta o context da aplicação
     */
    @Override
    public void setApplicationContext(ApplicationContext appContext) {
        setContext(appContext);
    }

    /**
     * Método que retorna um bean injetado pelo Spring
     * @param clazz
     * @return
     */
    public static <T>T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }


}

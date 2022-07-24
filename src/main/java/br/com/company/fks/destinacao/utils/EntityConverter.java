package br.com.company.fks.destinacao.utils;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;

/**
 * Created by Basis Tecnologia on 07/06/2016.
 */
@Component
public class EntityConverter {

    private ModelMapper map;

    @PostConstruct
    private void init() {
        map = new ModelMapper();
    }

    /**
     *
     * @param source
     * @param target
     * @param <D>
     * @return
     */
    public <D> D converter(Object source, Class<D> target){
        D retorno = map.map(source, target);

        return retorno;
    }

    /**
     *
     * @param source
     * @param target
     * @param <D>
     * @return
     */
    public <D> D converterStrict(Object source, Class<D> target){

        if (source == null){
            return null;
        }

        map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        D retorno = map.map(source, target);

        return retorno;
    }

    /**
     *
     * @param source
     * @param target
     * @param <D>
     * @return
     */
    public <D> D converterStrict(Object source, Type target){
        map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return  map.map(source, target);

    }

    /**
     *
     * @param source
     * @param target
     * @param <D>
     * @return
     */
    public <D> D converterLazyLoading(Object source, Class<D> target){
        return map.map(source, target);
    }

    /**
     *
     * @param source
     * @param target
     * @param <D>
     * @return
     */
    public <D> D converterStrictLazyLoading(Object source, Class<D> target){
        map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        D retorno = map.map(source, target);

        return retorno;
    }

    /**
     *
     * @param source
     * @param target
     * @param <D>
     * @return
     */
    public <D> D converterListaStrictLazyLoading(Object source, Type target) {
        map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return map.map(source, target);
    }
}

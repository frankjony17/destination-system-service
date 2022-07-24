package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.exceptions.NegocioException;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by diego on 09/03/17.
 */
public final class ObjetoUtils {

    private static final Logger LOGGER = Logger.getLogger(ObjetoUtils.class);

    private ObjetoUtils() {
        throw new IllegalAccessError("Classe utilitaria");
    }

    public static Boolean compararObjetosDiferentes(Object oldObject, Object newObject, Set<String> fields) throws NegocioException {
        Boolean atributosAlterados = Boolean.FALSE;
        for (String key : fields) {
            try {
                Field fieldOldObjeto = getField(oldObject, key);
                fieldOldObjeto.setAccessible(true);
                Field fieldNewObjeto = getField(newObject, key);
                fieldNewObjeto.setAccessible(true);

                atributosAlterados = !fieldOldObjeto.get(oldObject).equals(fieldNewObjeto.get(newObject));

                fieldOldObjeto.setAccessible(false);
                fieldNewObjeto.setAccessible(false);

                if (atributosAlterados) {
                    break;
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.error(e);
                throw new NegocioException(e.getMessage(), e);
            }
        }

        return atributosAlterados;

    }

    private static Field getField(Object object, String field) throws NoSuchFieldException {
        return object.getClass().getDeclaredField(field);
    }

    /**
     * Cria Set com o nomes dos campos para validação
     * @param campos
     * @return Set<String>
     */
    public static Set<String> criarCamposValidar(String... campos) {
        Set<String> camposValidar = new HashSet<>();
        for (String campo : campos) {
            camposValidar.add(campo);
        }
        return camposValidar;
    }

}

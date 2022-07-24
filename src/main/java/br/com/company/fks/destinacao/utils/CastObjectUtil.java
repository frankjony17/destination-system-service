package br.com.company.fks.destinacao.utils;

/**
 * Created by diego on 14/06/17.
 */
public final class CastObjectUtil {

    private CastObjectUtil() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Efetua o cast dos objeto informado
     * @param o
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T cast(Object o, Class<T> clazz) {
        try {
            if (o != null) {
                return clazz.cast(o);
            }
            return null;
        } catch(ClassCastException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

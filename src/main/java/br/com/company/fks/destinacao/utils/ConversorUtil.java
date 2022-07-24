package br.com.company.fks.destinacao.utils;

import java.util.Base64;

/**
 * Classe reponsável por conversão
 * Created by Basis Tecnologia on 10/10/2016.
 */
public final class ConversorUtil {

    private ConversorUtil() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Conversor
     * @param base64
     * @return
     */
    public static byte [] decodeBase64ByteArray(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    /**
     * Converte uma imagem para Base64
     * @param dados
     * @return
     */
    public static String encodeBase64(byte[] dados) {
        return Base64.getEncoder().encodeToString(dados);
    }

}

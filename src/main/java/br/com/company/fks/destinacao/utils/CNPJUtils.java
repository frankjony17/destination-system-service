package br.com.company.fks.destinacao.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe responsável por validar CNPJ
 * Created by Basis Tecnologia on 24/06/2016.
 */
public final class CNPJUtils {

    private static final Set<String> blackList = new HashSet<>();

    private static final int QUARENTA_OITO = 48;

    private CNPJUtils() {
        throw new IllegalAccessError("Utility class");
    }

    static {
        blackList.add("00000000000000");
        blackList.add("11111111111111");
        blackList.add("22222222222222");
        blackList.add("33333333333333");
        blackList.add("44444444444444");
        blackList.add("55555555555555");
        blackList.add("66666666666666");
        blackList.add("77777777777777");
        blackList.add("88888888888888");
        blackList.add("99999999999999");
    }

    /**
     * Valida CNPJ e verifica se está presente na CnpjBlackList
     * @param cnpj
     * @return true ou false
     */
    public static boolean isCNPJ(String cnpj) {

        if (isCnpjBlackList(cnpj)) {
            return false;
        }

        char dig13 = calcularDigito(cnpj, Constants.ONZE);
        char dig14 = calcularDigito(cnpj, Constants.DOZE);
        return (dig13 == cnpj.charAt(Constants.DOZE)) && (dig14 == cnpj.charAt(Constants.TREZE));

    }

    private static boolean isCnpjBlackList(String cnpj) {
        return  blackList.contains(cnpj) || (cnpj.length() != Constants.QUATORZE);
    }

    private static char calcularDigito(String cnpj, int quantidade) {
        int soma = Constants.ZERO;
        int peso = Constants.DOIS;
        int num;

        for (int i = quantidade; i >= Constants.ZERO; i--) {
            num = cnpj.charAt(i) - QUARENTA_OITO;
            soma = soma + (num * peso);
            peso = peso + Constants.UM;
            if (peso == Constants.DEZ) {
                peso = Constants.DOIS;
            }
        }

        return calcularDigito(soma);
    }

    private static char calcularDigito(int soma) {

        int r =  soma % Constants.ONZE;

        if ((r == Constants.ZERO) || (r == Constants.UM)) {
            return '0';
        } else {
            return (char)((Constants.ONZE - r) + QUARENTA_OITO);
        }

    }

}

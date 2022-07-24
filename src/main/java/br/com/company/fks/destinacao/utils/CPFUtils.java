package br.com.company.fks.destinacao.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe responsavel por validar CPF
 * Created by Basis Tecnologia on 17/10/2016.
 */
public final class CPFUtils {

    private static final Set<String> blackList = new HashSet<>();

    private static final int PESO_DEZ = 10;
    private static final int PESO_ONZE = 11;



    private CPFUtils() {
        throw new IllegalAccessError("Utility class");
    }

    static {
        blackList.add("11111111111");
        blackList.add("22222222222");
        blackList.add("33333333333");
        blackList.add("44444444444");
        blackList.add("55555555555");
        blackList.add("66666666666");
        blackList.add("77777777777");
        blackList.add("88888888888");
        blackList.add("99999999999");
    }

    /**
     * Valida o CPF e verifica se o mesmo está presente na blacklist
     * @param cpf
     * @return
     */
    public static boolean validar(String cpf) {
        if (isCpfOnzeDigitos(cpf) || isCPFPadrao(cpf)) {
            return false;
        }
        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { 
            return false;
        }
        String digitoVerificador = calculaDigitoVerificador(cpf.substring(Constants.ZERO, Constants.NOVE));
        return digitoVerificador.equals(cpf.substring(Constants.NOVE, Constants.ONZE));
    }

    private static boolean isCpfOnzeDigitos(String cpf) {
        return cpf == null || cpf.length() != Constants.ONZE;
    }

    private static boolean isCPFPadrao(String cpf) {
        if (isCpfBlackList(cpf)) {
            return true;
        }
        return false;
    }

    private static boolean isCpfBlackList(String cpf) {
        return blackList.contains(cpf);
    }

    private static String calculaDigitoVerificador(String num) {
        Integer primDig;
        Integer segDig;
        int soma = Constants.ZERO;
        int peso = PESO_DEZ;

        soma = somaDigitos(num, soma, peso);
        primDig = calculaPrimeiroDigito(soma);

        soma = Constants.ZERO;
        peso = PESO_ONZE;
        soma = somaDigitos(num, soma, peso);
        soma += primDig.intValue() * Constants.DOIS;
        segDig = calculaPrimeiroDigito(soma);
        return primDig.toString() + segDig.toString();
    }

    private static int somaDigitos(String num, int soma, int peso) {
        int total = soma;
        int pesoIdentificador = peso;
        for (int i = Constants.ZERO; i < num.length(); i++) {
            total += Integer.parseInt(num.substring(i, i + Constants.UM)) * pesoIdentificador--;
        }
        return total;
    }

    private static Integer calculaPrimeiroDigito(int soma) {
        Integer primDig;
        if (soma % Constants.ONZE == Constants.ZERO || soma % Constants.ONZE == Constants.UM) {
            primDig = Constants.ZERO;
        } else {
            primDig = Constants.ONZE - (soma % Constants.ONZE);
        }
        return primDig;
    }

}

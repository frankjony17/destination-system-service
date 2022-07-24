package br.com.company.fks.destinacao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

/**
 * Classe responsável por tratar datas
 * Created by diego on 09/12/16.
 */
public final class DataUtil {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private static final String HH_MM = "HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DD_MM_YY_HH_MM =  "dd/MM/yyyy HH:mm";

    private DataUtil() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Formata data e hora
     * @param data
     * @param pattern
     * @return String com a data e hora formatados
     */
    public static String formataDataHora(Date data, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if(data != null) {
            return simpleDateFormat.format(data);
        }else {
            return null;
        }
    }

    /**
     * Retorna Data atual formatada
     * @return String com data atual formatada
     */
    public static String getDataAtualFormatada() {
        return formataDataHora(new Date(), DD_MM_YYYY);
    }

    /**
     * Retorna Hora atual formatada
     * @return String com hora atual formatada
     */
    public static String getDataHoraFormatada() {
        return formataDataHora(new Date(), DD_MM_YY_HH_MM);
    }

    /**
     * Retorna Hora atual formatada
     * @return String com hora atual formatada
     */
    public static String getHoraAtualFormatada() {
        return formataDataHora(new Date(), HH_MM);
    }

    /**
     * Retorna Transforma String em Date
     * @return Date com a Hora formatada
     */
    public static Date stringToDate(String data, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(data);
    }

    /**
     * Converte objeto Date para LocalDate
     * @param data
     * @return
     */
    public static LocalDate converterLocalDate(Date data) {
        Optional<Date> optional = Optional.ofNullable(data);
        if (optional.isPresent()) {
            return LocalDate.parse(new SimpleDateFormat(YYYY_MM_DD).format(data));
        }
        return null;
    }


}

package br.com.company.fks.destinacao.utils;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by diego on 03/01/17.
 */
@RunWith(PowerMockRunner.class)
public class DataUtilTest {

    private static final String DD_MM_YYYY = "dd/MM/yyyy";
    private static final String HH_MM = "HH:mm";
    private static final String DD_MM_YY_HH_MM =  "dd/MM/yyyy HH:mm";

    @Test(expected = IllegalAccessError.class)
    @SneakyThrows
    public void testaInstancia() {
        Constructor<DataUtil> constructor = DataUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void formataDataHoraFormatandoData() {
        Date data = new Date();
        String dataFormatada = dataFormatada(data, DD_MM_YYYY);
        assertEquals(dataFormatada, DataUtil.formataDataHora(data, DD_MM_YYYY));
    }

    @Test
    public void formataDataHoraFormatandoDataNull() {
        assertEquals(null, DataUtil.formataDataHora(null, DD_MM_YYYY));
    }

    @Test
    public void formataDataHoraFormatandoHora() {
        Date data = new Date();
        String horaFormatada = dataFormatada(data, HH_MM);
        assertEquals(horaFormatada, DataUtil.formataDataHora(data, HH_MM));
    }

    @Test
    public void formataDataAtual() {
        Date data = new Date();
        String horaFormatata = dataFormatada(data, DD_MM_YYYY);
        assertEquals(horaFormatata, DataUtil.getDataAtualFormatada());
    }

    @Test
    public void formataHoraAtual() {
        Date data = new Date();
        String horaFormatata = dataFormatada(data, HH_MM);
        assertEquals(horaFormatata, DataUtil.getHoraAtualFormatada());
    }

    @Test
    public void DataHoraFormatada(){
        Date data = new Date();
        String horaFormatadar = dataFormatada(data, DD_MM_YY_HH_MM);
        assertEquals(horaFormatadar, DataUtil.getDataHoraFormatada());
    }

    private String dataFormatada(Date data, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(data);
    }

    @Test
    @SneakyThrows
    public void converterLocalDate() {
        LocalDate dataAtual = LocalDate.now();
        Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dataAtual.toString());
        LocalDate dataEsperada = DataUtil.converterLocalDate(data);
        assertEquals(dataAtual, dataEsperada);
    }

    @Test
    @SneakyThrows
    public void converterLocalDateRetornadoNulo() {
        LocalDate dataEsperada = DataUtil.converterLocalDate(null);
        assertNull(dataEsperada);
    }

    @Test
    public void stringToDate() throws ParseException {
        assertNotNull(DataUtil.stringToDate("18/05/2018", "dd/MM/yyyy"));
    }

}

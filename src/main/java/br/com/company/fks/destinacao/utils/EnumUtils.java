package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.dto.OrdenacaoEnumDTO;
import br.com.company.fks.destinacao.dominio.enums.BasicEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoCancelarEncerrarEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoEncerrarDestinacaoEnum;
import br.com.company.fks.destinacao.exceptions.EnumException;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by basis on 14/12/16.
 */
public final class EnumUtils {

    private static final Logger LOGGER = Logger.getLogger(EnumUtils.class);
    private static final String CODIGO = "codigo";
    private static final String DESCRICAO = "descricao";
    private static final String PERMISSAOCONSULTAR = "permissaoConsultar";
    private static final String PERMISSAOCADASTRAREDITAR = "permissaoCadastrarEditar";
    private EnumUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     *
     * @param e
     * @param <E>
     * @return
     */
    public static <E extends Enum<E> & BasicEnum> Map<String, String> buscarEnumChaveValor(Class<E> e){
        Map<String, String> result = new HashMap<>();
        for(E value : e.getEnumConstants()){
            result.put(value.name(), value.getDescricao());
        }

        return result;
    }

    public static <E extends Enum<E> & BasicEnum> List<OrdenacaoEnumDTO> ordenarEnum(Class<E> e) {
        List<OrdenacaoEnumDTO> list = new ArrayList<>();
        for(E value :e.getEnumConstants()){
            list.add(new OrdenacaoEnumDTO(value.name(),
                    value.getCodigo(),
                    value.getDescricao()));
        }

        return list;
    }

    public static <E extends Enum<E> & BasicEnum> List<OrdenacaoEnumDTO> ordenarEnumMotivoPosseIformal(Class<E> e) {
        List<OrdenacaoEnumDTO> list = new ArrayList<>();
        for(E value :e.getEnumConstants()){
            list.add(new OrdenacaoEnumDTO(value.name(),
                    value.getCodigo(),
                    value.getDescricao(),
                    ((MotivoCancelarEncerrarEnum)value).getIsPosseInformal()));
        }

        return list;
    }

    public static <E extends Enum<E> & BasicEnum> List<OrdenacaoEnumDTO> ordenarEnumMotivoPosseInformalparaEncerrarDestinacao(Class<E> e) {
        List<OrdenacaoEnumDTO> list = new ArrayList<>();
        for(E value :e.getEnumConstants()){
            list.add(new OrdenacaoEnumDTO(value.name(),
                    value.getCodigo(),
                    value.getDescricao(),
                    ((MotivoEncerrarDestinacaoEnum)value).getIsPosseInformal()));
        }

        return list;
    }

    /**
     *
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> List<Map<String, String>> buscarEnumCodigoDescricao(Class clazz) {

        Object[] constants = clazz.getEnumConstants();
        List<Map<String, String>> result = null;

        try {
            result = lerCamposCadigoDescricao(constants);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error("ERRO LER ENUMS", e);
            throw new EnumException(e);
        }

        return result;
    }

    private static List<Map<String, String>> lerCamposCadigoDescricao(Object... constants) throws NoSuchFieldException, IllegalAccessException {
        List<Map<String, String>> result = new ArrayList<>();
        for (Object constante : constants) {
            Class<?> sub = constante.getClass();
            Field fieldCodigo = sub.getDeclaredField(CODIGO);
            fieldCodigo.setAccessible(true);
            Integer codigo = (Integer) fieldCodigo.get(constante);
            Field fieldDescricao = sub.getDeclaredField(DESCRICAO);
            fieldDescricao.setAccessible(true);
            String descricao = (String) fieldDescricao.get(constante);
            Field fieldPermissaoConsultar = sub.getDeclaredField(PERMISSAOCONSULTAR);
            fieldPermissaoConsultar.setAccessible(true);
            String permissaoConsultar = (String) fieldPermissaoConsultar.get(constante);
            Field fieldPermissaoCadastrarEditar = sub.getDeclaredField(PERMISSAOCADASTRAREDITAR);
            fieldPermissaoCadastrarEditar.setAccessible(true);
            String permissaoCadastrarEditar = (String) fieldPermissaoCadastrarEditar.get(constante);
            result.add(montarMapaCampos(codigo, descricao, permissaoConsultar, permissaoCadastrarEditar));
            fieldCodigo.setAccessible(false);
            fieldDescricao.setAccessible(false);
        }
        return result;
    }

    private static Map<String, String> montarMapaCampos(Integer codigo, String descricao, String permissaoConsultar, String permissaoCadastrarEditar) {
        Map<String, String> mapCamposEnum = new HashMap<>();
        mapCamposEnum.put(CODIGO, codigo.toString());
        mapCamposEnum.put(DESCRICAO, descricao);
        mapCamposEnum.put(PERMISSAOCONSULTAR, permissaoConsultar);
        mapCamposEnum.put(PERMISSAOCADASTRAREDITAR, permissaoCadastrarEditar);
        return mapCamposEnum;
    }

}

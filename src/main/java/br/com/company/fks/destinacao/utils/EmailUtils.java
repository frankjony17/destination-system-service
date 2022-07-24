package br.com.company.fks.destinacao.utils;

import br.com.company.fks.destinacao.dominio.enums.EmailEnum;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável por verificar email
 * Created by Basis Tecnologia on 02/06/2016.
 */
@Component
public class EmailUtils {

    private static final String REGEX_VALIDADOR
            = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";

    private static final String INICIO_PATTERN_EMAIL = "\\{\\{";

    private static final String FINAL_PATTERN_EMAIL = "\\}\\}";

    /**
     * Valida email
     * @param email
     * @return true ou false
     */
    public boolean validarEmail(String email){
        Pattern p = Pattern.compile(REGEX_VALIDADOR);
        Matcher m = p.matcher(email);
        return m.find();
    }

    /**
     * Retorna o email
     * @param emailEnum
     * @param params
     * @return String com o email
     */
    public String getEmail(EmailEnum emailEnum, String... params){
        String messagem = ArquivoUtils.lerArquivo(emailEnum.getPath());

        for(int i=0; i < emailEnum.getParams().length; i++){
            messagem = messagem.replaceAll(
                    INICIO_PATTERN_EMAIL + emailEnum.getParams()[i] + FINAL_PATTERN_EMAIL,
                    params[i]);
        }

        return messagem;
    }



}

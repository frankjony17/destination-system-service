package br.com.company.fks.destinacao.exceptions;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Basis Tecnologia on 27/05/2016.
 */

public class NegocioException extends Exception {

    /**
     * Método construtor da classe, que recebe uma string como mensagem
     * @param msg
     * @param exception
     */
    public NegocioException(String msg, Exception exception){
            super(msg, exception);
    }

    /**
     * Método construtor da classe, que recebe uma string como mensagem
     * @param msg
     */
    public NegocioException(String msg){
        super(msg);
    }

    /**
     * Método construtor da classe, que recebe uma lista de strings como mensagens
     * @param mensagens
     */

    public NegocioException(List<String> mensagens){
          super(StringUtils.join(mensagens, "\n"));
    }

}

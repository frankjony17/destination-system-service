package br.com.company.fks.destinacao.exceptions;

/**
 * Created by diego on 12/01/17.
 */
public class EnumException extends RuntimeException {

    public EnumException(Exception exception) {
        super(exception.getMessage(), exception);
    }

}

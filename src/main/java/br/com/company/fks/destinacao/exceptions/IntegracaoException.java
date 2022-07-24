package br.com.company.fks.destinacao.exceptions;

/**
 * Created by Basis Tecnologia on 06/10/2016.
 */
public class IntegracaoException extends Exception {

    /**
     * Cria uma instancia passando a mensagem do erro
     * @param mensagem
     */
    public IntegracaoException(String mensagem) {
        super(mensagem);
    }

    /**
     * Cria uma instancia passando a mensagem e a exception
     * @param mensagem
     * @param e
     */
    public IntegracaoException(String mensagem, Exception e) {
        super(mensagem, e);
    }

}

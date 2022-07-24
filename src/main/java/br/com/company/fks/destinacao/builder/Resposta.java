package br.com.company.fks.destinacao.builder;

/**
 * Created by Basis Tecnologia on 04/10/2016.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Respostas
 * @param <T>
 */
public class Resposta <T> {

    private T resultado;

    private List<String> erros;

    private List<String> mensagens;

    private Long totalElementos;

    private Integer totalPaginas;

    /**
     * Método construtor da classe
     */
    public Resposta() {
        mensagens = new ArrayList<>();
        erros = new ArrayList<>();
    }

    /**
     * Método que retorna o resultado
     * @return
     */
    public T getResultado() {
        return resultado;
    }

    /**
     * Método que altera o resultado
     * @param resultado
     */
    public void setResultado(T resultado) {
        this.resultado = resultado;
    }

    /**
     * Método que retorna uma lista com os erros
     * @return
     */
    public List<String> getErros() {
        return erros;
    }

    /**
     * Método que altera a lista de erros
     * @param erros
     */
    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    /**
     * Método que retorna uma lista com as mensagens
     * @return
     */
    public List<String> getMensagens() {
        return mensagens;
    }

    /**
     * Método que altera a lista de mensagens
     * @param mensagens
     */
    public void setMensagens(List<String> mensagens) {
        this.mensagens = mensagens;
    }


    /**
     * Retorna o total de elementos
     * @return
     */
    public Long getTotalElementos() {
        return totalElementos;
    }

    /**
     * Informa o total de elementos
     * @param totalElementos
     */
    public void setTotalElementos(Long totalElementos) {
        this.totalElementos = totalElementos;
    }

    /**
     * Retorna o total de páginas
     * @return
     */
    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    /**
     * Informa o total de páginas
     * @param totalPaginas
     */
    public void setTotalPaginas(Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
}
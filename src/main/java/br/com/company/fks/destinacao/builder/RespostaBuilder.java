package br.com.company.fks.destinacao.builder;

/**
 * Created by Basis Tecnologia on 04/10/2016.
 */
import java.util.List;
public final class RespostaBuilder <T> {

    private final Resposta<T> resposta;

    private RespostaBuilder () {
        resposta = new Resposta<>();
    }

    /**
     *
     * @return
     */
    public static RespostaBuilder getBuilder () {
        return new RespostaBuilder();
    }

    /**
     *
     * @return
     */
    public Resposta <T> build() {
        return resposta;
    }

    /**
     *
     * @param resultado
     * @return
     */
    public RespostaBuilder<T> setResultado(T resultado) {
        resposta.setResultado(resultado);
        return this;
    }

    /**
     *
     * @param mensagem
     * @return
     */
    public RespostaBuilder<T> setMensagen(String mensagem) {
        resposta.getMensagens().add(mensagem);
        return this;
    }

    /**
     *
     * @param mensagens
     * @return
     */
    public RespostaBuilder<T> setMensagens(List<String> mensagens) {
        resposta.getMensagens().addAll(mensagens);
        return this;
    }

    /**
     *
     * @param erro
     * @return
     */
    public RespostaBuilder<T> setErro(String erro) {
        resposta.getErros().add(erro);
        return this;
    }

    /**
     *
     * @param erros
     * @return
     */
    public RespostaBuilder<T> setErros(List<String> erros) {
        resposta.getErros().addAll(erros);
        return this;
    }


    /**
     * Informa o total de elementos
     * @param totalElementos
     */
    public RespostaBuilder<T> setTotalElementos(Long totalElementos) {
        resposta.setTotalElementos(totalElementos);
        return this;
    }

    /**
     * Informa o total de páginas
     * @param totalPaginas
     */
    public RespostaBuilder<T> setTotalPaginas(Integer totalPaginas) {
        resposta.setTotalPaginas(totalPaginas);
        return this;
    }

}
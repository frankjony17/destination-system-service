package br.com.company.fks.destinacao.builder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Basis Tecnologia on 25/05/2016.
 */

@Getter
@Setter
public class ServicosResposta {

    private List<Object> resposta;

    private List<Object> mensagem;

    /**
     * subclasse Builder
     */
    public static class Builder{

        private List<Object> resposta;

        private List<Object> msg;

        /**
         * Método construtor da subclasse Builder
         */
        public Builder() {
            this.resposta = new ArrayList<>();
            this.msg = new ArrayList<>();
        }

        /**
         * Método que retorna o resultado do Builder
         * @param obj
         * @return
         */
        public Builder resultado(Object... obj){

            for (Object o : obj) {

                if (o.getClass().isArray()) {
                    this.resposta.addAll(Arrays.asList(o));
                } else if (o instanceof List) {
                    this.resposta.addAll((List) o);
                } else if (o instanceof Set){
                    this.resposta.addAll(new ArrayList<>((Set)o));
                }else {
                    this.resposta.add(o);
                }
            }
            return this;
        }

        /**
         * Método que retorna a mensagem do builder
         * @param obj
         * @return
         */
        public Builder mensagem(Object... obj){

            for (Object o : obj) {

                this.msg.add(o);
            }

            return this;
        }

        public ServicosResposta build() {
            return new ServicosResposta(this);
        }
    }

    /**
     * Método que seta a resposta e a mensagem da builder
     * @param builder
     */
    public ServicosResposta(Builder builder) {
        this.resposta = builder.resposta;
        this.mensagem = builder.msg;
    }
}

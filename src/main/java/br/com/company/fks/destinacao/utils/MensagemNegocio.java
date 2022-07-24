package br.com.company.fks.destinacao.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Classe responsável pelas mensagens de negócio
 * Created by Basis Tecnologia on 06/10/2016.
 */
@Component
public class MensagemNegocio {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    /**
     * init
     */
    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    /**
     * Retorna mensagem de negócio de acordo com o código
     * @param code
     * @return
     */
    public String get(String code) {
        return accessor.getMessage(code);
    }

}

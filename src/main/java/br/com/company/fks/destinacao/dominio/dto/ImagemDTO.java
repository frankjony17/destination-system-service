package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Basis Tecnologia on 06/10/2016.
 */

public class ImagemDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nomeImagem;
    @Getter
    @Setter
    private String imagem;

    public ImagemDTO(){}

    public ImagemDTO(Long id) {
        this.id = id;
    }

}
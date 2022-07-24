package br.com.company.fks.destinacao.apresentacao.builder;

import br.com.company.fks.destinacao.dominio.dto.AtoAutorizativoDTO;
import br.com.company.fks.destinacao.dominio.entidades.AtoAutorizativo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by diego on 15/08/17.
 */
public class AtoAutorizativoDTOBuilder {

    private final AtoAutorizativoDTO atoAutorizativoDTO;

    private static final Long ID_ARQUIVO = 1L;

    private AtoAutorizativoDTOBuilder(){
        this.atoAutorizativoDTO = new AtoAutorizativoDTO();
    }

    public static AtoAutorizativoDTOBuilder getBuilder(){
        return new AtoAutorizativoDTOBuilder();
    }

    public AtoAutorizativoDTO build(){
        return this.atoAutorizativoDTO;
    }

    public List<AtoAutorizativoDTO> buildList(){
        return Arrays.asList(this.atoAutorizativoDTO);
    }

    public AtoAutorizativoDTOBuilder getAtoAutorizativoPadrao(){
        this.atoAutorizativoDTO.setDataPublicacao(new Date());
        this.atoAutorizativoDTO.setNumeroAto(1L);
        return this;
    }

}

package br.com.company.fks.destinacao.apresentacao.builder;

import br.com.company.fks.destinacao.dominio.dto.PendenciaDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by basis on 17/08/17.
 */
public class PendenciaDTOBuilder {
    private final PendenciaDTO pendenciaDTO;

    private PendenciaDTOBuilder(){
        this.pendenciaDTO = new PendenciaDTO();
    }

    public static PendenciaDTOBuilder getBuilder(){
        return new PendenciaDTOBuilder();
    }

    public PendenciaDTO build(){
        return this.pendenciaDTO;
    }

    public List<PendenciaDTO> buildList(){
        return Arrays.asList(this.pendenciaDTO);
    }

    public PendenciaDTOBuilder setId(Long id){
        pendenciaDTO.setId(id);
        return this;
    }

    public PendenciaDTOBuilder setDataGerada(Date date){
        //    Date date = new Date(2017/07/17);
        pendenciaDTO.setDataGerada(date);
        return this;
    }

    public PendenciaDTOBuilder setDescricao(String descricao){
        pendenciaDTO.setDescricao(descricao);
        return this;
    }
}

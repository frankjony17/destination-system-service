package br.com.company.fks.destinacao.apresentacao.builder;

import br.com.company.fks.destinacao.dominio.dto.AtoAutorizativoDTO;
import br.com.company.fks.destinacao.dominio.dto.BenfeitoriaDestinadaDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by basis on 17/08/17.
 */
public class BenfeitoriaDestinadaDTOBuilder {
    private final BenfeitoriaDestinadaDTO benfeitoriaDestinadaDTO;

    private BenfeitoriaDestinadaDTOBuilder(){
        this.benfeitoriaDestinadaDTO = new BenfeitoriaDestinadaDTO();
    }

    public static BenfeitoriaDestinadaDTOBuilder getBuilder(){
        return new BenfeitoriaDestinadaDTOBuilder();
    }

    public BenfeitoriaDestinadaDTO build(){
        return this.benfeitoriaDestinadaDTO;
    }

    public List<BenfeitoriaDestinadaDTO> buildList(){
        return Arrays.asList(this.benfeitoriaDestinadaDTO);
    }

    public BenfeitoriaDestinadaDTOBuilder setId(Long id){
        benfeitoriaDestinadaDTO.setId(id);
        return this;
    }

    public BenfeitoriaDestinadaDTOBuilder setAreaUtilizar(BigDecimal area){
        benfeitoriaDestinadaDTO.setAreaUtilizar(area);
        return this;
    }

    public BenfeitoriaDestinadaDTOBuilder setIdBenfeitoria(Long id){
        benfeitoriaDestinadaDTO.setIdBenfeitoria(id);
        return this;
    }

}

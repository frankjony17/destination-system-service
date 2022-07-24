package br.com.company.fks.destinacao.apresentacao.builder;

import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.dto.BenfeitoriaDestinadaDTO;
import br.com.company.fks.destinacao.dominio.dto.EnderecoDTO;
import br.com.company.fks.destinacao.dominio.dto.ImagemDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by basis on 17/08/17.
 */
public class ImovelDTOBuilder {
    private final ImovelDTO imovelDTO;

    private ImovelDTOBuilder(){
        this.imovelDTO = new ImovelDTO();
    }

    public static ImovelDTOBuilder getBuilder(){
        return new ImovelDTOBuilder();
    }

    public ImovelDTO build(){
        return this.imovelDTO;
    }

    public List<ImovelDTO> buildList(){
        return Arrays.asList(this.imovelDTO);
    }

    public ImovelDTOBuilder setId(Long id){
        imovelDTO.setId(id);
        return this;
    }

    public ImovelDTOBuilder setAreaConstruida(BigDecimal area){
        imovelDTO.setAreaConstruida(area);
        return this;
    }

    public ImovelDTOBuilder setAreaTerrenoDestinada(BigDecimal area){
        imovelDTO.setAreaTerrenoDestinada(area);
        return this;
    }

    public ImovelDTOBuilder setBenfeitoriasDestinadas(List<BenfeitoriaDestinadaDTO> benfeitoriaDestinadaDTO){
        imovelDTO.setBenfeitoriasDestinadas(benfeitoriaDestinadaDTO);
        return this;
    }

    public ImovelDTOBuilder setPlantas(List<ArquivoDTO> arquivosDTO){
        imovelDTO.setPlantas(arquivosDTO);
        return this;
    }

    public ImovelDTOBuilder setEndereco(EnderecoDTO enderecoDTO){
        imovelDTO.setEndereco(enderecoDTO);
        return this;
    }

    public ImovelDTOBuilder setRip(String rip){
        imovelDTO.setRip(rip);
        return this;
    }

    public ImovelDTOBuilder setLonLat(Double longitude, Double latitude) {
        imovelDTO.setLongitude(longitude);
        imovelDTO.setLatitude(latitude);
        return this;
    }

    public ImovelDTOBuilder setImagem(String imagem) {
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setImagem(imagem);
        imovelDTO.setImagens(Arrays.asList(imagemDTO));
        return this;
    }


}

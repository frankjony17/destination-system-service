package br.com.company.fks.destinacao.apresentacao.builder;

import br.com.company.fks.destinacao.dominio.dto.EnderecoDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Created by basis on 17/08/17.
 */
public class EnderecoDTOBuilder {
    private final EnderecoDTO enderecoDTO;

    private EnderecoDTOBuilder(){
        this.enderecoDTO = new EnderecoDTO();
    }

    public static EnderecoDTOBuilder getBuilder(){
        return new EnderecoDTOBuilder();
    }

    public EnderecoDTO build(){
        return this.enderecoDTO;
    }

    public List<EnderecoDTO> buildList(){
        return Arrays.asList(this.enderecoDTO);
    }

    public EnderecoDTOBuilder setId(Long id){
        enderecoDTO.setId(id);
        return this;
    }

    public EnderecoDTOBuilder setTipoLogradouro(String tipoLogradouro){
        enderecoDTO.setTipoLogradouro(tipoLogradouro);
        return this;
    }

    public EnderecoDTOBuilder setPais(String pais){
        enderecoDTO.setPais(pais);
        return this;
    }

    public EnderecoDTOBuilder setUf(String uf){
        enderecoDTO.setUf(uf);
        return this;
    }

    public EnderecoDTOBuilder setCep(String cep){
        enderecoDTO.setCep(cep);
        return this;
    }

    public EnderecoDTOBuilder setMunicipio(String municipio){
        enderecoDTO.setMunicipio(municipio);
        return this;
    }

    public EnderecoDTOBuilder setLogradouro(String logradouro){
        enderecoDTO.setLogradouro(logradouro);
        return this;
    }

    public EnderecoDTOBuilder setBairro(String bairro){
        enderecoDTO.setBairro(bairro);
        return this;
    }

}

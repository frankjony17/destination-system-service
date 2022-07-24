package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe responsável por salvar Endereço
 * Created by Basis Tecnologia on 21/10/2016.
 */
@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    /**
     * Salva endereço
     * @param endereco
     * @return Endereco salvo
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Endereco salvar(Endereco endereco) {
        if(endereco != null){
            return enderecoRepository.save(endereco);
        }
        return null;
    }

    /**
     * Atualiza o endereço salvo apartir do novo endereço.
     * @param enderecoSalvo
     * @param novoEndereco
     * @return Endereco
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Endereco atualizar(Endereco enderecoSalvo, Endereco novoEndereco) {
        enderecoSalvo.setBairro(novoEndereco.getBairro());
        enderecoSalvo.setCep(novoEndereco.getCep());
        enderecoSalvo.setCidadeExterior(novoEndereco.getCidadeExterior());
        enderecoSalvo.setCodigoPostal(novoEndereco.getCodigoPostal());
        enderecoSalvo.setComplemento(novoEndereco.getComplemento());
        enderecoSalvo.setLogradouro(novoEndereco.getLogradouro());
        enderecoSalvo.setMunicipio(novoEndereco.getMunicipio());
        enderecoSalvo.setNumero(novoEndereco.getNumero());
        enderecoSalvo.setPais(novoEndereco.getPais());
        enderecoSalvo.setTipoLogradouro(novoEndereco.getTipoLogradouro());
        return salvar(enderecoSalvo);
    }


}

package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Destinatario;
import br.com.company.fks.destinacao.dominio.entidades.Interveniente;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.TipoDestinatario;
import br.com.company.fks.destinacao.repository.DadosResponsavelRepository;
import br.com.company.fks.destinacao.repository.DestinatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rogerio-feitoza on 11/05/18.
 */
@Service
public class DestinatarioService {

    @Autowired
    private DestinatarioRepository destinatarioRepository;

    @Autowired
    private EnderecoCorrespondenciaService enderecoService;



    @Transactional(propagation = Propagation.REQUIRED)
    public Destinatario salvar(Destinatario destinatario, TipoDestinatario tipoDestinatario) {
        if(destinatario != null){
            if(tipoDestinatario.getDescricao().equals("Estatal")){
                destinatario.setEnderecoCorrespondencia(enderecoService
                        .salvar(destinatario.getEnderecoCorrespondencia()));
            }
            Destinatario destinatarioSalvo = destinatarioRepository.save(destinatario);
            return destinatarioSalvo;
        }else {
            return null;
        }
    }
}

package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Interveniente;
import br.com.company.fks.destinacao.repository.IntervenienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import scala.Int;

import java.util.function.Consumer;

@Service
public class IntervenienteService {

    @Autowired
    private IntervenienteRepository intervenienteRepository;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoCorrespondenciaService enderecoService;


    /**
     * Salva os dados referentes ao interveniente no banco de dados
     * @param interveniente
     * @return Interveniente gravado no banco de daddos
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Interveniente salvar(Interveniente interveniente) {
        return intervenienteRepository.save(interveniente);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Interveniente salvar(Interveniente interveniente, DadosResponsavel dadosResponsavel, Boolean copiaDestinacao){
        Interveniente intervenienteSalvo = null;

        if(interveniente != null) {
            if(copiaDestinacao){
                interveniente.setId(null);
            }
            interveniente.setDadosResponsavel(dadosResponsavel);
            interveniente.setTelefones(telefoneService.salvarTelefoneInterveniente(interveniente.getTelefones(), interveniente));
            interveniente.setEnderecoCorrespondencia(enderecoService.salvar(interveniente.getEnderecoCorrespondencia()));
            intervenienteSalvo = intervenienteRepository.save(interveniente);
        }

        return intervenienteSalvo;
    }


}

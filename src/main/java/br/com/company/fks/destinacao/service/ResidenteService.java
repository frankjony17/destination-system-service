package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Residente;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tawan-souza on 28/11/17.
 */
@Service
public class ResidenteService {

    @Autowired
    private ResidenteRepository residenteRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Residente> salvar(List<Residente> lista, Responsavel responsavel) {
        List<Residente> novaListaResidente = new ArrayList<>();
        if (lista != null && !lista.isEmpty()) {
            lista.forEach(residente -> {
                residente.setResponsavel(responsavel);
                novaListaResidente.add(residenteRepository.save(residente));
            });
        }
        return novaListaResidente;
    }
}

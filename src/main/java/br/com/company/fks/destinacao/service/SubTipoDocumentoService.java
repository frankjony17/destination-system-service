package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.SubTipoDocumento;
import br.com.company.fks.destinacao.repository.SubTipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubTipoDocumentoService {

    @Autowired
    private SubTipoDocumentoRepository subTipoDocumentoRepository;


    public List<SubTipoDocumento> buscarSubTipoDocumento(Integer id) {
        return subTipoDocumentoRepository.buscarTipoDocumento(id);
    }
}

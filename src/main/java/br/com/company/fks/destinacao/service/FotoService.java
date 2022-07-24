package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Foto;
import br.com.company.fks.destinacao.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basis on 06/07/17.
 */

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private ArquivoService arquivoService;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Foto> salvar(List<Foto> fotos){

        List<Foto> fotosSalvos = new ArrayList<>();

        if(fotos != null && !fotos.isEmpty()){
            for (Foto foto: fotos) {
                foto.setArquivo(arquivoService.findById(foto.getArquivo().getId()));
                foto.setId(null);
                fotosSalvos.add(fotoRepository.save(foto));
            }
        }

        return fotosSalvos;
    }
}

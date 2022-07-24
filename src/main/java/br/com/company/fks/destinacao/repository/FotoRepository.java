package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by basis on 06/07/17.
 */
@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
}

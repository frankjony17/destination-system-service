package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by raphael on 06/12/16.
 */
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}

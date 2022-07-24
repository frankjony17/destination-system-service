package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.DocumentoArquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by haillanderson on 11/07/17.
 */

@Repository
public interface DocumentoArquivoRepository extends JpaRepository<DocumentoArquivo, Long> {
}

package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.AtoAutorizativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by diego on 15/08/17.
 */
@Repository
public interface AtoAutorizativoRepository extends JpaRepository<AtoAutorizativo, Long> {
}

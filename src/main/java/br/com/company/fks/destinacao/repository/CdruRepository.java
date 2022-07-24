package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Cdru;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface CdruRepository que extende JpaRepository<>
 */
public interface CdruRepository extends JpaRepository<Cdru, Long> {

}

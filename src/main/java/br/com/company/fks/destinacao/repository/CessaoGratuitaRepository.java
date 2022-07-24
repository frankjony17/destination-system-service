package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.CessaoGratuita;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface CessaoGratuitaRepository que extende JpaRepository<>
 */
public interface CessaoGratuitaRepository extends JpaRepository<CessaoGratuita, Long> {

}

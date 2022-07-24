package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.TermoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by basis on 19/01/17.
 */
@Repository
public interface TermoEntregaRepository extends JpaRepository<TermoEntrega, Long> {
}

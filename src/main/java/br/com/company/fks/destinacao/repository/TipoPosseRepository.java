package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.TipoPosse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by haillanderson on 07/07/17.
 */

@Repository
public interface TipoPosseRepository extends JpaRepository<TipoPosse, Long> {
}

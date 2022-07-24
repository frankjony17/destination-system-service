package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.MotivoCancelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author haillanderson
 */

@Repository
public interface MotivoCancelamentoRepository extends JpaRepository<MotivoCancelamento, Integer>{
    
}

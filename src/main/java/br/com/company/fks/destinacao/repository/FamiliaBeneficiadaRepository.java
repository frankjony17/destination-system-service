package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.FamiliaBeneficiada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by diego on 10/11/16.
 */
@Repository
public interface FamiliaBeneficiadaRepository extends JpaRepository<FamiliaBeneficiada, Long> {
}

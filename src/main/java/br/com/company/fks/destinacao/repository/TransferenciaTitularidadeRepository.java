package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Basis Tecnologia on 17/01/17.
 */
@Repository
public interface TransferenciaTitularidadeRepository extends JpaRepository<TransferenciaTitularidade, Long> {
}

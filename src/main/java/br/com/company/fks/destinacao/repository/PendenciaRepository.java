package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Pendencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface PendenciaRepository extends JpaRepository<Pendencia, Long> {
}

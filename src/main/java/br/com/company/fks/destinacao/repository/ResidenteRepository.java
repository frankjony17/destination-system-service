package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Residente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by basis on 28/11/17.
 */
@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Long> {
}

package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tawan-souza on 28/11/17.
 */
@Repository
public interface DadosResponsavelRepository extends JpaRepository<DadosResponsavel, Long> {
}

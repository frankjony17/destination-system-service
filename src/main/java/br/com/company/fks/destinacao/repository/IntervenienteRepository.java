package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Interveniente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervenienteRepository extends JpaRepository<Interveniente, Long> {
}

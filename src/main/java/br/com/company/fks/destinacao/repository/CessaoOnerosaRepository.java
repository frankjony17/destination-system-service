package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.CessaoOnerosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CessaoOnerosaRepository extends JpaRepository<CessaoOnerosa, Long> {

}

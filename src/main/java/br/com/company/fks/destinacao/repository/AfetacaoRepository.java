package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Afetacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfetacaoRepository extends JpaRepository<Afetacao, Long> {


}

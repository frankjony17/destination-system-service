package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by diego on 16/12/16.
 */
@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
}

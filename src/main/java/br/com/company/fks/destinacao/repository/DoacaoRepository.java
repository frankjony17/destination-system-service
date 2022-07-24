package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
}

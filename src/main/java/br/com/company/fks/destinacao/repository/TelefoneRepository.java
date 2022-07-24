package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Basis Rogerio Feitoza on 26/12/2017.
 */
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}

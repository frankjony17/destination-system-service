package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Destinatario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Basis Tecnologia on 18/01/2017.
 */
@Repository
public interface DestinatarioRepository extends JpaRepository<Destinatario, Long> {

}

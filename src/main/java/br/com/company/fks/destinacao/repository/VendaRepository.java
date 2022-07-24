package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Basis Tecnologia on 25/10/2016.
 */
@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}

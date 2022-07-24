package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Basis Tecnologia on 21/10/2016.
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

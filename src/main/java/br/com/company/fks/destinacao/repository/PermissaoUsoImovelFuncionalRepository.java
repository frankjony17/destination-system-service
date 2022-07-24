package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.PermissaoUsoImovelFuncional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rogerio on 13/10/17.
 */
@Repository
public interface PermissaoUsoImovelFuncionalRepository extends JpaRepository<PermissaoUsoImovelFuncional, Long> {

}


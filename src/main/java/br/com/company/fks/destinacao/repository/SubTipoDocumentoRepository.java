package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.SubTipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubTipoDocumentoRepository extends JpaRepository<SubTipoDocumento,Integer> {

    @Query("SELECT t FROM SubTipoDocumento t " +
            "JOIN t.tipoDocumento ti WHERE ti.id =:id")
    List<SubTipoDocumento> buscarTipoDocumento(@Param("id") Integer id);

}

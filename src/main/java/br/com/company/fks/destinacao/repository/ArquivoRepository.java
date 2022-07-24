package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    /**
     * Busca arquivos pelos ids.
     * @param ids
     * @return
     */
    @Query("Select a FROM Arquivo a WHERE a.id IN(:ids)")
    List<Arquivo> buscarArquivos(@Param("ids") List<Long> ids);

    @Modifying
    @Query(value = "delete from destinacao.tb_transferencia_ato_complementar as ac where ac.id_arquivo = :id", nativeQuery = true)
    void deletarAtosComplementares(@Param("id") Long id);

}

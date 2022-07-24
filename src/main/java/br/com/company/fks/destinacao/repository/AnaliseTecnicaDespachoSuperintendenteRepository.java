package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoID;
import br.com.company.fks.destinacao.dominio.entidades.AnaliseTecnicaDespachoSuperintendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by diego on 19/12/16.
 */
@Repository
public interface AnaliseTecnicaDespachoSuperintendenteRepository
        extends JpaRepository<AnaliseTecnicaDespachoSuperintendente, AnaliseTecnicaDespachoID> {

    /**
     *
     * @param idAnalise Deleta a Analise pelo ID
     */
    @Modifying
    @Query("delete from AnaliseTecnicaDespachoSuperintendente a " +
            "WHERE a.analiseTecnicaDespachoID.analiseTecnica.id  =:idAnalise")
    void deleteByIdAnalise(@Param("idAnalise") Long idAnalise);

}

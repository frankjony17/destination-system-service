package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.BenfeitoriaDestinada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by diego on 19/01/17.
 */
@Repository
public interface BenfeitoriaDestinadaRepository extends JpaRepository<BenfeitoriaDestinada, Long> {

    /**
     *
     * @param rip Busca todos as Benfeitorias pelo Id do Imovel
     * @return
     */
    @Query("SELECT b FROM BenfeitoriaDestinada b " +
            "JOIN b.destinacaoImovel di " +
            "JOIN di.imovel i WHERE i.rip =:rip")
    List<BenfeitoriaDestinada> buscarBenfeitoriasIdImovel(@Param("rip") String rip);

    /**
     *
     * @param idImovel
     * @param idBenfeitoria
     * Busca todos as Benfeitorias pelo Id do Imovel
     * @return
     */
    @Query("SELECT b FROM BenfeitoriaDestinada b " +
            "JOIN b.destinacaoImovel di " +
            "JOIN di.imovel i WHERE i.id =:idImovel " +
            "AND b.idBenfeitoria =:idBenfeitoria ")
    BenfeitoriaDestinada buscarBenfeitoriasIdImovelIdBenfeitoria(@Param("idImovel") Long idImovel, @Param("idBenfeitoria") Long idBenfeitoria);


    /**
     *
     * @param id
     * Busca todos as Benfeitorias pelo Id do Imovel
     * @return
     */
    @Query("SELECT b FROM BenfeitoriaDestinada b " +
            "WHERE b.idBenfeitoria =:id " +
            "AND b.ativa = true ")
    BenfeitoriaDestinada buscarBenfeitoriaPorId(@Param("id") Long id);

}

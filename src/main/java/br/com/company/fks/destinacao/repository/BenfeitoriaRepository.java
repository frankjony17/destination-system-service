package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by diego on 31/01/17.
 */
@Repository
public interface BenfeitoriaRepository extends CrudRepository<Benfeitoria, Long> {

    /**
     * Busca as benfeitorias de acordo com o id do cadastro imovel informado.
     * @param idCadastroImovel
     * @return
     */
    @Query("SELECT b FROM Benfeitoria b JOIN b.imovel i WHERE i.idCadastroImovel =:idCadastroImovel " +
            "AND b.ativa = true ")
    List<Benfeitoria> buscarBenfeitoriasPorImovelIdCadastro(@Param("idCadastroImovel") Long idCadastroImovel);

    /**
     * Apaga uma benfeitoria sem uma parcela
     * @param id
     */
    @Modifying
    @Query("DELETE FROM Benfeitoria b " +
            "WHERE b.parcela is null AND b.id =:id")
    void deletarBenfeitoriaSemParcela(@Param("id") Long id);

    /**
     * Busca as benfeitorias que não tem parcela do imovel
     * @return
     */
    @Query("SELECT b FROM Benfeitoria b " +
            "LEFT JOIN b.parcela p " +
            "JOIN b.imovel i " +
            "WHERE b.parcela is null AND i.id =:id " +
            "AND p.ativa = true " +
            "AND b.ativa = true ")
    List<Benfeitoria> buscarBenfeitoriasSemParcelaIdImovel(@Param("id") Long id);

    /**
     * Busca as benfeitorias que não tem parcela do imovel
     * @return
     */
    @Query("SELECT b FROM Benfeitoria b " +
            "LEFT JOIN b.parcela p " +
            "JOIN b.imovel i " +
            "WHERE b.parcela is null AND i.id =:id " +
            "AND p.ativa = true " +
            "AND b.ativa = true ")
    Benfeitoria buscarBenfeitoriaPorId(@Param("id") Long id);




    /**
     * Busca as benfeitorias que não tem parcela do imovel
     * @return
     */
    @Query("SELECT b FROM Benfeitoria b " +
            " WHERE b.id =:id " +
            " AND b.ativa = true ")
    List<Benfeitoria>  buscarListaBenfeitoriaPorId(@Param("id") Long id);

    /**
     * Soma a area total construida de um imóvel.
     * @return
     */
    @Query("SELECT sum(b.areaConstruida) FROM Benfeitoria b " +
            "WHERE b.imovel.rip = :rip and b.ativa = true and b.codigo LIKE 'E%' ")
    BigDecimal sumAreaConstruida(@Param("rip") String rip);

}

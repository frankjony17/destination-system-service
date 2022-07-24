package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by diego on 01/03/17.
 */
@Repository
public interface ParcelaRepository extends CrudRepository<Parcela, Long> {

    /**
     * Realiza um update no banco pelo id da parcela (a parcela com o id informado é modificado o campo ativa para false)
     * @param id
     */
    @Modifying
    @Query("UPDATE Parcela  SET ativa = false " +
            "WHERE id =:id")
    void setarParcelaInativa(@Param("id") Long id);


    /**
     * Realiza consulta no banco pelo rip retornando o imóvel encontrado com o número de parcelas do mesmo
     * @param rip
     * @return
     */
    @Query("SELECT COUNT(p) FROM Imovel i " +
            "INNER JOIN i.parcelas p " +
            "WHERE i.rip =:rip AND p.ativa = true")
    Integer buscarNumeroParcelas(@Param("rip") String rip);

    @Query("SELECT p FROM Parcela p JOIN p.destinacaoImoveis di WHERE di.id IN(:idsDestinacaoImovel) AND p.sequencial = 'P0'")
    Parcela findByIdDestinacaoImovelParcelaInicial(@Param("idsDestinacaoImovel") Set<Long> idsDestinacaoImovel);

    /**
     * Busca as parcelas sem utilização.
     * @param rip
     * @param codigoUtilizacao
     * @return
     */
    @Query("SELECT DISTINCT p FROM Parcela p JOIN p.destinacaoImoveis di JOIN di.imovel i " +
            "JOIN di.destinacao d " +
            "WHERE i.rip =:rip " +
            "AND di.codigoUtilizacao =:codigoUtilizacao " +
            "AND p.ativa = true")
    List<Parcela> buscarParcelasSemUso(@Param("rip") String rip, @Param("codigoUtilizacao") String codigoUtilizacao);

    /**
     * Busca as parcelas canceladas.
     * @param rip
     * @return
     */
    @Query("SELECT DISTINCT p FROM Parcela p " +
            "JOIN p.imovel i " +
            "WHERE i.rip =:rip AND p.ativa = false " +
            "ORDER BY p.id")
    List<Parcela> buscarParcelasCanceladas(@Param("rip") String rip);


    /**
     * Busca as parcelas.
     * @param rip
     * @return
     */
    @Query("SELECT DISTINCT p FROM Parcela p " +
            "JOIN p.imovel i " +
            "WHERE i.rip =:rip AND p.ativa = true " +
            "ORDER BY p.id")
    List<Parcela> buscarParcelasPorId(@Param("rip") String rip);
    /**
     * Recupera os ids das parcelas utilizadas.
     * @param id
     * @return
     */
    @Query("SELECT p.id FROM Parcela p " +
            "JOIN p.destinacaoImoveis di " +
            "JOIN di.destinacao d " +
            "JOIN d.tipoDestinacao td " +
            "JOIN p.imovel i " +
            "WHERE i.id =:id AND td.id <> 12 AND p.ativa = true")
    Set<Long> buscarIdsParcelasUtilizadasPorImovelId(@Param("id") Long id);

    /**
     * Conta a quantidade de parcelas que já foram destinadas
     * @param id
     * @return
     */
    @Query("SELECT COUNT(1) FROM Parcela p JOIN p.destinacaoImoveis di JOIN di.destinacao d " +
           "JOIN d.tipoDestinacao td " +
           "WHERE td.id <> 12 AND p.ativa = true AND p.id =:id")
    Integer contarQuantidadeParcelaDestinacao(@Param("id") Long id);

    /**
     * Conta a quantidade de parcelas que já foram destinadas
     * @param id
     * @param sequencial
     * @return
     */
    @Query("SELECT DISTINCT p FROM Parcela p " +
            "JOIN p.imovel i " +
            "WHERE p.sequencial =:sequencial " +
            "AND i.id =:id " +
            "AND p.ativa = true ")
    Parcela buscarParcelaPorIdImovelSequencial(@Param("id") Long id, @Param("sequencial") String sequencial);

    /**
     * Conta a quantidade de parcelas que já foram destinadas
     * @param id
     * @return
     */
    @Query("SELECT DISTINCT p FROM Parcela p " +
            "where p.id =:id " +
            "AND p.ativa = true ")
    Parcela buscarParcelaPorId(@Param("id") Long id);

}

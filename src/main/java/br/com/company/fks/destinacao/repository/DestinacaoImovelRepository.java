package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.UtilizacaoDTO;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface DestinacaoImovelRepository extends JpaRepository<DestinacaoImovel, Long> {

    /**
     *
     * @param idCadastroImovel Recupera o codifo de utilizacao do ultimo imovel utilizado
     * @return
     */
    @Query("SELECT di.codigoUtilizacao FROM DestinacaoImovel di " +
            "JOIN di.imovel i WHERE di.id = " +
            "(SELECT MAX(d.id) FROM DestinacaoImovel d JOIN d.imovel im WHERE im.idCadastroImovel =:idCadastroImovel)")
    String getUltimaUtilizacaoImovel(@Param("idCadastroImovel") Long idCadastroImovel);


    /**
     * Busca código de utilização pelo rip e idParcela
     * @param rip
     * @param
     * @return
     */
    @Query("SELECT new br.com.company.fks.destinacao.dominio.dto.DestinacaoImovelDTO(i.rip, di.codigoUtilizacao)"+
            "FROM DestinacaoImovel di " +
            "JOIN di.destinacao d " +
            "JOIN d.tipoDestinacao td " +
            "JOIN di.imovel i " +
            "JOIN i.benfeitorias b " +
            "WHERE i.rip =:rip AND td.id = 12")
    DestinacaoImovelDTO buscarCodigoUtilizacao(@Param("rip") String rip);

    /**
     * Busca Destinação Imóvel pelo id do imóvel e pelo sequencial da parcela.
     * @param idImovel
     * @param sequencial
     * @return
     */
    @Query("SELECT d "+
            "FROM DestinacaoImovel d " +
            "JOIN d.imovel i " +
            "JOIN d.parcelas p " +
            "WHERE i.id =:idImovel " +
            "AND p.sequencial =:sequencial " +
            "AND p.ativa = true " +
            "AND d.codigoUtilizacao =:codigoUtilizacao " +
            "AND d.ultimaDestinacao = true ")
    DestinacaoImovel findByIdImovelSequencialParcelaCodigoUtilizacao(@Param("idImovel") Long idImovel,
                                                                     @Param("sequencial") String sequencial,
                                                                     @Param("codigoUtilizacao") String codigoUtilizacao);
    /**
     * Busca Destinação Imóvel pelo id do imóvel e pelo sequencial da parcela.
     * @param idImovel
     * @return
     */
    @Query("SELECT d "+
            "FROM DestinacaoImovel d " +
            "JOIN d.imovel i " +
            "JOIN d.parcelas p " +
            "WHERE i.id =:idImovel " +
            "AND p.ativa = true " +
            "AND d.ultimaDestinacao = true ")
    DestinacaoImovel findByIdImovelSequencial(@Param("idImovel") Long idImovel);


    @Query("SELECT NEW br.com.company.fks.destinacao.dominio.dto.UtilizacaoDTO(i.rip, di.codigoUtilizacao, p.sequencial," +
            " t.descricao, r.nome, r.cpfCnpj) " +
            "FROM DestinacaoImovel di " +
            "JOIN di.imovel i " +
            "JOIN di.parcelas p " +
            "JOIN di.destinacao d " +
            "JOIN d.tipoDestinacao t " +
            "JOIN d.dadosResponsavel dr " +
            "JOIN dr.responsaveis r " +
            "WHERE i.rip =:rip " +
            "AND t.id <> 12 " +
            "AND p.ativa = true")
    List<UtilizacaoDTO> buscarTodasUtilizacoesPorRip(@Param("rip") String rip);

    @Query("SELECT d FROM DestinacaoImovel d WHERE d.id IN(:ids)")
    List<DestinacaoImovel> findByIds(@Param("ids") Set<Long> ids);


    @Query("SELECT d FROM DestinacaoImovel d " +
            "WHERE d.id =:id " +
            "AND d.codigoUtilizacao like '0001'")
    List<DestinacaoImovel> findByDestinacaoImovelById(@Param("id") Long id);

    @Query("SELECT d FROM DestinacaoImovel d " +
            "JOIN d.imovel i " +
            "WHERE i.id =:id " +
            "ORDER BY d.id ")
    List<DestinacaoImovel> buscarDestinacaoImovelPorId(@Param("id") Long id);

}

package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    /**
     * Recupera todos os Imoveis atrelado a um RIP
     * @param rip
     * @return
     */
    @Query("SELECT DISTINCT i from Imovel i where i.rip =:rip")
    List<Imovel> buscarPorRip(@Param(Constants.RIP) String rip);

    /**
     * Recupera todos os Imoveis atrelado a um RIP e possuem parcela ativas
     * @param rip
     * @return
     */
    @Query("SELECT DISTINCT i from Imovel i JOIN i.parcelas p where i.rip =:rip AND p.ativa = true")
    List<Imovel> buscarPorRipParcelaAtiva(@Param(Constants.RIP) String rip);

    /**
     *
     * @param rip Recupera todos os Imoveis atrelado a um RIP
     * @return
     */
    @Query("SELECT DISTINCT i from Imovel i where i.rip =:rip")
    Imovel findByRip(@Param(Constants.RIP) String rip);

    /**
     *
     * @param rip Recupera a quantidade de imoveis atrelado a um rip
     * @return
     */
    @Query("SELECT COUNT(d.id) from DestinacaoImovel di " +
            "JOIN di.imovel i " +
            "JOIN di.destinacao d " +
            "JOIN d.tipoDestinacao td " +
            "WHERE i.rip =:rip AND td.id <> 12")
    Integer quantidadesDestinacaoImovel(@Param("rip") String rip);

    /**
     *
     * @param rip Recupera a quantidade de destinações ativas de uma determinado RIP
     * @return
     */
    @Query("SELECT COUNT(d.id) from DestinacaoImovel di " +
            "JOIN di.imovel i " +
            "JOIN di.destinacao d " +
            "JOIN d.tipoDestinacao td " +
            "JOIN d.statusDestinacao sd WHERE i.rip =:rip AND sd.id = 1 AND td.id <> 12")
    Integer quantidadesDestinacaoAtivaImovel(@Param("rip") String rip);

    @Query("SELECT COUNT(d.id) from DestinacaoImovel di " +
            "JOIN di.imovel i " +
            "JOIN di.destinacao d " +
            "JOIN d.statusDestinacao sd " +
            "WHERE i.rip =:rip " +
            "AND sd.id = 1 " +
            "AND d.tipoDestinacao IN (6,7,10)")
    Integer quantidadesDestinacaoAtivaImovelCessaoEntrega(@Param(Constants.RIP) String rip);

    /**
     *
     * @param idCadastroImovel Recupera o imovel pelo id do imovel cadastrado
     * @return
     */
    @Query("SELECT i FROM Imovel i WHERE i.idCadastroImovel =:idCadastroImovel")
    Imovel findByIdCadastroImovel(@Param("idCadastroImovel") Long idCadastroImovel);

    /**
     *
     * @param idCadastroImovel Recupera o ID do imovel pelo id do cadastro do imovel
     * @return
     */
    @Query("SELECT i.id FROM Imovel i WHERE i.idCadastroImovel =:idCadastroImovel")
    Long getIdImovelByIdCadastroImovel(@Param("idCadastroImovel") Long idCadastroImovel);


    /**
     * Realiza uma consulta no banco pelo cep, rip e uf, trasendo dados sobre parcelas e imóvel
     * @param rip
     * @param cep
     * @param uf
     * @param municipio
     * @param pageable
     * @return
     */
    @Query("SELECT new br.com.company.fks.destinacao.dominio.dto.ImovelDTO ( " +
            "    i.rip, " +
            "    e, " +
            "    i.areaTerreno, " +
            //"    (select sum(b.areaConstruida) from Benfeitoria b where b.imovel.id = i.id and b.ativa = true and b.codigo LIKE 'E%'), " +
            "    0, " +
            "    u.area, " +
            //"    (select count(p) from Parcela p where p.imovel = i and p.ativa = true) " +
            "    0 " +
            " ) " +
            "FROM Imovel i " +
            "INNER JOIN i.endereco e " +
            "LEFT JOIN i.unidadeAutonoma u " +
            "WHERE (e.uf LIKE :uf) "+
            "AND (:cep is null or e.cep = :cep) "+
            "AND (:rip is null or i.rip LIKE %:rip%) " +
            "AND (:municipio is null or UPPER(e.municipio) LIKE %:municipio%) " +
            "GROUP BY i.rip, i.id, e, u.area ")
    Page<ImovelDTO> buscarDestinacao(@Param(Constants.RIP) String rip,
                                           @Param("cep") String cep,
                                           @Param("uf") String uf,
                                           @Param("municipio") String municipio,
                                           Pageable pageable);


    /**
     * Recupera todas as parcelas canceladas do imovel informado
     * @param rip
     * @return
     */
    @Query("SELECT DISTINCT i " +
            "FROM Imovel i " +
            "INNER JOIN i.parcelas p " +
            "INNER JOIN p.destinacaoImoveis di " +
            "JOIN p.benfeitorias b "+
            "WHERE i.rip =:rip " +
            " AND p.ativa = true " +
            " AND di.ultimaDestinacao = true " +
            " AND b.ativa = true "

    )
    Imovel buscarDadosRipUtilizacao(@Param(Constants.RIP) String rip);


    /**
     * Recupera todas as parcelas canceladas do imovel informado
     * @param rip
     * @return
     */
    @Query("SELECT DISTINCT i " +
            "FROM Imovel i " +
            "INNER JOIN i.parcelas p " +
            "INNER JOIN p.destinacaoImoveis di " +
            "INNER JOIN p.benfeitorias b "+
            "WHERE i.rip =:rip ")
    Imovel buscarDadosBenfeitorias(@Param(Constants.RIP) String rip);


    @Query("SELECT new br.com.company.fks.destinacao.dominio.dto.ImovelDTO(i.id, i.endereco, i.proprietario, " +
            "di.destinacao, i.idCadastroImovel, i.rip)" +
            "FROM DestinacaoImovel di " +
            "INNER JOIN di.imovel i " +
            "WHERE i.rip =:rip " +
            "AND di.ultimaDestinacao = true ")
    ImovelDTO buscarDadosPosseInformal(@Param(Constants.RIP) String rip);
    
}
package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface DestinacaoRepository extends JpaRepository<Destinacao, Long> {

    String RIP = "rip";

    /**
     *
     * @param rip Recupera todas as destinações atrelado a um RIP
     * @return
     */
    @Query("SELECT di.destinacao from DestinacaoImovel di " +
            "JOIN di.imovel i " +
            "WHERE i.rip =:rip AND di.destinacao.tipoDestinacao.id <> 12")
    List<Destinacao> buscarDestinacaoPorRip(@Param(RIP) String rip);

    /**
     * Busca destinação por id
     * @param id da destinação
     * @return
     */
    @Query("SELECT d " +
            "FROM Destinacao d " +
            "INNER JOIN d.destinacaoImoveis de " +
            "INNER JOIN de.imovel i " +
            "INNER JOIN i.endereco e " +
            "INNER JOIN de.parcelas p " +
            "WHERE d.id = :id ")
    List<Destinacao> buscarDestinacaoPorId(@Param("id") Long id);

    @Query("SELECT DISTINCT d.id, " +
            "i.rip, " +
            "de.codigoUtilizacao, " +
            "p.sequencial, " +
            "td.id, " +
            "td.descricao, " +
            "tu.id, " +
            "tu.descricao, " +
            "stu.id, " +
            "stu.descricao, " +
            "de.fracaoIdeal, " +
            "SUM(b.areaConstruida), " +
            "r.nome, " +
            "r.cpfCnpj, " +
            "e.cep, " +
            "e.tipoLogradouro, " +
            "e.logradouro, " +
            "e.numero, " +
            "e.complemento, " +
            "e.municipio, " +
            "e.bairro, " +
            "e.uf, " +
            "e.pais, " +
            "e.cidadeExterior, " +
            "i.areaTerreno, " +
            "c.dataInicio, " +
            "c.dataFinal, " +
            "sd.id, " +
            "sd.descricao, " +
            "i.codigoClassificacaoImovel, " +
            "d.ativa, " +
            "d.dataDestinacaoHistorico, " +
            "d.versaoDestinacao, " +
            "d.dataInicioFundamento, " +
            "d.dataFinalFundamento " +
            "FROM DestinacaoPendencia dp " +
            "LEFT JOIN dp.destinacaoPendenciaID.destinacao d " +
            "LEFT JOIN dp.destinacaoPendenciaID.pendencia pd " +
            "JOIN d.statusDestinacao sd " +
            "LEFT JOIN d.contrato c " +
            "LEFT JOIN d.utilizacao u " +
            "LEFT JOIN u.tipoUtilizacao tu " +
            "LEFT JOIN u.subTipoUtilizacao stu " +
            "LEFT JOIN d.dadosResponsavel dr " +
            "LEFT JOIN dr.responsaveis r " +
            "JOIN d.tipoDestinacao td " +
            "JOIN d.destinacaoImoveis de " +
            "JOIN de.parcelas p " +
            "JOIN de.imovel i " +
            "LEFT JOIN p.benfeitorias b " +
            "JOIN i.endereco e " +
            "WHERE pd.descricao = :pendencia " +
            "AND p.ativa = true " +
            "GROUP BY dp.dataGerada, " +
            "d.id, " +
            "i.rip, " +
            "de.codigoUtilizacao, " +
            "p.sequencial, " +
            "td.id, " +
            "td.descricao, " +
            "tu.id, " +
            "tu.descricao, " +
            "stu.id, " +
            "stu.descricao, " +
            "de.fracaoIdeal, " +
            "r.nome, " +
            "r.cpfCnpj, " +
            "e.cep, " +
            "e.tipoLogradouro, " +
            "e.tipoLogradouro, " +
            "e.logradouro, " +
            "e.numero, " +
            "e.complemento, " +
            "e.municipio, " +
            "e.bairro, " +
            "e.uf, " +
            "e.pais, " +
            "e.cidadeExterior, " +
            "i.areaTerreno, " +
            "c.dataInicio, " +
            "c.dataFinal, " +
            "sd.id, " +
            "sd.descricao, " +
            "i.codigoClassificacaoImovel, " +
            "d.ativa, " +
            "d.dataDestinacaoHistorico, " +
            "d.versaoDestinacao, " +
            "d.dataInicioFundamento, " +
            "d.dataFinalFundamento " +
            "ORDER BY d.id"
    )
    List<Object> buscarDestinacaoPorPendencia(@Param("pendencia") String pendencia);

    /**
     *
     * @param codFundamentoLegal Recupera uma lista de ids de destinação atrelada a um fundamento legal
     * @return
     */
    @Query("SELECT d.id from Destinacao d WHERE d.codFundamentoLegal =:codFundamentoLegal")
    List<Long> buscarIdsDestinacaoPorFundamentoLegal(@Param("codFundamentoLegal") Long codFundamentoLegal);

    /** TODO implementar codigo contrato e classificaçao quando for impementado essas funcionalidades.
     * Efetua a consulta de uma destinação
     * @param rip
     * @param codigoUtilizadao
     * @param codigoParcela
     * @param idTipoUtilizacao
     * @param idSubTipoUtilizacao
     * @param pais
     * @param cep
     * @param uf
     * @param municipio
     * @param cidadeExterior
     * @param idTiposDestinacao
     * @param nomeResponsavel
     * @param cpfCnpjResponsavel
     * @param fracaoIdealInicial
     * @param fracaoIdealFinal
     * @param areaConstruidaInicial
     * @param areaConstruidaFinal
     * @return
     */
    @Query("SELECT DISTINCT d.id, " +
            "i.rip, " +
            "de.codigoUtilizacao, " +
            "p.sequencial, " +
            "td.id, " +
            "td.descricao, " +
            "tu.id, " +
            "tu.descricao, " +
            "stu.id, " +
            "stu.descricao, " +
            "de.fracaoIdeal, " +
            "SUM(b.areaConstruida), " +
            "r.nome, " +
            "r.cpfCnpj, " +
            "e.cep, " +
            "e.tipoLogradouro, " +
            "e.logradouro, " +
            "e.numero, " +
            "e.complemento, " +
            "e.municipio, " +
            "e.bairro, " +
            "e.uf, " +
            "e.pais, " +
            "e.cidadeExterior, " +
            "i.areaTerreno, " +
            "c.dataInicio, " +
            "c.dataFinal, " +
            "sd.id, " +
            "sd.descricao, " +
            "i.codigoClassificacaoImovel, " +
            "d.ativa, " +
            "d.dataDestinacaoHistorico, " +
            "d.versaoDestinacao, " +
            "d.dataInicioFundamento, " +
            "d.dataFinalFundamento " +
            "FROM Destinacao d " +
            "JOIN d.statusDestinacao sd " +
            "LEFT JOIN d.contrato c " +
            "LEFT JOIN d.utilizacao u " +
            "LEFT JOIN u.tipoUtilizacao tu " +
            "LEFT JOIN u.subTipoUtilizacao stu " +
            "LEFT JOIN d.dadosResponsavel dr " +
            "LEFT JOIN dr.responsaveis r " +
            "JOIN d.tipoDestinacao td " +
            "JOIN d.destinacaoImoveis de " +
            "JOIN de.parcelas p " +
            "JOIN de.imovel i " +
            "LEFT JOIN p.benfeitorias b " +
            "JOIN i.endereco e " +
            "WHERE p.ativa = true " +
            "AND d.ativa = true " +
            "AND de.ultimaDestinacao = true " +
            "AND (:rip IS NULL OR i.rip = :rip) " +
            "AND (:codigoUtilizacao IS NULL OR de.codigoUtilizacao = :codigoUtilizacao) " +
            "AND (:codigoParcela IS NULL OR p.sequencial = :codigoParcela) " +
            "AND (:idTipoUtilizacao IS NULL OR tu.id = :idTipoUtilizacao) " +
            "AND (:idSubTipoUtilizacao IS NULL OR stu.id = :idSubTipoUtilizacao) " +
            "AND (:pais IS NULL OR e.pais = :pais) " +
            "AND (:cep IS NULL OR e.cep = :cep) " +
            "AND (:uf IS NULL OR e.uf = :uf) " +
            "AND (:municipio IS NULL OR e.municipio = :municipio) " +
            "AND (:cidadeExterior IS NULL OR e.cidadeExterior = :cidadeExterior) " +
            "AND ((:idTiposDestinacao) IS NULL OR td.id IN (:idTiposDestinacao)) " +
            "AND (:nomeResponsavel IS NULL OR r.nome = :nomeResponsavel) " +
            "AND (:cpfCnpjResponsavel IS NULL OR r.cpfCnpj = :cpfCnpjResponsavel) " +
            "AND (:numeroContrato IS NULL OR c.numero = :numeroContrato) " +
            "AND ((:fracaoIdealInicial IS NULL OR :fracaoIdealFinal IS NULL) OR de.fracaoIdeal BETWEEN :fracaoIdealInicial AND :fracaoIdealFinal) " +
            "AND (:classificacao IS NULL OR i.codigoClassificacaoImovel = :classificacao) " +
            "GROUP BY d.id, " +
                "i.rip, " +
                "de.codigoUtilizacao, " +
                "p.sequencial, " +
                "td.id, " +
                "td.descricao, " +
                "tu.id, " +
                "tu.descricao, " +
                "stu.id, " +
                "stu.descricao, " +
                "de.fracaoIdeal, " +
                "r.nome, " +
                "r.cpfCnpj, " +
                "e.cep, " +
                "e.tipoLogradouro, " +
                "e.tipoLogradouro, " +
                "e.logradouro, " +
                "e.numero, " +
                "e.complemento, " +
                "e.municipio, " +
                "e.bairro, " +
                "e.uf, " +
                "e.pais, " +
                "e.cidadeExterior, " +
                "i.areaTerreno, " +
                "c.dataInicio, " +
                "c.dataFinal, " +
                "sd.id, " +
                "sd.descricao, " +
                "i.codigoClassificacaoImovel, " +
                "d.ativa, " +
                "d.dataDestinacaoHistorico, " +
                "d.versaoDestinacao, " +
                "d.dataInicioFundamento, " +
                "d.dataFinalFundamento " +
                "HAVING ((:areaConstruidaInicial IS NULL OR :areaConstruidaFinal IS NULL) OR " +
                "(SUM(b.areaConstruida) BETWEEN :areaConstruidaInicial AND :areaConstruidaFinal)) " +
                "ORDER BY d.id"
            )
    Page<Object> consultar(@Param(RIP) String rip,
                                     @Param("codigoUtilizacao") String codigoUtilizadao,
                                     @Param("codigoParcela") String codigoParcela,
                                     @Param("idTipoUtilizacao") Long idTipoUtilizacao,
                                     @Param("idSubTipoUtilizacao") Long idSubTipoUtilizacao,
                                     @Param("pais") String pais,
                                     @Param("cep") String cep,
                                     @Param("uf") String uf,
                                     @Param("municipio") String municipio,
                                     @Param("cidadeExterior") String cidadeExterior,
                                     @Param("idTiposDestinacao") List<Integer> idTiposDestinacao,
                                     @Param("nomeResponsavel") String nomeResponsavel,
                                     @Param("cpfCnpjResponsavel") String cpfCnpjResponsavel,
                                     @Param("fracaoIdealInicial") BigDecimal fracaoIdealInicial,
                                     @Param("fracaoIdealFinal") BigDecimal fracaoIdealFinal,
                                     @Param("areaConstruidaInicial") BigDecimal areaConstruidaInicial,
                                     @Param("areaConstruidaFinal") BigDecimal areaConstruidaFinal,
                                     @Param("numeroContrato") String numeroContrato,
                                     @Param("classificacao") Long classificacao,
                                     Pageable pageable
                               );


    /** TODO implementar codigo contrato e classificaçao quando for impementado essas funcionalidades.
     * Efetua a consulta de uma destinação
     * @return
     */
    @Query("SELECT DISTINCT d.id, " +
            "i.rip, " +
            "de.codigoUtilizacao, " +
            "p.sequencial, " +
            "td.id, " +
            "td.descricao, " +
            "tu.id, " +
            "tu.descricao, " +
            "stu.id, " +
            "stu.descricao, " +
            "de.fracaoIdeal, " +
            "SUM(b.areaConstruida), " +
            "r.nome, " +
            "r.cpfCnpj, " +
            "e.cep, " +
            "e.tipoLogradouro, " +
            "e.logradouro, " +
            "e.numero, " +
            "e.complemento, " +
            "e.municipio, " +
            "e.bairro, " +
            "e.uf, " +
            "e.pais, " +
            "e.cidadeExterior, " +
            "i.areaTerreno, " +
            "c.dataInicio, " +
            "c.dataFinal, " +
            "sd.id, " +
            "sd.descricao, " +
            "i.codigoClassificacaoImovel, " +
            "d.ativa, " +
            "d.dataDestinacaoHistorico, " +
            "d.versaoDestinacao, " +
            "d.dataInicioFundamento, " +
            "d.dataFinalFundamento " +
            "FROM Destinacao d " +
            "JOIN d.statusDestinacao sd " +
            "LEFT JOIN d.contrato c " +
            "LEFT JOIN d.utilizacao u " +
            "LEFT JOIN u.tipoUtilizacao tu " +
            "LEFT JOIN u.subTipoUtilizacao stu " +
            "LEFT JOIN d.dadosResponsavel dr " +
            "LEFT JOIN dr.responsaveis r " +
            "JOIN d.tipoDestinacao td " +
            "JOIN d.destinacaoImoveis de " +
            "JOIN de.parcelas p " +
            "JOIN de.imovel i " +
            "LEFT JOIN p.benfeitorias b " +
            "JOIN i.endereco e " +

            "WHERE d.id = :idDestinacao " +
            "AND b.ativa = true " +
            "AND d.versaoDestinacao = :idVersao " +
            "GROUP BY d.id, " +
            "i.rip, " +
            "de.codigoUtilizacao, " +
            "p.sequencial, " +
            "td.id, " +
            "td.descricao, " +
            "tu.id, " +
            "tu.descricao, " +
            "stu.id, " +
            "stu.descricao, " +
            "de.fracaoIdeal, " +
            "r.nome, " +
            "r.cpfCnpj, " +
            "e.cep, " +
            "e.tipoLogradouro, " +
            "e.tipoLogradouro, " +
            "e.logradouro, " +
            "e.numero, " +
            "e.complemento, " +
            "e.municipio, " +
            "e.bairro, " +
            "e.uf, " +
            "e.pais, " +
            "e.cidadeExterior, " +
            "i.areaTerreno, " +
            "c.dataInicio, " +
            "c.dataFinal, " +
            "sd.id, " +
            "sd.descricao, " +
            "i.codigoClassificacaoImovel, " +
            "d.ativa, " +
            "d.dataDestinacaoHistorico, " +
            "d.versaoDestinacao, " +
            "d.dataInicioFundamento, " +
            "d.dataFinalFundamento "
    )
    List<Object> buscarHistoricoDestinacoes(@Param("idDestinacao") Long idDestinacao, @Param("idVersao") Long idVersao );



    /** TODO implementar codigo contrato e classificaçao quando for impementado essas funcionalidades.
     * Efetua a consulta de uma destinação
     * @param rip
     * @param codigoUtilizadao
     * @param codigoParcela
     * @param pais
     * @param cep
     * @param uf
     * @param municipio
     * @param cidadeExterior
     * @param idTiposDestinacao
     * @return
     */
    @Query("SELECT DISTINCT d.id, " +
            "i, " +
            "de.codigoUtilizacao, " +
            "p.sequencial, " +
            "td.id, " +
            "td.descricao, " +
            "tu.id, " +
            "tu.descricao, " +
            "stu.id, " +
            "stu.descricao, " +
            "de.fracaoIdeal, " +
            "SUM(b.areaConstruida), " +
            "r.nome, " +
            "r.cpfCnpj, " +
            "e.cep, " +
            "e.tipoLogradouro, " +
            "e.logradouro, " +
            "e.numero, " +
            "e.complemento, " +
            "e.municipio, " +
            "e.bairro, " +
            "e.uf, " +
            "e.pais, " +
            "e.cidadeExterior, " +
            "i.areaTerreno, " +
            "c.dataInicio, " +
            "c.dataFinal, " +
            "sd.id, " +
            "sd.descricao, " +
            "i.codigoClassificacaoImovel, " +
             "d.ativa " +
            "FROM Destinacao d " +
            "JOIN d.statusDestinacao sd " +
            "LEFT JOIN d.contrato c " +
            "LEFT JOIN d.utilizacao u " +
            "LEFT JOIN u.tipoUtilizacao tu " +
            "LEFT JOIN u.subTipoUtilizacao stu " +
            "LEFT JOIN d.dadosResponsavel dr " +
            "LEFT JOIN dr.responsaveis r " +
            "JOIN d.tipoDestinacao td " +
            "JOIN d.destinacaoImoveis de " +
            "JOIN de.parcelas p " +
            "JOIN de.imovel i " +
            "LEFT JOIN p.benfeitorias b " +
            "JOIN i.endereco e " +
            "WHERE p.ativa = true " +
            "AND d.ativa = true " +
            "AND de.ultimaDestinacao = true " +
            "AND (:rip IS NULL OR i.rip LIKE %:rip%) " +
            "AND (:codigoUtilizacao IS NULL OR de.codigoUtilizacao = :codigoUtilizacao) " +
            "AND (:codigoParcela IS NULL OR p.sequencial = :codigoParcela) " +
            "AND (:pais IS NULL OR e.pais = :pais) " +
            "AND (:cep IS NULL OR e.cep = :cep) " +
            "AND (:uf IS NULL OR e.uf = :uf) " +
            "AND (:municipio IS NULL OR e.municipio = :municipio) " +
            "AND (:cidadeExterior IS NULL OR e.cidadeExterior = :cidadeExterior) " +
            "AND ((:idTiposDestinacao) IS NULL OR td.id IN (:idTiposDestinacao)) " +
            "GROUP BY d.id, " +
            "i, " +
            "de.codigoUtilizacao, " +
            "p.sequencial, " +
            "td.id, " +
            "td.descricao, " +
            "tu.id, " +
            "tu.descricao, " +
            "stu.id, " +
            "stu.descricao, " +
            "de.fracaoIdeal, " +
            "r.nome, " +
            "r.cpfCnpj, " +
            "e.cep, " +
            "e.tipoLogradouro, " +
            "e.tipoLogradouro, " +
            "e.logradouro, " +
            "e.numero, " +
            "e.complemento, " +
            "e.municipio, " +
            "e.bairro, " +
            "e.uf, " +
            "e.pais, " +
            "e.cidadeExterior, " +
            "i.areaTerreno, " +
            "c.dataInicio, " +
            "c.dataFinal, " +
            "sd.id, " +
            "sd.descricao, " +
            "i.codigoClassificacaoImovel, " +
             "d.ativa " +
            "ORDER BY d.id"
    )
    Page<Object> consultarUtilizacao(@Param(RIP) String rip ,
                           @Param("codigoUtilizacao") String codigoUtilizadao,
                           @Param("codigoParcela") String codigoParcela,
                           @Param("pais") String pais,
                           @Param("cep") String cep,
                           @Param("uf") String uf,
                           @Param("municipio") String municipio,
                           @Param("cidadeExterior") String cidadeExterior,
                           @Param("idTiposDestinacao") List<Integer> idTiposDestinacao,
                           Pageable pageable
    );



    @Query("SELECT DISTINCT e.cidadeExterior " +
            "FROM Destinacao d " +
            "INNER JOIN d.destinacaoImoveis de " +
            "INNER JOIN de.imovel i " +
            "INNER JOIN i.endereco e " +
            "INNER JOIN de.parcelas p " +
            "WHERE p.ativa = true " +
            "AND i.rip = :rip " +
            "AND de.codigoUtilizacao = :codigoUtilizacao " +
            "AND e.pais = :pais " +
            "AND p.sequencial = :codigoParcela")
    List<String> consultarCidades(@Param("pais") String pais,
                                  @Param(RIP) String rip,
                                  @Param("codigoUtilizacao") String codigoUtilizacao,
                                  @Param("codigoParcela") String codigoParcela);


    @Query(" SELECT di FROM DestinacaoImovel di " +
            " JOIN di.destinacao d " +
            " JOIN di.imovel i " +
            " JOIN d.tipoDestinacao td " +
            " LEFT JOIN d.utilizacao u" +
            " LEFT JOIN d.dadosResponsavel r " +
            " LEFT JOIN d.contrato c " +
            " WHERE i.rip = :rip " +
                " AND td.descricao <> :tipoDestinacao ")
    List<DestinacaoImovel> buscarDadosDestinacaoAvaliacao(@Param(RIP) String rip,
                                                          @Param("tipoDestinacao") String tipoDestinacao);

    @Query(" SELECT d FROM Destinacao d " +
            " JOIN d.destinacaoImoveis di " +
            " JOIN di.imovel i " +
            " WHERE i.rip = :rip " +
            "AND d.versaoDestinacao <> 0"
    )
    List<Destinacao>  buscarListaVersoesDestinacoes(@Param(RIP) String rip);

    @Query("SELECT d FROM Destinacao d " +
        "JOIN d.tipoDestinacao td " +
        "WHERE td.id = :idTipoDestinacao AND d.cancelamentosEncerramentos IS NOT NULL")
    List<Destinacao> consultarComCancelamento(@Param("idTipoDestinacao") Integer idTipoDestinacao);

}

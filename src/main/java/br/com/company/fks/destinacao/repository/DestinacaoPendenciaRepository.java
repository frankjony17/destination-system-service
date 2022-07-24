package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.dto.DestinacaoPendenciaDTO;
import br.com.company.fks.destinacao.dominio.dto.PendenciaDTO;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoPendencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface DestinacaoPendenciaRepository extends JpaRepository<DestinacaoPendencia, Long> {

    @Query("SELECT DISTINCT " +
            " new br.com.company.fks.destinacao.dominio.dto.PendenciaDTO(d.id, p.descricao, dp.dataGerada) " +
            " FROM DestinacaoPendencia dp " +
            " JOIN dp.destinacaoPendenciaID dpID " +
            " JOIN dpID.pendencia p " +
            " JOIN dpID.destinacao d " +
            " JOIN d.destinacaoImoveis di " +
            " JOIN di.imovel i " +
            " JOIN i.endereco e " +
            " JOIN p.permissoes per " +
            " WHERE per.descricao IN(:permissoes) " +
            " AND e.uf IN(:ufs) ")
    Set<PendenciaDTO> buscarPendenciasUsuarioLogado(@Param("permissoes") Set<String> permissoes, @Param("ufs") Set<String> ufs);

    @Query("SELECT " +
            " new br.com.company.fks.destinacao.dominio.dto.PendenciaDTO(p.id, d.id, p.descricao, p.modulo, dp.dataGerada) " +
            " FROM DestinacaoPendencia dp " +
            " JOIN dp.destinacaoPendenciaID dpID " +
            " JOIN dpID.pendencia p " +
            " JOIN dpID.destinacao d " +
            " JOIN d.destinacaoImoveis di " +
            " JOIN di.imovel i " +
            " JOIN i.endereco e " +
            " WHERE d.id = :idDestinacao ")
    Set<PendenciaDTO> buscarPendenciasPorIdDestinacao(@Param("idDestinacao") Long idDestinacao);

    @Query("SELECT " +
            " new br.com.company.fks.destinacao.dominio.dto.DestinacaoPendenciaDTO(d.id, p.id, p.descricao, p.modulo, dp.dataGerada) " +
            " FROM DestinacaoPendencia dp " +
            " JOIN dp.destinacaoPendenciaID dpID " +
            " JOIN dpID.pendencia p " +
            " JOIN dpID.destinacao d " +
            " JOIN d.destinacaoImoveis di " +
            " JOIN di.imovel i " +
            " JOIN i.endereco e " +
            " WHERE d.id = :idDestinacao " + 
            " AND p.id = :idPendencia ")
    DestinacaoPendenciaDTO buscarDestinacaoPendenciaPorId(@Param("idDestinacao") Long idDestinacao, @Param("idPendencia") Long idPendencia);



}

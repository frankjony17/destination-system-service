package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.SubTipoUtilizacao;
import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by haillanderson on 07/04/17.
 */

@Repository
public interface SubTipoUtilizacaoRepository extends JpaRepository<SubTipoUtilizacao, Long>{

    /**
     * Busca todos os SubTipos da Utilização ativos
     * @return
     */
    @Query("SELECT DISTINCT s FROM SubTipoUtilizacao s WHERE s.ativo = true ORDER BY s.descricao ASC")
    List<SubTipoUtilizacao> findAllAtivos();

    @Query("SELECT DISTINCT s FROM SubTipoUtilizacao s WHERE s.ativo = true AND s.tipoUtilizacao.id = :tipoUtilizacao")
    List<SubTipoUtilizacao> findAllByTipoUtilizacao(@Param("tipoUtilizacao")Integer idTipoUtilizacao);

}

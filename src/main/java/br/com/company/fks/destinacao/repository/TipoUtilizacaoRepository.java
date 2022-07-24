package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.TipoUtilizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by haillanderson on 07/04/17.
 */

@Repository
public interface TipoUtilizacaoRepository extends JpaRepository<TipoUtilizacao, Integer>{

    /**
     * Busca todos os Tipos de Utilização ativos
     * @return
     */
    @Query("SELECT DISTINCT t FROM TipoUtilizacao t WHERE t.ativo = true ORDER BY t.descricao ASC")
    List<TipoUtilizacao> findAllAtivos();

}

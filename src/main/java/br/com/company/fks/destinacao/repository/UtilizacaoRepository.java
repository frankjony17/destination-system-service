package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Utilizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface UtilizacaoRepository extends JpaRepository<Utilizacao, Long> {

    @Query("SELECT DISTINCT u.especificacao FROM Utilizacao u WHERE u.tipoUtilizacao.id =:idTipoUtilizacao AND u.especificacao <> null")
    List<String> buscarEspecificacoes (@Param("idTipoUtilizacao")Integer idTipoUtilizacao);

    @Query("SELECT u FROM Utilizacao u JOIN u.tipoUtilizacao t WHERE t.id = 1")
    Utilizacao selecionaSemUsoVago();
}

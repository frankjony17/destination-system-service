package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.SemUtilizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by diego on 01/03/17.
 */
@Repository
public interface SemUtilizacaoRepository extends JpaRepository<SemUtilizacao, Long> {


    /**
     * Conta a quantidade de destinações ativas para o imovel informado.
     * @return Retorna a quantidade de destinações ativas para o imovel informado.
     */
    @Query("SELECT COUNT(*) FROM DestinacaoImovel di " +
            " JOIN di.imovel i " +
            " JOIN di.destinacao d " +
            " JOIN d.statusDestinacao sd " +
            " JOIN d.tipoDestinacao td " +
            " WHERE i.rip =:rip" +
            " AND sd.id = 1 " +
            " AND td.id <> 12")
    Long getQuantidadeDestinacoesImovel(@Param("rip") String rip);

    /**
     * Recupera destinacao sem utilização pelo id do imovel cadastrado no cadastro de imoveis.
     * @param rip
     * @return SemUtilizacao
     */
    @Query("SELECT d FROM DestinacaoImovel di " +
            " JOIN di.imovel i " +
            " JOIN i.endereco e " +
            " JOIN i.parcelas " +
            " JOIN di.destinacao d " +
            " JOIN d.tipoDestinacao td " +
            " JOIN d.statusDestinacao sd " +
            " WHERE i.rip =:rip " +
            " AND td.id = 12 " +
            " AND sd.id = 1 ")
    SemUtilizacao buscarDestinacaoRipImovel(@Param("rip") String rip);
}

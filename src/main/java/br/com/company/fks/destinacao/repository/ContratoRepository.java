package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    @Query(value = "SELECT contrato.* FROM " +
            "destinacao.tb_contrato AS contrato " +
            "left join destinacao.tb_doacao AS doacao on (contrato.id_contrato = doacao.co_contrato) " +
            "left join destinacao.tb_venda AS venda on (contrato.id_contrato = venda.co_contrato) " +
            "left join destinacao.tb_cuem AS cuem on (contrato.id_contrato = cuem.co_contrato) " +
            "left join destinacao.tb_cdru AS cdru on (contrato.id_contrato = cdru.co_contrato) " +
            "left join destinacao.tb_cessao_gratuita AS cessaoGratuita on (contrato.id_contrato = cessaoGratuita.co_contrato) " +
            "left join destinacao.tb_termo_entrega AS termoEntrega on (contrato.id_contrato = termoEntrega.co_contrato) " +
            "WHERE venda.id_venda =:idDestinacao OR doacao.id_doacao =:idDestinacao " +
            "OR cuem.id_cuem =:idDestinacao OR cdru.id_cdru =:idDestinacao " +
            "OR cessaoGratuita.id_cessao_gratuita =:idDestinacao OR termoEntrega.id_termo_entrega =:idDestinacao",
            nativeQuery = true)
    Contrato buscarContratoPorIdDestinacao(@Param("idDestinacao") Long idDestinacao);
}

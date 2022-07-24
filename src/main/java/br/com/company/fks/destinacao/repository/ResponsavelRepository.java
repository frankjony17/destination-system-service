package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Basis Tecnologia on 17/10/2016.
 */
@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    @Query(value = "select td.ds_tipo_destinacao, c.dt_inicio, c.dt_final, di.nu_codigo_utilizacao, i.nu_rip, p.ds_sequencial" +
            " from destinacao.tb_dados_responsavel as dr" +
            " join destinacao.tb_responsavel as r on dr.id_dados_responsavel = r.co_dados_responsavel" +
            " join destinacao.tb_destinacao as d on d.co_dados_responsavel = dr.id_dados_responsavel" +
            " join destinacao.tb_tipo_destinacao as td on td.id_tipo_destinacao = d.co_tipo_destinacao" +
            " join destinacao.tb_contrato as c on c.id_contrato = d.co_contrato" +
            " join destinacao.tb_destinacao_imovel as di on di.co_destinacao = d.id_destinacao" +
            " join destinacao.tb_imovel as i on i.id_imovel = di.co_imovel" +
            " join destinacao.tb_destinacao_imovel_parcela as dip on dip.id_destinacao_imovel = di.id_destinacao_imovel" +
            " join destinacao.tb_parcela as  p on p.id_parcela = dip.id_parcela" +
            " where r.ds_cpf_cnpj = :cpf", nativeQuery = true)
    List<Object> buscarDestinacaoesResponsavel(@Param("cpf") String cpf);

}

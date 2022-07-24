package br.com.company.fks.destinacao.repository;

import br.com.company.fks.destinacao.dominio.entidades.Despacho;
import br.com.company.fks.destinacao.dominio.enums.TipoDespachoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Basis Tecnologia on 11/10/2016.
 */
@Repository
public interface DespachoRepository extends JpaRepository<Despacho, Long> {

    /**
     *
     * @param tipoDespacho Traz todos os despachos atrelados a um tipo de despacho
     * @return
     */
    @Query("SELECT d FROM Despacho d WHERE d.tipoDespacho = :tipoDespacho")
    List<Despacho> buscarPorTipoDespacho(@Param(value = "tipoDespacho") TipoDespachoEnum tipoDespacho);

}

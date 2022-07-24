package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.FamiliaBeneficiada;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.repository.FamiliaBeneficiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por salvar Família Beneficiada
 * Created by diego on 10/11/16.
 */
@Service
public class FamiliaBeneficiadaService {

    @Autowired
    private FamiliaBeneficiadaRepository familiaBeneficiadaRepository;

    /**
     * Salva família beneficiada no banco de dados
     * @param familiaBeneficiada
     * @return FamiliaBeneficiada gravada no banco de dados
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public FamiliaBeneficiada salvar(FamiliaBeneficiada familiaBeneficiada) {
        return familiaBeneficiadaRepository.save(familiaBeneficiada);
    }

    /**
     * Salva responsavel nas famílias responsáveis salvas
     * @param familiasBeneficiadas
     * @param responsavel
     * @return List<FamiliaBeneficiada> lista com o responsável inserido na lista de famílias beneficiadas
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<FamiliaBeneficiada> salvar (List<FamiliaBeneficiada> familiasBeneficiadas, Responsavel responsavel) {
        List<FamiliaBeneficiada> familiasBeneficiadasSalvas = new ArrayList<>();
        for (FamiliaBeneficiada familiaBeneficiada : familiasBeneficiadas) {
            familiaBeneficiada.setResponsavel(responsavel);
            familiasBeneficiadasSalvas.add(salvar(familiaBeneficiada));
        }
        return familiasBeneficiadasSalvas;
    }

}

package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Cuem;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.TipoModalidade;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by diego on 10/11/16.
 */

/**
 * Classe para fazer as validações específicas de CUEM
 */
@Service
public class ValidadorCuem extends ValidadorDestinacao<Cuem> {

    private static final long TIPO_MODALIDADE_COLETIVO = 2;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    /**
     *
     * @param cuem Recebe o objeto de CUEM para verifcar os dados específicos
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(Cuem cuem) throws NegocioException {
        List<String> erros = new ArrayList<>();

        validarCampoTipoModalidadeVazio(cuem.getTipoModalidade(), erros);

        validarCamposObrigatorioContrato(cuem, erros);
        
        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }

    }

    /**
     *
     * @param tipoModalidade recebe o valor de tipo modalidade e verifica se não está null
     * @param erros
     */
    public void validarCampoTipoModalidadeVazio (TipoModalidade tipoModalidade, List<String> erros) {
        Boolean tipoModalidadePreenchido = isTipoModalidadePreenchido(tipoModalidade);
        if (!tipoModalidadePreenchido) {
            erros.add(mensagemNegocio.get("tipo.modalidade.vazio"));
        }
    }

    private Boolean isTipoModalidadePreenchido(TipoModalidade tipoModalidade) {
        Optional<TipoModalidade> optional = Optional.ofNullable(tipoModalidade);
        return optional.map(map -> map.getId() != null).orElse(false);
    }

    /**
     *
     * @param responsaveis Recebe uma lista de responsaveis
     * @param tipoModalidade recebe o valor de tipo modalidade
     * @param erros recebe uma lista de erros para ser populado e printado
     */
    public void validarBeneficiario (List<Responsavel> responsaveis, TipoModalidade tipoModalidade, List<String> erros) {
        Optional<List<Responsavel>> optional = Optional.ofNullable(responsaveis);
        Boolean listaPreenchida = optional.map(lista -> !lista.isEmpty()).orElse(false);

        if (listaPreenchida) {
            for (Responsavel responsavel : responsaveis) {
                validarExisteMaisUmBeneficiarioTipoIndividual(responsavel, tipoModalidade, erros);
            }
        } else {
            erros.add(mensagemNegocio.get("resposavel.vazio"));
        }

    }

    /**
     *
     * @param responsavel Recebe um responsavel e não pode existir mais de uma beneficiario
     * @param tipoModalidade recebe o valor de tipo modalidade
     * @param erros recebe uma lista de erros para ser populado e prontado
     */
    private void validarExisteMaisUmBeneficiarioTipoIndividual(Responsavel responsavel, TipoModalidade tipoModalidade, List<String> erros) {
        Boolean tipoPreenchido = isTipoModalidadePreenchido(tipoModalidade);
        if (tipoPreenchido) {
            verificarExisteMaisUmBeneficiarioTipoIndividual(responsavel, tipoModalidade, erros);
        }

    }

    private void verificarExisteMaisUmBeneficiarioTipoIndividual(Responsavel responsavel, TipoModalidade tipoModalidade, List<String> erros) {
        if (tipoModalidade.getId().intValue() != TIPO_MODALIDADE_COLETIVO
                && isResponsavelPessoaJuridicaPossuiFamiliasBeneficiadas(responsavel)) {
            erros.add(mensagemNegocio.get("responsavel.ind.familia.mais.um.benef"));
        }
    }

    private Boolean isResponsavelPessoaJuridicaPossuiFamiliasBeneficiadas(Responsavel responsavel) {
        return responsavel.getCpfCnpj().length() == QTD_DIGITOS_CNPJ
                && responsavel.getFamiliasBeneficiadas().size() > Constants.UM;
    }

}

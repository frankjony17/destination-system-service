package br.com.company.fks.destinacao.service.validadores.impl;

import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.SemUtilizacao;
import br.com.company.fks.destinacao.dominio.enums.TipoImovelEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.validadores.ValidadorDestinacao;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by diego on 08/03/17.
 */
@Service
public class ValidadorSemUtilizacao extends ValidadorDestinacao<SemUtilizacao> {

    @Autowired
    private MensagemNegocio mensagemNegocio;

    /**
     * Valida se os dados da destinação sem utilização estão corretos.
     * @param semUtilizacao
     * @throws NegocioException
     */
    @Override
    public void validadorEspecifico(SemUtilizacao semUtilizacao) throws NegocioException {
        List<String> erros = new ArrayList<>();

        validarImovel(semUtilizacao.getDestinacaoImoveis(), erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }

    }

    private void validarImovel(List<DestinacaoImovel> destinacaoImoveis, List<String> erros) {
        destinacaoImoveis.forEach(destinacaoImovel -> {
            Imovel imovel = destinacaoImovel.getImovel();
            validarImovel(erros, imovel);
        });
    }

    private void validarImovel(List<String> erros, Imovel imovel) {
        if (imovel != null) {
            validarTipoImovel(imovel, erros);
            validarEnderecoImovelVazio(imovel.getEndereco(), erros);
        }
    }

    private void validarEnderecoImovelVazio(Endereco endereco, List<String> erros) {
        if (endereco == null) {
            erros.add(mensagemNegocio.get("endereco.vazio"));
        }
    }

    private void validarTipoImovel(Imovel imovel, List<String> erros) {

        TipoImovelEnum tipoImovelEnum = imovel.getIndicadorUnidadeBenfeitoria();
        if (tipoImovelEnum == TipoImovelEnum.BENFEITORIA) {
            verificarTipoImovelBenfeitoria(imovel, erros);
        } else {
            verificarExisteUnidadeAutonoma(imovel, erros);
        }

    }

    private void verificarTipoImovelBenfeitoria(Imovel imovel, List<String> erros) {

        if (isPossuiUnidadeAutonoma(imovel)) {
            erros.add(mensagemNegocio.get("tipo.imovel.invalido"));
        }

    }

    private Boolean isPossuiBenfeitoria(Imovel imovel) {
        Optional<List<Benfeitoria>> optional = Optional.ofNullable(imovel.getBenfeitorias());
        return optional.map(map -> !map.isEmpty()).orElse(false);
    }

    private Boolean isPossuiUnidadeAutonoma(Imovel imovel) {
        return imovel.getUnidadeAutonoma() != null;
    }

    private void verificarExisteUnidadeAutonoma(Imovel imovel, List<String> erros) {
        if (isPossuiBenfeitoria(imovel) || !isPossuiUnidadeAutonoma(imovel)) {
            erros.add(mensagemNegocio.get("tipo.imovel.invalido"));
        }
    }

}

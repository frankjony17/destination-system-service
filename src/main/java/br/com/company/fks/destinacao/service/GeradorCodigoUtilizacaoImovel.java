package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * Classe responsável por gerar código de utilização da destinação
 * Created by diego on 01/02/17.
 */
@Service
public class GeradorCodigoUtilizacaoImovel {

    private static final String VALOR_PREENCHIMENTO = "0";
    private static final Integer QUATRO_DIGITOS = 4;

    @Autowired
    private DestinacaoImovelService destinacaoImovelService;

    /**
     * Gera código de utilização para destinações informadas
     * @param destinacoesImoveis
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void gerarCodigoUtilizacao (List<DestinacaoImovel> destinacoesImoveis) {

        Map<Long, Integer> mapaCodigoUtilizacao = new HashMap<>();

        destinacoesImoveis.forEach(destinacaoImovel -> {
            if (destinacaoImovel.getId() == null) {
                Imovel imovel = destinacaoImovel.getImovel();
                if (!mapaCodigoUtilizacao.containsKey(imovel.getIdCadastroImovel())) {
                    String codigoUtilizacao = destinacaoImovelService.getUltimaUtilizadaoImovel(imovel.getIdCadastroImovel());
                    setarCodigoUtilizacao(destinacaoImovel, mapaCodigoUtilizacao, Integer.valueOf(codigoUtilizacao));
                } else {
                    Integer sequencial = mapaCodigoUtilizacao.get(imovel.getIdCadastroImovel());
                    setarCodigoUtilizacao(destinacaoImovel, mapaCodigoUtilizacao, sequencial);
                }
            }
        });

    }

    private void setarCodigoUtilizacao(DestinacaoImovel destinacaoImovel, Map<Long, Integer> mapaCodigoUtilizacao, Integer sequencial) {
        Integer novoSequencial = sequencial + 1;
        Imovel imovel = destinacaoImovel.getImovel();
        mapaCodigoUtilizacao.put(imovel.getIdCadastroImovel(), novoSequencial);
        destinacaoImovel.setCodigoUtilizacao(formartarCodigoUtilizacao(novoSequencial));
    }

    private String formartarCodigoUtilizacao(Integer sequencial) {
        return String.format("%S", leftPad(sequencial.toString(), QUATRO_DIGITOS, VALOR_PREENCHIMENTO));
    }

}

package br.com.company.fks.destinacao.service.validadores;

import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorCdru;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorCessaoGratuita;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorCessaoOnerosa;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorCuem;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorDoacao;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorPermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorPosseInformal;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorSemUtilizacao;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorTermoEntrega;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorTransferencia;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorUsoProprio;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorVenda;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */

/**
 * Classe para enviar a destinação para o validador correspondente
 */
@Component
public class ValidadorStrategy {

    private static final Map<TipoDestinacaoEnum, Class> beanMap = new HashMap<>();
    static {
        beanMap.put(TipoDestinacaoEnum.DOACAO, ValidadorDoacao.class);
        beanMap.put(TipoDestinacaoEnum.VENDA, ValidadorVenda.class);
        beanMap.put(TipoDestinacaoEnum.POSSE_INFORMAL, ValidadorPosseInformal.class);
        beanMap.put(TipoDestinacaoEnum.CUEM, ValidadorCuem.class);
        beanMap.put(TipoDestinacaoEnum.CDRU, ValidadorCdru.class);
        beanMap.put(TipoDestinacaoEnum.TERMO_ENTREGA, ValidadorTermoEntrega.class);
        beanMap.put(TipoDestinacaoEnum.CESSAO_GRATUITA, ValidadorCessaoGratuita.class);
        beanMap.put(TipoDestinacaoEnum.SEM_UTILIZACAO, ValidadorSemUtilizacao.class);
        beanMap.put(TipoDestinacaoEnum.USO_PROPRIO, ValidadorUsoProprio.class);
        beanMap.put(TipoDestinacaoEnum.PERMISSAO_USO_IMOVEL_FUNCIONAL, ValidadorPermissaoUsoImovelFuncional.class);
        beanMap.put(TipoDestinacaoEnum.CESSAO_ONEROSA, ValidadorCessaoOnerosa.class);
        beanMap.put(TipoDestinacaoEnum.TRANSFERENCIA, ValidadorTransferencia.class);


    }

    /**
     *
     * @param tipoDestinacaoEnum Recebe o tipo de destinação e cria o respectivo bean
     * @return
     */
    public ValidadorDestinacao createBean(TipoDestinacaoEnum tipoDestinacaoEnum) {
        Class clazz = beanMap.get(tipoDestinacaoEnum);
        return (ValidadorDestinacao) SpringContext.getBean(clazz);
    }
}

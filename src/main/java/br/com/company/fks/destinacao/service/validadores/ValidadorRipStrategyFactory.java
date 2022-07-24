package br.com.company.fks.destinacao.service.validadores;

import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipCategoriaCessao;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipCuem;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipGenerico;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipLocacao;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipPermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipTermoEntrega;
import br.com.company.fks.destinacao.service.validadores.impl.ValidadorRipUsoProprio;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by diego on 26/05/17.
 */
@Component
public class ValidadorRipStrategyFactory {

    private static final Map<TipoDestinacaoEnum, Class> mapaBean = new HashMap<>();
    static {
        mapaBean.put(TipoDestinacaoEnum.DOACAO, ValidadorRipGenerico.class);
        mapaBean.put(TipoDestinacaoEnum.VENDA, ValidadorRipGenerico.class);
        mapaBean.put(TipoDestinacaoEnum.CDRU, ValidadorRipGenerico.class);
        mapaBean.put(TipoDestinacaoEnum.CUEM, ValidadorRipCuem.class);
        mapaBean.put(TipoDestinacaoEnum.TERMO_ENTREGA, ValidadorRipTermoEntrega.class);
        mapaBean.put(TipoDestinacaoEnum.CESSAO_GRATUITA, ValidadorRipCategoriaCessao.class);
        mapaBean.put(TipoDestinacaoEnum.LOCACAO, ValidadorRipGenerico.class);
        mapaBean.put(TipoDestinacaoEnum.USO_PROPRIO, ValidadorRipUsoProprio.class);
        mapaBean.put(TipoDestinacaoEnum.PERMISSAO_USO_IMOVEL_FUNCIONAL, ValidadorRipPermissaoUsoImovelFuncional.class);
        mapaBean.put(TipoDestinacaoEnum.TRANSFERENCIA, ValidadorRipGenerico.class);
        mapaBean.put(TipoDestinacaoEnum.CESSAO_ONEROSA, ValidadorRipCategoriaCessao.class);
    }

    /**
     *
     * @param tipoDestinacaoEnum Recebe o tipo de destinação e cria o respectivo bean
     * @return
     */
    public ValidadorRip createBean(TipoDestinacaoEnum tipoDestinacaoEnum) {
        Class clazz = mapaBean.get(tipoDestinacaoEnum);
        return (ValidadorRip) SpringContext.getBean(clazz);
    }

}

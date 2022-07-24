package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.service.CessaoOnerosaService;
import br.com.company.fks.destinacao.service.TransferenciaTitularidadeService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsavel por definir o service da destinação
 * Created by Basis Tecnologia on 10/10/2016.
 */
@Component
public class DestinacaoStrategy {

    private static final Map<TipoDestinacaoEnum, Class> beanMap = new HashMap<>();
    static {
        beanMap.put(TipoDestinacaoEnum.DOACAO, DoacaoService.class);
        beanMap.put(TipoDestinacaoEnum.VENDA, VendaService.class);
        beanMap.put(TipoDestinacaoEnum.POSSE_INFORMAL, PosseInformalService.class);
        beanMap.put(TipoDestinacaoEnum.CUEM, CuemService.class);
        beanMap.put(TipoDestinacaoEnum.CDRU, CdruService.class);
        beanMap.put(TipoDestinacaoEnum.TERMO_ENTREGA, TermoEntregaService.class);
        beanMap.put(TipoDestinacaoEnum.CESSAO_GRATUITA, CessaoGratuitaService.class);
        beanMap.put(TipoDestinacaoEnum.USO_PROPRIO, UsoProprioService.class);
        beanMap.put(TipoDestinacaoEnum.SEM_UTILIZACAO, SemUtilizacaoService.class);
        beanMap.put(TipoDestinacaoEnum.PERMISSAO_USO_IMOVEL_FUNCIONAL, PermissaoUsoImovelFuncionalService.class);
        beanMap.put(TipoDestinacaoEnum.CESSAO_ONEROSA, CessaoOnerosaService.class);
        beanMap.put(TipoDestinacaoEnum.TRANSFERENCIA, TransferenciaTitularidadeService.class);
}

    /**
     * Define qual service para cada tipo de destinação
     * @param tipoDestinacaoEnum
     * @return DestinacaoService para cada tipo de destinação
     */
    public DestinacaoService createBean(TipoDestinacaoEnum tipoDestinacaoEnum) {
        Class <? extends DestinacaoService> clazz = beanMap.get(tipoDestinacaoEnum);
        return SpringContext.getBean(clazz);
    }
}

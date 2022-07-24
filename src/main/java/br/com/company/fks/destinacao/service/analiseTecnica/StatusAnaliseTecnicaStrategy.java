package br.com.company.fks.destinacao.service.analiseTecnica;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.configuracao.SpringContext;
import br.com.company.fks.destinacao.dominio.enums.PermissaoAnaliseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por realizar buscas em despachos
 * Created by raphael on 09/12/16.
 */
public final class StatusAnaliseTecnicaStrategy {


    private static final Map<PermissaoAnaliseEnum, Class> beanMap = new HashMap<>();
    static{
        beanMap.put(PermissaoAnaliseEnum.EXEC_ANALISE_SUPERINTENDENTE, StatusAnaliseDespachoSuperintendente.class);
        beanMap.put(PermissaoAnaliseEnum.EXEC_ANALISE_SECRETARIO, StatusAnaliseDespachoSecretario.class);
        beanMap.put(PermissaoAnaliseEnum.EXEC_ANALISE_CHEFIA, StatusAnaliseDespachoChefia.class);
    }

    private StatusAnaliseTecnicaStrategy() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Busca status da análise por despacho e permissão do usuário usando usuarioLogadoDTO como parâmetro
     * @param usuarioLogadoDTO
     * @return StatusAnaliseDespacho status da análise encontrado pelo despacho e permissão do usuário
     */
    public static StatusAnaliseDespacho buscarStatusAnalisePorDespachoPorPermisao(UsuarioLogadoDTO usuarioLogadoDTO){
        PermissaoAnaliseEnum permissaoAnaliseEnum =
                PermissaoAnaliseEnum.getPermissaoAnaliseDescricao(usuarioLogadoDTO.getPermissoes());
        return buscarStatusAnalisePorDespachoPorPermisao(permissaoAnaliseEnum);
    }

    /**
     * Busca status da análise por Despacho e Permissão do usuário usando PermissaoAnaliseEnum como parâmentro
     * @return StatusAnaliseDespacho encontrado
     */
    public static StatusAnaliseDespacho buscarStatusAnalisePorDespachoPorPermisao(PermissaoAnaliseEnum permissaoAnaliseEnum){
        Class<? extends StatusAnaliseDespacho> clazz = beanMap.get(permissaoAnaliseEnum);
        return SpringContext.getBean(clazz);
    }


}

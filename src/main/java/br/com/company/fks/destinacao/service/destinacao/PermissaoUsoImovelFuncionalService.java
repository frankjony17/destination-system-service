package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.builder.RespostaBuilder;
import br.com.company.fks.destinacao.dominio.dto.PermissaoUsoImovelFuncionalDTO;
import br.com.company.fks.destinacao.dominio.entidades.PermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.exceptions.IntegracaoException;
import br.com.company.fks.destinacao.repository.PermissaoUsoImovelFuncionalRepository;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class PermissaoUsoImovelFuncionalService extends DestinacaoService<PermissaoUsoImovelFuncional> {

    @Autowired
    private PermissaoUsoImovelFuncionalRepository permissaoUsoImovelFuncionalRepository;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    @Autowired
    private EntityConverter entityConverter;


    /**
     * Salva Permissao de uso de Imovel Funcional
     * @param permissaoUsoImovelFuncional
     * @return
     */

    @Override
    public PermissaoUsoImovelFuncional salvarDadosEspecificos (PermissaoUsoImovelFuncional permissaoUsoImovelFuncional){
        permissaoUsoImovelFuncional.setDadosResponsavel(dadosResponsavelService.salvar(permissaoUsoImovelFuncional.getDadosResponsavel(), false));
        return permissaoUsoImovelFuncionalRepository.save(permissaoUsoImovelFuncional);

    }

    @Override
    public Resposta<PermissaoUsoImovelFuncionalDTO> findOne(Long id) throws IntegracaoException, IOException {
        PermissaoUsoImovelFuncionalDTO permissaoUsoImovelFuncionalDTO = entityConverter.converterStrict(permissaoUsoImovelFuncionalRepository.findOne(id), PermissaoUsoImovelFuncionalDTO.class);
        montarDadosMapa(permissaoUsoImovelFuncionalDTO.getDestinacaoImoveis());
        return RespostaBuilder.getBuilder().setResultado(permissaoUsoImovelFuncionalDTO).build();
    }

}

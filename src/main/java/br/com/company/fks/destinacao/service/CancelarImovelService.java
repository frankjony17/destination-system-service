package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.CancelamentoImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.EnderecoDTO;
import br.com.company.fks.destinacao.dominio.dto.ImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoCancelamentoImovelDTO;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CancelarImovelService {

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private UsuarioService usuarioService;

    private static final String JUSTIFICATIVA = "Imóvel cancelado pela destinação ";

    public void cancelarImovel(Destinacao destinacao){
        destinacao.getDestinacaoImoveis().forEach(destinacaoImovel -> {
            CancelamentoImovelDTO cancelamentoImovelDTO = criarObjeto(destinacao, destinacaoImovel);
            String url = urlIntegracaoUtils.getUrlCancelarImovel();

            requestUtils.doPostBetweenModules(url, cancelamentoImovelDTO);
        });

    }

    private CancelamentoImovelDTO criarObjeto(Destinacao destinacao, DestinacaoImovel destinacaoImovel){
        CancelamentoImovelDTO cancelamentoImovelDTO = new CancelamentoImovelDTO();
        cancelamentoImovelDTO.setDataCancelamento(new Date());
        cancelamentoImovelDTO.setJustificativa(JUSTIFICATIVA + destinacao.getTipoDestinacao().getDescricao() + "(" + destinacaoImovel.getImovel().getRip() + "/" + destinacaoImovel.getCodigoUtilizacao() + ")");
        cancelamentoImovelDTO.setNumeroProcesso(destinacao.getNumeroProcesso());
        cancelamentoImovelDTO.setUsuarioCadastrador(5L);
        cancelamentoImovelDTO.setTipoCancelamentoImovel(new TipoCancelamentoImovelDTO(7L, "Alienação", 1L,"" ));
        ImovelDTO imovel = new ImovelDTO();
        Endereco endereco = new Endereco();
        endereco.setUf(destinacaoImovel.getImovel().getEndereco().getUf());
        imovel.setId(destinacaoImovel.getImovel().getIdCadastroImovel());
        imovel.setEnderecamento(endereco);
        cancelamentoImovelDTO.setImovel(imovel);
        return cancelamentoImovelDTO;
    }
}

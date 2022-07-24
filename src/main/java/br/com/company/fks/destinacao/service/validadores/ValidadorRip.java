package br.com.company.fks.destinacao.service.validadores;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.dominio.dto.EntidadeExtintaDTO;
import br.com.company.fks.destinacao.dominio.dto.FormaDeIncorporacaoPermitidaDTO;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.dto.TipoAquisicaoPermitidaDTO;
import br.com.company.fks.destinacao.dominio.entidades.Benfeitoria;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.enums.EntidadeExtintaEnum;
import br.com.company.fks.destinacao.dominio.enums.FormaDeIncorporacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoAquisicaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.UsuarioService;
import br.com.company.fks.destinacao.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar a validação do Rip
 * Created by haillanderson on 24/03/17.
 */
@Component
public abstract class ValidadorRip {

    @Autowired
    private UsuarioService usuarioService;

    private static final Long ESPELHO_DAGUA = 3L;

    private static final Long CAVIDADE_NATURAL = 5L;

    private static final Long RURAL = 2L;

    private static final Long INCORPORADO = 1L;

    private static final Long EM_PROCESSO_DE_INCORPORACAO = 2L;

    private static final Long POR_AQUISICAO = 2L;

    private static final Long SUCESSAO_POR_ENTIDADES = 9L;

    /**
     * Método que valida o RIP
     * @param imovel
     * @throws NegocioException
     */
    public void validar(Imovel imovel, Long idModalidade, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException {
        verificarJurisdicaoUsuarioLogado(imovel);
        validadorEspecifico(imovel, idModalidade);
        validarTipoImovel(imovel, fundamentoLegalDTO);
        validarNatureza(imovel, fundamentoLegalDTO);
        validarSituacaoIncorporacao(imovel, fundamentoLegalDTO);
        validarFormaDeIncorporacao(imovel,fundamentoLegalDTO);
        verificarImovelParcelado(imovel, fundamentoLegalDTO);
    }

    public abstract void validadorEspecifico(Imovel imovel, Long idModalidade) throws NegocioException;

    protected NegocioException setaMensagemErro(String mensagem) {
        if (mensagem != null) {
            return new NegocioException(mensagem);
        }
        return new NegocioException("RIP Inválido");
    }

    protected Boolean isImovelNaoIncorporadoOuCavidadeOuEspelhoDagua(Imovel imovel){
        if(imovel.getCodigoSituacaoIncorporacao() != Constants.UM || isImovelCavidadeOuEspelhoDagua(imovel.getCodigoTipoImovel())){
            return true;
        }
        return false;
    }

    protected Boolean isImovelCavidadeOuEspelhoDagua(Long idTipoImovel){
        return idTipoImovel == Constants.CINCO || idTipoImovel == Constants.TRES;
    }

    private void verificarJurisdicaoUsuarioLogado(Imovel imovel) throws NegocioException {
        if(!verificaJurisdicaoUsuarioLogado(imovel)){
            throw setaMensagemErro("O RIP informado não está sob jurisdição da sua unidade de lotação.");
        }
    }

    private Boolean verificaJurisdicaoUsuarioLogado(Imovel imovel){
        UsuarioLogadoDTO usuarioLogadoDTO = usuarioService.getUsuarioLogado();
        Set<String> jurisdicoes = usuarioLogadoDTO.getJurisdicoes();
        return jurisdicoes.contains(imovel.getEndereco().getUf());
    }

    protected Boolean verificaAreaConstruidaCessaoEntrega(Imovel imovel) {

        if (!imovel.getBenfeitorias().isEmpty()) {

            List<Benfeitoria> benfeitoriasEdificacao = imovel.getBenfeitorias()
                    .stream()
                    .filter(benfeitoria -> benfeitoria.getCodigo().startsWith("E"))
                    .collect(Collectors.toList());

            BigDecimal totalConstruido = somarAreaDisponivel(benfeitoriasEdificacao);

            return totalConstruido.compareTo(BigDecimal.ZERO) <= Constants.ZERO;
        }

        return false;
    }

    private BigDecimal somarAreaDisponivel(List<Benfeitoria> benfeitorias) {
        BigDecimal totalConstruido = BigDecimal.ZERO;

        for (Benfeitoria benfeitoria : benfeitorias) {
            if(benfeitoria.getAreaDisponivel() != null) {
                totalConstruido = totalConstruido.add(benfeitoria.getAreaDisponivel());
            }
        }

        return totalConstruido;
    }

    protected Boolean verificaImovelDf(Imovel imovel){
        return imovel.getEndereco().getUf().equals("DF");
    }

    protected void verificarImovelParcelado(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException{
        if(imovel.getParcelas().size() > 1 && !fundamentoLegalDTO.getIsPermitirImovelParcelado()){
            throw new NegocioException("O imóvel informado não pode estar parcelado.");
        }

    }


    private void validarTipoImovel(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException{
        if(imovel.getCodigoTipoImovel().equals(ESPELHO_DAGUA)){
            if(fundamentoLegalDTO.getIsPermitirImoveisEspelhoDAgua() == null || !fundamentoLegalDTO.getIsPermitirImoveisEspelhoDAgua() ){
                throw new NegocioException("O imóvel/parcela informado não pode ser do tipo Espelho D'água");
            }
        } else{
            if(imovel.getCodigoTipoImovel().equals(CAVIDADE_NATURAL) && isNaoPermitido(fundamentoLegalDTO)){
                throw new NegocioException("O imóvel/parcela informado não pode ser do tipo Cavidade Natural Subterrânea");
            }
        }
    }

    private static Boolean isNaoPermitido(FundamentoLegalDTO fundamentoLegalDTO){
        return fundamentoLegalDTO.getIsPermitirImoveisCavidadeNatural() == null || !fundamentoLegalDTO.getIsPermitirImoveisCavidadeNatural();
    }

    private void validarNatureza(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException{
        if(imovel.getCodigoNaturezaImovel().equals(RURAL)){
            if(fundamentoLegalDTO.getIsNaturezaRural() == null || !fundamentoLegalDTO.getIsNaturezaRural()) {
                throw new NegocioException("O imóvel/parcela informado não pode ser de natureza Rural");
            }
        }else {
            if (fundamentoLegalDTO.getIsNaturezaUrbana() == null || !fundamentoLegalDTO.getIsNaturezaUrbana()){
                throw new NegocioException("O imóvel/parcela informado não pode ser de natureza Urbana");
            }
        }
    }

    private void validarSituacaoIncorporacao(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO)  throws NegocioException{
        if (imovel.getCodigoSituacaoIncorporacao().equals(INCORPORADO)){
            if(fundamentoLegalDTO.getIsincorporado() == null || !fundamentoLegalDTO.getIsincorporado()){
                throw new NegocioException("O imóvel/parcela informado não pode ter a situação da incorporação como Incorporado");
            }
        } else if(imovel.getCodigoSituacaoIncorporacao().equals(EM_PROCESSO_DE_INCORPORACAO)){
            if(fundamentoLegalDTO.getIsEmProcessoIncorporacao() == null || !fundamentoLegalDTO.getIsEmProcessoIncorporacao()){
                throw new NegocioException("O imóvel/parcela informado não pode ter a situação da incorporação como em processo de incorporação");
            }
        } else {
            if(fundamentoLegalDTO.getIsNaoSeAplica() == null || !fundamentoLegalDTO.getIsNaoSeAplica()){
                throw new NegocioException("O imóvel/parcela informado não pode ter a situação da incorporação como não se aplica");
            }
        }
    }

    private void validarFormaDeIncorporacao(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException{
        FormaDeIncorporacaoEnum formaDeIncorporacaoEnum = FormaDeIncorporacaoEnum.getByCodigo(imovel.getCodigoFormaIncorporacao());
        List<FormaDeIncorporacaoPermitidaDTO> formaDeIncorporacaoPermitida = fundamentoLegalDTO.getFormaDeIncorporacaoPermitidas().stream().filter(f -> f.getDescricao().equals(formaDeIncorporacaoEnum.getDescricao())).collect(Collectors.toList());
        if(formaDeIncorporacaoPermitida.isEmpty()){
            throw new NegocioException("O imóvel/parcela informado não pode ter a forma de incorporação como " + formaDeIncorporacaoEnum.getDescricao());
        }
        if(imovel.getCodigoFormaIncorporacao().equals(POR_AQUISICAO)){
            validarTipoAquisicaoFundamento(imovel, fundamentoLegalDTO);
            if (imovel.getCodigoTipoAquisicao().equals(SUCESSAO_POR_ENTIDADES)){
                validarEntidadeExtinta(imovel, fundamentoLegalDTO);
            }
        }
    }

    private void validarTipoAquisicaoFundamento(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException{
        TipoAquisicaoEnum tipoAquisicaoEnum = TipoAquisicaoEnum.getByCodigo(imovel.getCodigoTipoAquisicao());
        List<TipoAquisicaoPermitidaDTO> tipoAquisicaoPermitida = fundamentoLegalDTO.getTipoAquisicaoPermitidas().stream().filter(f -> f.getDescricao().equals(tipoAquisicaoEnum.getDescricao())).collect(Collectors.toList());
        if(tipoAquisicaoPermitida.isEmpty()){
            throw new NegocioException("O imóvel/parcela informado não pode ter o tipo de aquisição como " + tipoAquisicaoEnum.getDescricao());
        }
    }

    private void validarEntidadeExtinta(Imovel imovel, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException{
        EntidadeExtintaEnum entidadeExtintaEnum = EntidadeExtintaEnum.getByCodigo(imovel.getCodigoEntidadeExtinta());
        List<EntidadeExtintaDTO> entidadeExtintaDTOS = fundamentoLegalDTO.getEntidadeExtintas().stream().filter(f -> f.getDescricao().equals(entidadeExtintaEnum.getDescricao())).collect(Collectors.toList());
        if(entidadeExtintaDTOS.isEmpty()){
            throw new NegocioException("O imóvel/parcela informado não pode ter o proprietário anterior como " + entidadeExtintaEnum.getDescricao());
        }
    }

    protected Boolean verificarParcela(Imovel imovel){
        return imovel.getParcelas().size() > 1;
    }

}

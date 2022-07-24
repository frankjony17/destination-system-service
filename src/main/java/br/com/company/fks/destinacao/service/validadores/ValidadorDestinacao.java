package br.com.company.fks.destinacao.service.validadores;

import br.com.company.fks.destinacao.dominio.entidades.Contrato;
import br.com.company.fks.destinacao.dominio.entidades.DadosResponsavel;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.DestinacaoImovel;
import br.com.company.fks.destinacao.dominio.entidades.Encargo;
import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.Foto;
import br.com.company.fks.destinacao.dominio.entidades.Licitacao;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.StatusDestinacao;
import br.com.company.fks.destinacao.dominio.entidades.TransferenciaTitularidade;
import br.com.company.fks.destinacao.dominio.entidades.UsoProprio;
import br.com.company.fks.destinacao.dominio.enums.StatusDestinacaoEnum;
import br.com.company.fks.destinacao.dominio.enums.TipoRepresentacaoEnum;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.service.UsuarioService;
import br.com.company.fks.destinacao.utils.CNPJUtils;
import br.com.company.fks.destinacao.utils.CPFUtils;
import br.com.company.fks.destinacao.utils.Constants;
import br.com.company.fks.destinacao.utils.MensagemNegocio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Basis Tecnologia on 20/10/2016.
 */

/**
 *
 * @param <T> Recebe um tipo de validação e faz a validação dos valores comuns a todas as destinações
 */
@Service
public abstract class ValidadorDestinacao <T> {

    protected static final Logger LOGGER = Logger.getLogger(ValidadorDestinacao.class);
    protected static final int QTD_DIGITOS_CNPJ = 14;

    @Autowired
    private MensagemNegocio mensagemNegocio;

    @Autowired
    private UsuarioService usuarioService;

    /**
     *
     * @param objeto Recebe um objeto do tipo de destinação e verifica se possui alguma verificação específica
     * @throws NegocioException
     */
    public void validar(T objeto) throws NegocioException {

        Destinacao destinacao = (Destinacao) objeto;
        List<String> erros = new ArrayList<>();

        if (!(destinacao instanceof PosseInformal)) {
            validaImovelJurisdicaoUsuarioLogado(destinacao.getDestinacaoImoveis(), erros);
            validarImoveisEstadoDiferente(destinacao.getDestinacaoImoveis(), erros);
            if(!(destinacao instanceof TransferenciaTitularidade) && !(destinacao instanceof UsoProprio) && destinacao.getDadosResponsavel().getTipoRepresentacao() != null
                    && (destinacao.getDadosResponsavel().getTipoRepresentacao().equals(TipoRepresentacaoEnum.FORMAL)
                    || destinacao.getDadosResponsavel().getTipoRepresentacao().equals(TipoRepresentacaoEnum.INFORMAL))){
                validarCpfCnpj(destinacao.getDadosResponsavel(), erros);

            }
        }
        validaExisteDestinacaoImovelInformado(destinacao.getDestinacaoImoveis(), erros);

        if (!erros.isEmpty()) {
            throw new NegocioException(erros);
        }
        
        validadorEspecifico(objeto);
    }

    /**
     *
     * @param objeto recebe uma objeto de destinação, e envia para sua verificação específica
     * @throws NegocioException
     */
    public abstract void validadorEspecifico(T objeto) throws NegocioException;

    protected void validaImovelJurisdicaoUsuarioLogado (List<DestinacaoImovel> destinacaoImoveis, List<String> erros) {
        if (destinacaoImoveis != null) {
            Set<String> jurisdicoes = usuarioService.getUsuarioLogado().getJurisdicoes();
            validaImovelJurisdicaoUsuarioLogado(destinacaoImoveis, erros, jurisdicoes);
        }

    }

    protected void validarExisteFotos(List<Foto> fotos, List<String> erros){
        Optional<List<Foto>> optionalFoto = Optional.ofNullable(fotos);
        int sizeFotos = optionalFoto.map(List::size).orElse(0);
        if(sizeFotos == 0){
            erros.add(mensagemNegocio.get("fotos.nao.existe"));
        }
    }


    private void validaImovelJurisdicaoUsuarioLogado(List<DestinacaoImovel> destinacaoImoveis, List<String> erros, Set<String> jurisdicoes) {
        for (DestinacaoImovel destinacaoImovel : destinacaoImoveis) {
            Endereco endereco = destinacaoImovel.getImovel().getEndereco();
            if (!jurisdicoes.contains(endereco.getUf())) {
                erros.add(mensagemNegocio.get("imovel.outra.juristicao"));
                break;
            }
        }
    }

    protected void validarImoveisEstadoDiferente (List<DestinacaoImovel> destinacaoImoveis, List<String> erros) {
        Optional<List<DestinacaoImovel>> optional = Optional.ofNullable(destinacaoImoveis);
        Boolean listaPreenchida =
                optional.map(list -> !list.isEmpty())
                        .orElse(false);

        if (listaPreenchida) {
            String uf = destinacaoImoveis.get(0).getImovel().getEndereco().getUf();
            validarImoveisEstadoDiferente(destinacaoImoveis, erros, uf);
        }
    }

    private void validarImoveisEstadoDiferente(List<DestinacaoImovel> destinacaoImoveis, List<String> erros, String uf) {
        for (DestinacaoImovel destinacaoImovel : destinacaoImoveis) {
            if (!uf.equals(destinacaoImovel.getImovel().getEndereco().getUf())) {
                erros.add(mensagemNegocio.get("imovel.estado.diferente"));
                break;
            }
        }
    }

    protected void validarCpfCnpj(DadosResponsavel dadosResponsavel, List<String> erros) {
        if (dadosResponsavel != null && !dadosResponsavel.getTipoPosseOcupacao().getDescricao().equals("Ocupante não identificado")) {
            validarCpfCnpj(dadosResponsavel.getCnpj(), erros);
        }
    }

    protected void validarCpfCnpj(String cpfCnpj, List<String> erros) {
        if (cpfCnpj.length() == Constants.ONZE) {
            validarCpf(cpfCnpj, erros);
        } else {
            validarCnpj(cpfCnpj, erros);
        }
    }

    private void validarCpf(String cpfCnpj, List<String> erros) {
        if (!CPFUtils.validar(cpfCnpj)) {
            erros.add(mensagemNegocio.get("cpf.invalido"));
        }
    }

    private void validarCnpj(String cpfCnpj, List<String> erros) {
        if (!CNPJUtils.isCNPJ(cpfCnpj)) {
            erros.add(mensagemNegocio.get("cnpj.invalido"));
        }
    }

    protected void validarCamposObrigatorioContrato(Destinacao destinacao, List<String> erros) {
        
        if (isStatusDiferenteRascunho(destinacao.getStatusDestinacao())) {        
            Optional<Contrato> optional = Optional.ofNullable(destinacao.getContrato());

            if (optional.map(map -> map.getDataInicio() == null).orElse(true)) {
                erros.add(mensagemNegocio.get("contrato.dataInicio.vazio"));
            }
            
        }

    }


    protected Boolean isStatusDiferenteRascunho(StatusDestinacao statusDestinacao) {
        StatusDestinacaoEnum statusDestinacaoEnum = StatusDestinacaoEnum.getByCodigo(statusDestinacao.getId());
        return statusDestinacaoEnum != StatusDestinacaoEnum.RASCUNHO;
    }

    protected void validarExisteResponsavel (List<Responsavel> responsaveis, List<String> erros) {
        if (responsaveis == null || responsaveis.isEmpty()) {
            erros.add(mensagemNegocio.get("resposavel.vazio"));
        }
    }

    protected void validarDatasEntreEncargoEAssinatura(Contrato contrato, List<Encargo> encargos, List<String> erros){
        if (encargos != null && !encargos.isEmpty()) {

            if (verificarContratoNulo(contrato, erros)) {
                return;
            }

            if (verificarDataEncargosInvalida(encargos, contrato)) {
                erros.add(mensagemNegocio.get("encargo.data.invalida"));
            }
        }

    }

    private Boolean verificarDataEncargosInvalida(List<Encargo> encargos, Contrato contrato) {
        Boolean dataInvalida = false;

        for (Encargo encargo : encargos) {
            if (encargo.getDataCumprimento() == null) {
                dataInvalida = false;
                break;
            } else if (encargo.getDataCumprimento().compareTo(contrato.getDataInicio()) <= Constants.ZERO) {
                dataInvalida = true;
            }
        }

        return dataInvalida;
    }

    private boolean verificarContratoNulo(Contrato contrato, List<String> erros) {
        if (contrato == null) {
            erros.add(mensagemNegocio.get("contrato.vazio"));
            return true;
        } else if (contrato.getDataInicio() == null) {
            erros.add(mensagemNegocio.get("contrato.dataInicio.vazio"));
            return true;
        }
        return false;
    }

    protected void validarExisteFundamentoLegal (Long codFundamentoLegal, List<String> erros) {
        if (codFundamentoLegal == null) {
            erros.add(mensagemNegocio.get("fundamento.legal"));
        }
    }

    protected void validaExisteDestinacaoImovelInformado(List<DestinacaoImovel> destinacoesImoveis, List<String> erros) {
        Optional<List<DestinacaoImovel>> optional = Optional.ofNullable(destinacoesImoveis);
        int size = optional.map(List::size).orElse(0);
        if (size == 0) {
            erros.add(mensagemNegocio.get("imovel.nao.informado"));
        }
    }


    protected void validarCamposObrigatoriosLicitacao(Licitacao licitacao, List<String> erros) {
        validarCampoTipoLicitacao(licitacao, erros);
        validarNumeroProcesso(licitacao, erros);
    }

    protected void validarNumeroProcesso(Licitacao licitacao, List<String> erros) {
        if (licitacao.getNumeroProcesso() == null) {
            erros.add(mensagemNegocio.get("licitacao.numero.processo.vazio"));
        }
    }

    protected void validarCampoTipoLicitacao(Licitacao licitacao, List<String> erros) {
        if (licitacao.getTipoLicitacao() == null || licitacao.getTipoLicitacao().getId() == null) {
            erros.add(mensagemNegocio.get("licitacao.tipo.vazio"));
        }
    }
}

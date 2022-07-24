package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.RespostaDadosPessoaFisicaHistoricoCampo;
import br.com.company.fks.destinacao.dominio.dto.ConsultaImovelDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaConsultaDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaFisicaDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaHistoricoCampoDTO;
import br.com.company.fks.destinacao.dominio.dto.DadosPessoaJuridicaDTO;
import br.com.company.fks.destinacao.dominio.dto.EnderecoDTO;
import br.com.company.fks.destinacao.dominio.dto.FundamentoLegalDTO;
import br.com.company.fks.destinacao.dominio.dto.NaturezaJuridicaPermitidaDTO;
import br.com.company.fks.destinacao.dominio.dto.SocioDTO;
import br.com.company.fks.destinacao.dominio.dto.TelefoneDTO;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DadosPessoaService {

    @Autowired
    private URLIntegracaoUtils urlIntegracaoUtils;

    @Autowired
    private RequestUtils requestUtils;



    public DadosPessoaFisicaDTO buscarPessoaFisica(DadosPessoaConsultaDTO dto, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException {
            if(!dto.getTipoDestinacao().equals("POSSE_INFORMAL")) {
                validarPessoaFisica(fundamentoLegalDTO);
            }
            return mocarPessoFisica();
    }

    private DadosPessoaFisicaDTO mocarPessoFisica(){
        DadosPessoaFisicaDTO dados = new DadosPessoaFisicaDTO();
        dados.setCpf("79506240949");
        dados.setSituacaoCadastral("0");
        dados.setNome("TVZMWIV TLNVH WV LOREVRIZ");
        dados.setDataNascimento(new Date());
        dados.setAnoObito(0);
        dados.setNomeMae("ZMZ NZIRZ WV LOREVRIZ");
        dados.setSexo("M");
        dados.setEstrangeiro(Boolean.FALSE);
        dados.setTituloEleitor("234235332");
        dados.setDataCriacao(new Date());
        dados.setEndereco(mocarEndereco());
        dados.setFonte("RECEITA FEDERAL");
        dados.setTelefones(mocarTelefones());
        return dados;
    }
    private void validarPessoaFisica(FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException {
        boolean encontrado = false;
        for (NaturezaJuridicaPermitidaDTO naturezaJuridicas : fundamentoLegalDTO.getNaturezaJuridicaPermitidas()) {
            if (naturezaJuridicas.getNome().equals("PESSOA_FISICA")){
                encontrado = true;
            }
        }
        if (!encontrado) {
            throw new NegocioException("ESSE FUNDAMENTO LEGAL NÃO É PERMITIDO PARA PESSOA FÍSICA");
        }
    }


    public List<DadosPessoaHistoricoCampoDTO> buscarPessoaFisicaHistoricaCampo(String cpf, String campo) {
        String url = urlIntegracaoUtils.getUrlGetBuscarDadosPessoaFisicaHistoricoCampo(cpf, campo);
        ResponseEntity<?> responseEntity = requestUtils.doGet(url, RespostaDadosPessoaFisicaHistoricoCampo.class);
        return ((RespostaDadosPessoaFisicaHistoricoCampo) responseEntity.getBody()).getResultado();
    }

    public DadosPessoaJuridicaDTO buscarPessoaJuridica(DadosPessoaConsultaDTO dto, FundamentoLegalDTO fundamentoLegalDTO) throws NegocioException {

        DadosPessoaJuridicaDTO dados = new DadosPessoaJuridicaDTO();
        dados.setCnpj("60701190000104");
        dados.setCapitalSocial("100");
        dados.setNaturezaJuridica("Órgão Público Autônomo Municipal");
        dados.setNomeEmpresarial("Brasilinha SA");
        dados.setCpfResponsavel("79506240949");
        dados.setTelefones(mocarTelefones());
        dados.setSocios(mocarSocios());
        dados.setEndereco(mocarEndereco());
        if(!dto.getTipoDestinacao().equals("POSSE_INFORMAL")) {
            validarPessoaJuridica(fundamentoLegalDTO, dados);
        }
        return dados;
    }

    public void validarPessoaJuridica(FundamentoLegalDTO fundamentoLegalDTO, DadosPessoaJuridicaDTO dados) throws NegocioException{
        boolean naturezaFisicaEncontrada = false;
        boolean naturezaJuridicaPermitida = false;
        int i = 0;
        for (NaturezaJuridicaPermitidaDTO naturezaJuridicas: fundamentoLegalDTO.getNaturezaJuridicaPermitidas()) {
            if(naturezaJuridicas.getDescricao().equals("PESSOA_FISICA")) {
                naturezaFisicaEncontrada = true;
            }
            if(naturezaJuridicas.getDescricao().equals(dados.getNaturezaJuridica())) {
                naturezaJuridicaPermitida = true;
            }
            i++;
        }
        validarNaturezaJuridica(naturezaJuridicaPermitida, naturezaFisicaEncontrada, i);
    }
    private void validarNaturezaJuridica(boolean naturezaJuridicaPermitida, boolean naturezaFisicaEncontrada, int i) throws  NegocioException{
        if(!naturezaJuridicaPermitida) {
            if(!naturezaFisicaEncontrada && i == 1){
                throw new NegocioException("ESSE FUNDAMENTO LEGAL NÃO É PERMITIDO PARA PESSOA JURÍDICA");
            }
            else {
                throw new NegocioException("PARA ESSE FUNDAMENTO LEGAL NÃO É PERMITIDO A NATUREZA JURÍDICA DO CNPJ INFORMADO.");
            }
        }
    }
    public List<DadosPessoaHistoricoCampoDTO> buscarPessoaJuridicaHistoricaCampo(String cnpj, String campo) {
        String url = urlIntegracaoUtils.getUrlGetBuscarDadosPessoaJuridicaHistoricoCampo(cnpj, campo);
        ResponseEntity<?> responseEntity = requestUtils.doGet(url, RespostaDadosPessoaFisicaHistoricoCampo.class);
        return ((RespostaDadosPessoaFisicaHistoricoCampo) responseEntity.getBody()).getResultado();
    }

    private EnderecoDTO mocarEndereco() {
        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setCep("82100570");
        endereco.setTipoLogradouro("PRACA");
        endereco.setLogradouro("WDWIHNX HBJNQX NH UXGSW WIWMCW 100");
        endereco.setNumero("312");
        endereco.setComplemento("TORRE OLAVO SETUBAL");
        endereco.setMunicipio("SAO PAULO");
        endereco.setBairro("PARQUE JABAQUARA");
        endereco.setUf("SP");
        endereco.setPais("BRASIL");
        endereco.setCidadeExterior(null);
        endereco.setCodigoPostal("555");
        return endereco;
    }

    private List<TelefoneDTO> mocarTelefones() {
        TelefoneDTO telefone = new TelefoneDTO();
        telefone.setDdd("11");
        telefone.setNumero("234234234");
        return Arrays.asList(telefone);
    }

    private List<SocioDTO> mocarSocios() {
        SocioDTO socio = new SocioDTO();
        socio.setNome("Marco");
        socio.setNomePaisOrigem("Brazil");
        socio.setPercentualParticipacao(40);
        return Arrays.asList(socio);
    }


}
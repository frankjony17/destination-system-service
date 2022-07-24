package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.dto.BenfeitoriaDTO;
import br.com.company.fks.destinacao.dominio.dto.ParcelaDTO;
import lombok.SneakyThrows;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haillanderson on 22/03/17.
 */

@IntegrationTest("server.port:0")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParcelaControllerIT extends BaseIntegrationTestCofig{

    private static final String URL_BASE = "/parcela/";

    @Autowired
    private Environment environment;

    @Ignore
    @Test
    @SneakyThrows
    public void salvar(){
        List<ParcelaDTO> novasParcelas = criarListaNovaParcela();
        String requestJson = toJson(novasParcelas);
        mockMvc.perform(post(URL_BASE + "/salvar")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void editarComArquivo(){
        ParcelaDTO parcelaDTO = criarParcelaDTO("P0", 61l, 61l, true);
        parcelaDTO.setId(76L);
        parcelaDTO.setArquivosExcluir(Collections.emptyList());
        String requestJson = toJson(parcelaDTO);
        mockMvc.perform(put(URL_BASE + "/editar")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void editarSemArquivo(){
        ParcelaDTO parcelaDTO = criarParcelaDTO("P0", 61l, 61l, false);
        parcelaDTO.setId(76L);
        parcelaDTO.setArquivosExcluir(Collections.emptyList());
        String requestJson = toJson(parcelaDTO);
        mockMvc.perform(put(URL_BASE + "/editar")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    public void editarGerandoErro(){
        ParcelaDTO parcelaDTO = criarParcelaDTO("P0", 61l, 61l, false);
        parcelaDTO.setId(76L);
        ArquivoDTO arquivoDTO = criarArquivo();
        arquivoDTO.setId(8L);
        parcelaDTO.setArquivosExcluir(Arrays.asList(arquivoDTO));
        String requestJson = toJson(parcelaDTO);
        mockMvc.perform(put(URL_BASE + "/editar")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Ignore
    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @SneakyThrows
    public void excluirParcela(){
        List<ParcelaDTO> parcelas = new ArrayList<>();
        ParcelaDTO parcelaExcluida = criarParcelaDTOComImovel(null);
        ParcelaDTO parcelaAtribuida = criarParcelaDTOComImovel(78L);
        parcelas.add(parcelaExcluida);
        parcelas.add(parcelaAtribuida);
        String requestJson = toJson(parcelas);
        mockMvc.perform(post(URL_BASE + "/excluir")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @SneakyThrows
    public void excluirParcelaSemBenfeitoria(){
        List<ParcelaDTO> parcelas = new ArrayList<>();
        ParcelaDTO parcelaExcluida = criarParcelaDTOComImovel(null);
        parcelaExcluida.setBenfeitorias(null);
        ParcelaDTO parcelaAtribuida = criarParcelaDTOComImovel(78L);
        parcelaAtribuida.setBenfeitorias(null);
        parcelas.add(parcelaExcluida);
        parcelas.add(parcelaAtribuida);
        String requestJson = toJson(parcelas);
        mockMvc.perform(post(URL_BASE + "/excluir")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @SneakyThrows
    public void excluirParcelaDuasParcelas(){
        criarNovaParcela();
        List<ParcelaDTO> parcelas = new ArrayList<>();
        ParcelaDTO parcelaExcluida = criarParcelaDTOComImovel(null);
        ParcelaDTO parcelaAtribuida = criarParcelaDTOComImovel(78L);
        parcelas.add(parcelaExcluida);
        parcelas.add(parcelaAtribuida);
        String requestJson = toJson(parcelas);
        mockMvc.perform(post(URL_BASE + "/excluir")
                .contentType(contentType)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Ignore
    @Test
    @SneakyThrows
    public void buscarParcelasSemUtilizacao(){
        mockMvc.perform(get(URL_BASE + "/buscarParcelasSemUtilizacao/00000010/0000")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarParcelasInativas(){
        mockMvc.perform(get(URL_BASE + "/buscarParcelasInativas/00000010")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    private List<ParcelaDTO> criarListaNovaParcela() {
        return Arrays.asList(criarParcelaDTO("P1", 61l, 61l, true), criarParcelaDTO("P2", 61l, 61l, false));
    }

    private ParcelaDTO criarParcelaDTO(String sequencial, Long idImovel, Long idDestinacao, Boolean comArquivoBenfeitoria) {
        Set<Long> idDestinacoesImovel = new HashSet<>();
        idDestinacoesImovel.add(idDestinacao);
        ParcelaDTO parcelaDTO = new ParcelaDTO();
        parcelaDTO.setSequencial(sequencial);
        parcelaDTO.setIdImovel(idImovel);
        parcelaDTO.setIdDestinacaoImoveis(idDestinacoesImovel);
        parcelaDTO.setAreaTerreno(BigDecimal.TEN);
        parcelaDTO.setIdParcelaInativar(76L);
        parcelaDTO.setMemorialDescritivo("Teste");
        if (comArquivoBenfeitoria) {
            parcelaDTO.setArquivos(Arrays.asList(criarArquivo()));
            parcelaDTO.setBenfeitorias(criarBenfeitoriasDTO());
        }
        return parcelaDTO;
    }

    private List<BenfeitoriaDTO> criarBenfeitoriasDTO() {
        BenfeitoriaDTO benfeitoriaDTO = new BenfeitoriaDTO();
        benfeitoriaDTO.setId(73L);
        benfeitoriaDTO.setAreaConstruida(BigDecimal.TEN);
        return Arrays.asList(benfeitoriaDTO);
    }



    private ArquivoDTO criarArquivo(){
        ArquivoDTO arquivoDTO= new ArquivoDTO();
        arquivoDTO.setId(1L);
        return arquivoDTO;
    }

    private ParcelaDTO criarParcelaDTOComImovel(Long id){
        Set<Long> idsDestinacaoImoveis = new HashSet<>();
        idsDestinacaoImoveis.add(56L);
        ParcelaDTO parcelaDTO =  new ParcelaDTO();
        List<BenfeitoriaDTO> benfeitorias = new ArrayList<>();
        benfeitorias.add(criarBenfeitoriasDTO().get(0));
        List<ArquivoDTO> arquivos = new ArrayList<>();
        arquivos.add(criarArquivo());
        parcelaDTO.setId(id);
        parcelaDTO.setRip("00000010");
        parcelaDTO.setSequencial("P0");
        parcelaDTO.setBenfeitorias(benfeitorias);
        parcelaDTO.setAreaTerreno(BigDecimal.valueOf(100));
        parcelaDTO.setAreaDisponivel(BigDecimal.valueOf(100));
        parcelaDTO.setAtiva(Boolean.TRUE);
        parcelaDTO.setMemorialDescritivo("teste");
        parcelaDTO.setArquivos(arquivos);
        parcelaDTO.setIdDestinacaoImoveis(idsDestinacaoImoveis);
        return parcelaDTO;
    }

    @SneakyThrows
    private void criarNovaParcela(){

        Class.forName(environment.getProperty("spring.datasource.driverClassName"));
        String url = environment.getProperty("spring.datasource.url");
        String user = environment.getProperty("spring.datasource.username");
        String pwd = environment.getProperty("spring.datasource.password");
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String query = "INSERT INTO destinacao.tb_parcela " +
                "(id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo) "+
                "VALUES(78, 'P0', 242.42, 242.42, true, 61, NULL)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);

        statement.close();
        connection.close();

    }

}
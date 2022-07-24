package br.com.company.fks.destinacao.apresentacao;

import br.com.company.fks.destinacao.dominio.dto.CancelamentoEncerramentoDTO;
import br.com.company.fks.destinacao.dominio.enums.DespachoCancelarEncerrarEnum;
import br.com.company.fks.destinacao.dominio.enums.MotivoCancelarEncerrarEnum;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class CancelamentoEncerramentoUtilizacaoControllerIT extends BaseIntegrationTestCofig{

    private String BASE_URL = "/cancelamento-encerramento/";

    private CancelamentoEncerramentoDTO cancelamentoEncerramentoDTO;

    private Date data;

    @Before
    public void setUp() {
        cancelamentoEncerramentoDTO = new CancelamentoEncerramentoDTO();
        data = new Date();
    }

    @Test
    public void buscarMotivosCancelarEncerrar() throws Exception {
        mockMvc.perform(get(BASE_URL + "lista-motivos")
            .contentType(contentType))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    public void buscarDespachosCancelarEncerrar() throws Exception {
        mockMvc.perform(get(BASE_URL + "lista-despachos")
            .contentType(contentType))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Ignore
    @Test
    public void submeterSuperIntendente() throws Exception {
        CancelamentoEncerramentoDTO dto = new CancelamentoEncerramentoDTO();
        dto.setNomeResponsavelTecnico("Nome Responsavel Tecnico");
        dto.setCpfResponsavelTecnico("12312312312");
        dto.setNomeSuperintendente("Nome Superintendente");
        dto.setCpfSuperIntendente("32132132132");
        dto.setDataCancelamentoEncerramento(new Date());
        dto.setMotivo(MotivoCancelarEncerrarEnum.CANCELAMENTO_POR_ERRO);
        dto.setObservacaoMotivo("Observacao Motivo");
        dto.setArquivos(Collections.emptyList());
        dto.setDespacho(DespachoCancelarEncerrarEnum.INDEFERO_CANCELAMENTO_ENCERRAMENTO);
        dto.setObservacaoDespacho("Observaçao Despacho");

        mockMvc.perform(put(BASE_URL + "/submeter/1")
            .contentType(contentType)
            .content(toJson(dto)))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Ignore
    @Test
    public void buscarPorIdDestinacaoRetornaDestinacao() throws Exception {
        submeterSuperIntendente();
        mockMvc.perform(get(BASE_URL + "/destinacao/1")
            .contentType(contentType))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    public void getDataCancelamentoEncerramentoNula(){
        cancelamentoEncerramentoDTO.setDataCancelamentoEncerramento(null);
        assertNull(cancelamentoEncerramentoDTO.getDataCancelamentoEncerramento());
    }
    @Test
    public void getDataCancelamentoEncerramentoNaoNula(){
        cancelamentoEncerramentoDTO.setDataCancelamentoEncerramento(data);
        assertEquals(data, cancelamentoEncerramentoDTO.getDataCancelamentoEncerramento());
    }
}
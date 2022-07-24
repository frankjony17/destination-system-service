package br.com.company.fks.destinacao.apresentacao;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Created by diego on 04/01/17.

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DominioControllerIT extends BaseIntegrationTestCofig {

    private String URL_BASE = "/dominio/";

    @Autowired
    private Environment environment;

    @Test
    @SneakyThrows
    public void buscarTodosTipoPagamentos() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-pagamento")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTipoPeriocidades() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-periocidade")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTipoMoeda() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-moeda")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTipoReajuste() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-reajuste")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTipoJuros() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-juros")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTipoLicitacao() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-licitacao")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTipoPosse() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-posse")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTipoModalidade() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-modalidade")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTiposConcessao() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-concessao")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTiposInstrumento() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-instrumento")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTiposDestinacao() {
        mockMvc.perform(get(URL_BASE + "/buscar-tipo-destinacao")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodasUfs() {
        mockMvc.perform(get(URL_BASE + "/buscar-ufs")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }

    @Test
    @SneakyThrows
    public void buscarTodosTipoPagamentosVazio() {

        apagarTodosTiposPagamento();

        mockMvc.perform(get(URL_BASE + "/buscar-tipo-pagamento")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isEmpty());

    }

    @Test
    @SneakyThrows
    public void buscarTipoDocumento() {

        mockMvc.perform(get(URL_BASE + "/buscar-tipo-documento")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty())
                .andExpect(jsonPath("$.resultado[0].id").value(1))
                .andExpect(jsonPath("$.resultado[0].descricao").value("Extrato"));
    }

    @Test
    @SneakyThrows
    public void buscaTodosTiposAtosAdministrativos() {

        mockMvc.perform(get(URL_BASE + "/atoAutorizativo")
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").isNotEmpty());
    }


    @SneakyThrows
    private void apagarTodosTiposPagamento() {
        Class.forName(environment.getProperty("spring.datasource.driverClassName"));
        String url = environment.getProperty("spring.datasource.url");
        String user = environment.getProperty("spring.datasource.username");
        String pwd = environment.getProperty("spring.datasource.password");
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String query = "DELETE FROM destinacao.tb_tipo_pagamento";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);

        statement.close();
        connection.close();

    }

}

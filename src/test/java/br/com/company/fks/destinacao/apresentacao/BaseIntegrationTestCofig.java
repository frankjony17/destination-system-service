package br.com.company.fks.destinacao.apresentacao;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import br.com.company.fks.destinacao.SistemaUnificadoApplication;
import br.com.company.fks.destinacao.dominio.enums.UFEnum;
import br.com.company.fks.destinacao.utils.RequestUtils;
import br.com.company.fks.destinacao.utils.URLIntegracaoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hibernate.envers.AuditOverride;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//Created by diego on 24/11/16.


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SistemaUnificadoApplication.class})
@WebAppConfiguration
@ActiveProfiles("h2")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Ignore
public class BaseIntegrationTestCofig {

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    protected MockMvc mockMvc;
    protected ObjectMapper mapper;
    private ObjectWriter ow;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected RequestUtils requestUtils;

    @Autowired
    protected URLIntegracaoUtils urlIntegracaoUtils;

    protected UsuarioLogadoDTO usuarioLogadoDTO;

    protected String [] permissoes;

    @Before
    public void setup() throws Exception {
        usuarioLogadoDTO = criarUsuarioLogado();
        permissoes = getPermissoes();
        criarUsarioLogadoMock();
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        mapper = new ObjectMapper();
        ow = mapper.writer().withDefaultPrettyPrinter();
    }

    protected String toJson(Object object) throws JsonProcessingException {
        return ow.writeValueAsString(object);
    }

    private void criarUsarioLogadoMock() {
        SecurityContextHolder.getContext().setAuthentication(
                new AnonymousAuthenticationToken("61914509153", usuarioLogadoDTO, AuthorityUtils.createAuthorityList(permissoes)));
    }

    private String[] getPermissoes() {
        String [] permissoes = new String[] {"DESTINACAOMANTERDOACAO",
                                             "DESTINACAOMANTERVENDA",
                                             "DESTINACAOMANTERPOSSEINFORMAL",
                                             "DESTINACAOMANTERCUEM",
                                             "DESTINACAOMANTERCDRU",
                                             "DESTINACAO_EXEC_ANALISE_TEC_TECNICO",
                                             "DESTINACAO_EXEC_ANALISE_TEC_CHEFIA",
                                             "DESTINACAO_EXEC_ANALISE_TEC_SUPERINTENDENTE",
                                             "DESTINACAO_EXEC_ANALISE_TEC_SECRETARIO"};

        return permissoes;
    }

    private UsuarioLogadoDTO criarUsuarioLogado() {
        UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
        usuarioLogadoDTO.setId(1L);
        usuarioLogadoDTO.setNome("Teste");
        usuarioLogadoDTO.setCpf("61914509153");
        usuarioLogadoDTO.setIdSistema(2L);
        usuarioLogadoDTO.setPerfil("DESTINACAO.TECNICO");
        usuarioLogadoDTO.setPermissoes(criarPermissoes());
        usuarioLogadoDTO.setJurisdicoes(criarJurisdicoes());
        return usuarioLogadoDTO;
    }

    private Set<String> criarJurisdicoes() {
        Set<String> ufs = new HashSet<>();
        for (UFEnum ufEnum : UFEnum.values()) {
            if(!ufEnum.getSigla().equals("SE")){
                ufs.add(ufEnum.getSigla());
            }
        }
        return ufs;
    }


    private Set<String> criarPermissoes() {
        Set<String> permissoes = new HashSet<>();
        for (String permissao : getPermissoes()) {
            permissoes.add(permissao);
        }
        return permissoes;
    }

}

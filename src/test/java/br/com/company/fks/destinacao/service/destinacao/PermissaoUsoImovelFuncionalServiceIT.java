package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.PermissaoUsoImovelFuncional;
import br.com.company.fks.destinacao.repository.PermissaoUsoImovelFuncionalRepository;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class PermissaoUsoImovelFuncionalServiceIT extends BaseIntegrationTestCofig {

    @Autowired
    private PermissaoUsoImovelFuncionalService permissaoUsoImovelFuncionalService;

    @Autowired
    private PermissaoUsoImovelFuncionalRepository permissaoUsoImovelFuncionalRepository;

    @Test
    @SneakyThrows
    public void testeSalvarDadosEspecificos() {
        permissaoUsoImovelFuncionalService.salvarDadosEspecificos(new PermissaoUsoImovelFuncional());
        permissaoUsoImovelFuncionalRepository.save(new PermissaoUsoImovelFuncional());


    }




}

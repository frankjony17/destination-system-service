package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.Documento;
import br.com.company.fks.destinacao.dominio.entidades.SubTipoDocumento;
import br.com.company.fks.destinacao.dominio.entidades.TipoDocumento;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by haillanderson on 18/04/17.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class DocumentoServiceIT extends BaseIntegrationTestCofig{

    @Autowired
    private DocumentoService documentoService;

    @Test
    @SneakyThrows
    public void salvar(){
        Documento documento = documentoService.salvar(criarDocumento(),criarDestinacao());
        assertNotNull(documento.getId());
    }

    @Test
    @SneakyThrows
    public void salvarSemDocumento(){
        Documento parametro = null;
        Documento documento = documentoService.salvar(parametro,criarDestinacao());
        assertNull(documento);
    }

    private Documento criarDocumento(){
        Documento documento = new Documento();
        TipoDocumento tipoDocumento = new TipoDocumento();
        SubTipoDocumento subTipoDocumento = new SubTipoDocumento();
        tipoDocumento.setId(1);
        subTipoDocumento.setId(1L);
        documento.setTipoDocumento(tipoDocumento);
        documento.setSubTipoDocumento(subTipoDocumento);
        documento.setDestinacao(criarDestinacao());
        return documento;
    }

    private Destinacao criarDestinacao(){
        Destinacao destinacao = new Destinacao();
        destinacao.setId(1L);
        return destinacao;
    }

}
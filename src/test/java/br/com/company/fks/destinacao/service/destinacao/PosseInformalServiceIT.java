package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.apresentacao.BaseIntegrationTestCofig;
import br.com.company.fks.destinacao.dominio.entidades.Destinacao;
import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import br.com.company.fks.destinacao.dominio.entidades.Ocupante;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.dominio.enums.TipoDestinacaoEnum;
import br.com.company.fks.destinacao.repository.DestinacaoRepository;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by basis on 19/05/17.
 */
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PosseInformalServiceIT extends BaseIntegrationTestCofig{

     @Autowired
     private PosseInformalService posseInformalService;
     @Autowired
     private DestinacaoRepository destinacaoRepository;

     @Ignore
     @Test
     @Transactional(propagation = Propagation.REQUIRED)
     public void testarbuscarDestinacoes(){
         PosseInformal posseInformal = new PosseInformal();
         Destinacao destinacao = new Destinacao();
         destinacao.setId(1L);
         posseInformal.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
         Imovel imovel = new Imovel();
         imovel.setId(20L);
         posseInformal.setImovel(imovel);
         posseInformal = destinacaoRepository.save(posseInformal);
         Ocupante ocupante = new Ocupante();
         ocupante.setCpfCnpj("00000000191");
         ocupante.setNomeRazaoSocial("teste");
         List<Ocupante> ocupantes = Arrays.asList(ocupante);
         posseInformal.setOcupantes(ocupantes);
         destinacao = posseInformalService.salvarDadosEspecificos(posseInformal);
         Assert.assertFalse(destinacao.getId()==null);


     }

     @Ignore
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void testarbuscarDestinacoesSalvasAnteriormente(){
        PosseInformal posseInformal = new PosseInformal();
        posseInformal.setId(62L);
        Destinacao destinacao = new Destinacao();
        destinacao.setId(1L);
        posseInformal.setTipoDestinacaoEnum(TipoDestinacaoEnum.POSSE_INFORMAL);
        Imovel imovel = new Imovel();
        imovel.setId(20L);
        posseInformal.setImovel(imovel);
        posseInformal = destinacaoRepository.save(posseInformal);
        Ocupante ocupante = new Ocupante();
        ocupante.setCpfCnpj("00000000191");
        ocupante.setNomeRazaoSocial("teste");
        List<Ocupante> ocupantes = Arrays.asList(ocupante);
        posseInformal.setOcupantes(ocupantes);
        destinacao = posseInformalService.salvarDadosEspecificos(posseInformal);
        Assert.assertFalse(destinacao.getId()==null);


    }

}

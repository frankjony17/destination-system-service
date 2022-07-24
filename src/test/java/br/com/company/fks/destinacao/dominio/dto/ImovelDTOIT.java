package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by samuel on 16/05/17.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@IntegrationTest("server.port:0")
public class ImovelDTOIT {

    private static final String rip = "rip";
    private static final int QUANTIDADE = 1;
    private static final int AREA_CONSTRUIDA_BENFEITORIA = 1;
    private static final int AREA_CONSTRUIDA_UNIDADE = 1;
    private static final Number AREA_TERRENO = 1;
    private static final String proprietario = "proprietario";
    private static final long ID = 1;
    private static final long CADASTRO_ID = 1;
    private DestinacaoDTO destinacaoDTO;
    private EnderecoDTO enderecoDTO;

    @Before
    public void setUp() {
        destinacaoDTO = new DestinacaoDTO();
        enderecoDTO = new EnderecoDTO();
    }

    @Test
    public void testaConstrutor(){
        ImovelDTO imovelDTO = new ImovelDTO(rip, enderecoDTO, AREA_TERRENO, AREA_CONSTRUIDA_BENFEITORIA, null, QUANTIDADE);
        assertEquals(rip, imovelDTO.getRip());
        assertTrue(QUANTIDADE == imovelDTO.getQuantidade());
    }

    @Test
    public void testaConstrutorAreaUnidadeNaoNula(){
        ImovelDTO imovelDTO = new ImovelDTO(rip, enderecoDTO, AREA_TERRENO, null, AREA_CONSTRUIDA_UNIDADE, QUANTIDADE);
        assertEquals(rip, imovelDTO.getRip());
    }

    @Test
    public void testaConstrutorAreaUnidadeAreaBenfeitoriaNula(){
        ImovelDTO imovelDTO = new ImovelDTO(rip, enderecoDTO, AREA_TERRENO, null, null, QUANTIDADE);
        assertEquals(BigDecimal.valueOf(AREA_TERRENO.doubleValue()), imovelDTO.getAreaConstruida());
    }

    @Test
    public void testaConstrutorAreaNulo(){
        ImovelDTO imovelDTO = new ImovelDTO(rip, enderecoDTO, AREA_TERRENO, null, AREA_CONSTRUIDA_UNIDADE, QUANTIDADE);
        assertEquals(rip, imovelDTO.getRip());
        assertTrue(QUANTIDADE == imovelDTO.getQuantidade());
    }

    @Ignore
    @Test
    public void testeConstrutor(){
        ImovelDTO imovelDTO = new ImovelDTO(ID, enderecoDTO, proprietario, destinacaoDTO, CADASTRO_ID, rip);
        assertEquals(rip, imovelDTO.getRip());
        assertTrue(proprietario == imovelDTO.getProprietario());
    }
}

package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.entidades.Imovel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by samuel on 16/05/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ImovelDTOTest {

    private ImovelDTO imovelDTO;
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
    private DadosResponsavelDTO dadosResponsavelDTO;
    private List<ResponsavelDTO>  responsavelDTOList;
    private UtilizacaoDTO utilizacaoDTO;

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
    public void testaConstrutorSegundo(){
        ImovelDTO imovelDTO2 = new ImovelDTO(2L, enderecoDTO, proprietario, new DestinacaoDTO(){{
            DadosResponsavelDTO dadosResponsavelDTO = new DadosResponsavelDTO();
            dadosResponsavelDTO.setResponsaveis(new ArrayList<>());
            setDadosResponsavel(dadosResponsavelDTO);
        }}, 1L, rip);
        assertEquals(rip, imovelDTO2.getRip());
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

}

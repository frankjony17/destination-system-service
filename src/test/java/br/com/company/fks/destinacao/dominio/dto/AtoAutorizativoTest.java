package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.dominio.enums.TipoAtoAutorizativoEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by diego on 25/05/17.
 */
public class AtoAutorizativoTest {

    private AtoAutorizativoDTO atoAutorizativoDTO;
    private Date data;
    private static final Long ID_TESTE = 1L;

    @Before
    public void setUp() {
        atoAutorizativoDTO = new AtoAutorizativoDTO();
        data = new Date();
    }

    @Test
    public void getDataPublicacaoNula() {
        atoAutorizativoDTO.setDataPublicacao(null);
        assertNull(atoAutorizativoDTO.getDataPublicacao());
    }

    @Test
    public void getDataPublicacaoNaoNula() {
        atoAutorizativoDTO.setDataPublicacao(data);
        assertEquals(data, atoAutorizativoDTO.getDataPublicacao());
    }

    @Test
    public void getNumerAtoNula(){
        atoAutorizativoDTO.setNumeroAto(null);
        assertNull(atoAutorizativoDTO.getNumeroAto());
    }

    @Test
    public void getNumerAtoNaoNula(){
        atoAutorizativoDTO.setNumeroAto(ID_TESTE);
        assertEquals(ID_TESTE, atoAutorizativoDTO.getNumeroAto());
    }

    @Test
    public void getTipoAtoAutorizativoNula(){
        atoAutorizativoDTO.setTipoAtoAutorizativoEnum(null);
        assertNull(atoAutorizativoDTO.getTipoAtoAutorizativoEnum());
    }

    @Test
    public void getTipoAtoAutorizativoNaoNula(){
        atoAutorizativoDTO.setTipoAtoAutorizativoEnum(TipoAtoAutorizativoEnum.DECRETO);
        assertEquals(TipoAtoAutorizativoEnum.DECRETO, atoAutorizativoDTO.getTipoAtoAutorizativoEnum());
    }


    private Long numeroAto;

    private TipoAtoAutorizativoEnum tipoAtoAutorizativoEnum;


}

package br.com.company.fks.destinacao.dominio.dto;

import org.junit.Before;

import java.io.Serializable;

/**
 * Created by jonatas on 28/06/18.
 */
public class TipoEspecificacaoDTOTest implements Serializable {
    public TipoEspecificacaoDTO tipoEspecificacaoDTO;

    @Before
    public void setUp(){
        tipoEspecificacaoDTO = new TipoEspecificacaoDTO();
    }

}

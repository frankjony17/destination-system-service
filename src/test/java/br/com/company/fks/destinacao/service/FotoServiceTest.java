package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.dominio.entidades.Foto;
import br.com.company.fks.destinacao.repository.FotoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class FotoServiceTest {

    @InjectMocks
    private FotoService fotoService;

    @Mock
    private FotoRepository fotoRepository;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private Foto fotos;

    @Mock
    private Arquivo arquivo;

    @Before
    public void setUp(){
        when(arquivoService.findById(anyLong())).thenReturn(arquivo);
        when(fotoRepository.save(any(Foto.class))).thenReturn(fotos);
        when(arquivo.getId()).thenReturn(1L);
        when(fotos.getArquivo()).thenReturn(arquivo);
    }

    @Test
    public void salvarNulo (){
        List<Foto> foto = fotoService.salvar(null);
        assertNotNull(foto);
    }

    @Test
    public void salvar (){
        List<Foto> listFotos = new ArrayList<>();
        listFotos.add(fotos);
        List<Foto> foto = fotoService.salvar(listFotos);
        assertNotNull(foto);
    }

    @Test
    public void salvarLista (){
        List<Foto> listFotos = new ArrayList<>();
        List<Foto> foto = fotoService.salvar(listFotos);
        assertNotNull(foto);
    }
}

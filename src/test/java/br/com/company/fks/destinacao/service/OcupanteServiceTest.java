package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.OcupanteDTO;
import br.com.company.fks.destinacao.dominio.entidades.Ocupante;
import br.com.company.fks.destinacao.dominio.entidades.PosseInformal;
import br.com.company.fks.destinacao.repository.OcupanteRepository;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;

@RunWith(PowerMockRunner.class)
public class OcupanteServiceTest {

    @InjectMocks
    private OcupanteService ocupanteService;

    @Mock
    private OcupanteRepository ocupanteRepository;

    @Mock
    private Ocupante ocupante;

    @Mock
    private PosseInformal posseInformal;

    @Before
    public void setup(){
        given(ocupanteRepository.save(any(Ocupante.class))).willReturn(ocupante);
        given(ocupanteRepository.findByIdPosseInformal(anyLong())).willReturn(Arrays.asList());
        doNothing().when(ocupanteRepository).delete(anyLong());
    }

    @Test
    public void salvar() {
        Ocupante ocupanteTest = ocupanteService.salvar(ocupante);
        assertNotNull(ocupanteTest);
    }

    @Test
    public void salvarLista() {
        List<Ocupante> ocupanteLists = new ArrayList<>();
        ocupanteLists.add(ocupante);
        List<Ocupante> lista = ocupanteService.salvar(ocupanteLists, posseInformal);
        lista.add(ocupante);
        assertNotNull(lista);
    }

    @Test
    public void deletar() {
        ocupanteService.delete(1l);
    }

    @Test
    public void findByIdPosseInformal() {
        List<OcupanteDTO> ocupanteDTOList = ocupanteService.findByIdPosseInformal(1l);
        assertNotNull(ocupanteDTOList);
    }
}

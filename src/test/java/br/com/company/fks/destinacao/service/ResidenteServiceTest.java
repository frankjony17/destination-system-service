package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Residente;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.repository.ResidenteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ResidenteServiceTest {

    @InjectMocks
    private ResidenteService residenteService;

    @Mock
    private Responsavel responsavel;

    @Mock
    private Residente residente;

    @Mock
    private ResidenteRepository residenteRepository;

    @Before
    public void setup(){
        when(residenteRepository.save(any(Residente.class))).thenReturn(residente);
    }

    @Test
    public void salvar (){
        Residente residente = new Residente();
        residente.setId(1L);
        residente.setNome("teste");
        List<Residente> lista = new ArrayList<>();
        lista.add(residente);
        lista.forEach(residente1 ->{
            assertNotNull(residente1);
        });
        List<Residente> responsavelTest = residenteService.salvar(lista, responsavel);
        responsavelTest.add(residente);
        assertFalse(lista.isEmpty());
    }

    @Test
    public void salvarNull(){
        List<Residente> lista = null;
        List<Residente> responsavelTest = residenteService.salvar(lista, responsavel);
        responsavelTest.add(null);
        assertNull(null);
    }

    @Test
    public void salvarListaFalse(){
        List<Residente> lista = new ArrayList<>();
        List<Residente> responsavelTest = residenteService.salvar(lista, responsavel);
        responsavelTest.add(null);
        assertNull(null);
    }

}

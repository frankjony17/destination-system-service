package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.Interveniente;
import br.com.company.fks.destinacao.dominio.entidades.Responsavel;
import br.com.company.fks.destinacao.dominio.entidades.Telefone;
import br.com.company.fks.destinacao.repository.TelefoneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class TelefoneServiceTest {

    @InjectMocks
    private TelefoneService telefoneService;

    @Mock
    private TelefoneRepository telefoneRepository;

    @Mock
    private Telefone telefone;


    @Mock
    private Responsavel responsavel;

    @Mock
    private Interveniente interveniente;

    @Before
    public void setup (){
        when(telefoneRepository.save(any(Telefone.class))).thenReturn(telefone);
    }

    @Test
    public void salvar(){
        Telefone telefoneTest = telefoneService.salvar(telefone);
        assertNotNull(telefoneTest);
    }

    @Test
    public void salvarNullTelefone(){
        List<Telefone> list = telefoneService.salvar(null, responsavel);
        assertNotNull(list);
    }

    @Test
    public void salvarResponvaelNulo(){
        List<Telefone> telefoneLista = new ArrayList<>();
        telefoneLista.add(telefone);
        List<Telefone> telefoneList = telefoneService.salvar(telefoneLista, responsavel);
        assertNotNull(telefoneList);
    }

    @Test
    public void salvarTelefoneInterveniente(){
        List<Telefone> telefoneLista = new ArrayList<>();
        telefoneLista.add(telefone);
       List<Telefone> telefoneList = telefoneService.salvarTelefoneInterveniente(telefoneLista, interveniente);
       assertNotNull(telefoneList);
    }

    @Test
    public void salvarTelefoneIntervenienteNull() {

        List<Telefone> telefoneList = telefoneService.salvarTelefoneInterveniente(null, interveniente);
        assertNotNull(telefoneList);
    }
}

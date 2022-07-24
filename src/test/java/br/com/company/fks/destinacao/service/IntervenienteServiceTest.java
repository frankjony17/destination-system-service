package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.repository.IntervenienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.persistence.Basic;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;

@RunWith(PowerMockRunner.class)
public class IntervenienteServiceTest {

    @InjectMocks
    private IntervenienteService intervenienteService;

    @Mock
    private IntervenienteRepository intervenienteRepository;

    @Mock
    private TelefoneService telefoneService;

    @Mock
    private EnderecoCorrespondenciaService enderecoCorrespondenciaService;

    @Mock
    private Interveniente interveniente;

    @Mock
    private DadosResponsavel dadosResponsavel;

    @Mock
    private EnderecoCorrespondencia enderecoCorrespondencia;

    @Before
    public void setup() {
        given(telefoneService.salvarTelefoneInterveniente(anyList(), any(Interveniente.class))).willReturn(Arrays.asList());
        given(enderecoCorrespondenciaService.salvar(any(EnderecoCorrespondencia.class))).willReturn(enderecoCorrespondencia);
        given(intervenienteRepository.save(any(Interveniente.class))).willReturn(interveniente);
        given(interveniente.getEnderecoCorrespondencia()).willReturn(enderecoCorrespondencia);
        given(interveniente.getTelefones()).willReturn(Arrays.asList());
    }

    @Test
    public void testSalvar() {

        Interveniente interveniente1 = intervenienteService.salvar(interveniente, dadosResponsavel, Boolean.TRUE);
        assertNotNull(interveniente1);
    }

    @Test
    public void testSalvar2() {

        Interveniente interveniente1 = intervenienteService.salvar(interveniente, dadosResponsavel, Boolean.FALSE);
        assertNotNull(interveniente1);
    }

    @Test
    public void testSalvarNull() {

        Interveniente interveniente1 = intervenienteService.salvar(null, dadosResponsavel, Boolean.FALSE);
        assertNull(interveniente1);
    }

    @Test
    public void testSalvarInterveniente() {
        given(intervenienteRepository.save(any(Interveniente.class))).willReturn(interveniente);
        Interveniente interveniente1 = intervenienteService.salvar(interveniente);
        assertNotNull(interveniente1);

    }

}

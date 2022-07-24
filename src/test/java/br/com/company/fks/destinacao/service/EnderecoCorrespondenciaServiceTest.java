package br.com.company.fks.destinacao.service;


import br.com.company.fks.destinacao.dominio.entidades.Endereco;
import br.com.company.fks.destinacao.dominio.entidades.EnderecoCorrespondencia;
import br.com.company.fks.destinacao.repository.EnderecoCorrespondenciaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
public class EnderecoCorrespondenciaServiceTest {

    @InjectMocks
    private EnderecoCorrespondenciaService enderecoCorrespondenciaService;

    @Mock
    private EnderecoCorrespondenciaRepository enderecoCorrespondenciaRepository;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private Endereco endereco;

    @Mock
    private EnderecoCorrespondencia enderecoCorrespondencia;

    @Before
    public void setup() {
        given(enderecoService.salvar(any(Endereco.class))).willReturn(endereco);
        given(enderecoCorrespondenciaRepository.save(any(EnderecoCorrespondencia.class))).willReturn(enderecoCorrespondencia);
    }

    @Test
    public void salvar() {
        EnderecoCorrespondencia enderecoCorrespondenciaa = enderecoCorrespondenciaService.salvar(enderecoCorrespondencia);
        assertNotNull(enderecoCorrespondenciaa);
    }

    @Test
    public void salvarNulo(){
        EnderecoCorrespondencia enderecoCorrespondencia = enderecoCorrespondenciaService.salvar(null);
        assertNull(null);
    }
}

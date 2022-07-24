package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.builder.Resposta;
import br.com.company.fks.destinacao.dominio.dto.CessaoOnerosaDTO;
import br.com.company.fks.destinacao.dominio.dto.TransferenciaTitularidadeDTO;
import br.com.company.fks.destinacao.dominio.entidades.*;
import br.com.company.fks.destinacao.repository.TipoDestinatarioRepository;
import br.com.company.fks.destinacao.repository.TipoTransferenciaRepository;
import br.com.company.fks.destinacao.repository.TransferenciaTitularidadeRepository;
import br.com.company.fks.destinacao.utils.EntityConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
public class TransferenciaTitularidadeServiceTest {

    @InjectMocks
    private TransferenciaTitularidadeService transferenciaTitularidadeService;

    @Mock
    private TransferenciaTitularidadeRepository transferenciaTitularidadeRepository;

    @Mock
    private DestinatarioService destinatarioService;

    @Mock
    private TransferenciaTitularidade transferenciaTitularidade;

    @Mock
    private TransferenciaTitularidadeDTO transferenciaTitularidadeDTO;

    @Mock
    private TipoTransferenciaRepository tipoTransferenciaRepository;

    @Mock
    private TipoTransferencia tipoTransferencia;

    @Mock
    private TipoDestinatario tipoDestinatario;

    @Mock
    private TipoDestinatarioRepository tipoDestinatarioRepository;

    @Mock
    private Destinatario destinatario;

    @Mock
    private EntityConverter entityConverter;

    @Before
    public void setUp(){
        when(tipoTransferenciaRepository.save(any(TipoTransferencia.class))).thenReturn(tipoTransferencia);
        when(tipoDestinatarioRepository.save(any(TipoDestinatario.class))).thenReturn(tipoDestinatario);
        when(transferenciaTitularidadeRepository.save(any(TransferenciaTitularidade.class))).thenReturn(transferenciaTitularidade);
        when(destinatarioService.salvar(destinatario, tipoDestinatario)).thenReturn(destinatario);
        when(entityConverter.converterStrict(any(TransferenciaTitularidade.class), eq(TransferenciaTitularidadeDTO.class))).thenReturn(transferenciaTitularidadeDTO);
        when(transferenciaTitularidadeRepository.findOne(anyLong())).thenReturn(transferenciaTitularidade);
    }

    @Test
    public void salvarDadosEspecificos(){
        TransferenciaTitularidade transferenciaTitularidadeTest = transferenciaTitularidadeService.salvarDadosEspecificos(transferenciaTitularidade);
        assertNotNull(transferenciaTitularidadeTest);
    }

    @Test
    public void findOne() throws Exception, IOException {
        Resposta<TransferenciaTitularidadeDTO> test = transferenciaTitularidadeService.findOne(1l);
        assertNotNull(test);
    }
}

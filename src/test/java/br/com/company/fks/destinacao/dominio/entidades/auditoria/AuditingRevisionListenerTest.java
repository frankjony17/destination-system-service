package br.com.company.fks.destinacao.dominio.entidades.auditoria;


import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by samuel on 17/05/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SecurityContextHolder.class})
public class AuditingRevisionListenerTest {

    @Mock
    private Authentication auth;

    @Mock
    private SecurityContext securityContext;
    
    @Mock
    private UsuarioLogadoDTO usuarioLogadoDTO;

    @Before
    public void setUp() {
        mockStatic(SecurityContextHolder.class);
        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(auth);
    }

    @Test
    public void newRevision(){
        when(auth.getName()).thenReturn("123");
        when(auth.getPrincipal()).thenReturn(usuarioLogadoDTO);
        when(usuarioLogadoDTO.getCpf()).thenReturn("123");
        AuditingRevisionListener auditingRevisionListener = new AuditingRevisionListener();
        Revision revision = new Revision();
        auditingRevisionListener.newRevision(revision);
        assertEquals("123", revision.getCpf());
    }

    @Test
    public void newRevisionIsNull(){
        when(auth.getName()).thenReturn("");
        AuditingRevisionListener auditingRevisionListener = new AuditingRevisionListener();
        Revision revision = new Revision();
        auditingRevisionListener.newRevision(revision);
        assertNull(revision.getCpf());
    }
}

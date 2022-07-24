package br.com.company.fks.destinacao.dominio.entidades.auditoria;

import br.gov.mpog.acessos.cliente.dto.UsuarioLogadoDTO;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;


/**
 * Created by guilherme.lima on 27/01/17.
 */
public class AuditingRevisionListener implements RevisionListener, Serializable {

    @Override
    public void newRevision(Object revisionEntity) {
        Revision revision = (Revision) revisionEntity;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getName().isEmpty()){
            UsuarioLogadoDTO usuarioLogadoDTO = (UsuarioLogadoDTO) auth.getPrincipal();
            revision.setCpf(usuarioLogadoDTO.getCpf());
        }
    }
}

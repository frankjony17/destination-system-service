package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rogerio.feitoza on 18/06/18.
 */

public class CadastroSuspensaoImovelDTO implements Serializable{
    @Getter
    @Setter
    private SuspensaoImovelDTO suspensaoImovel;
    @Getter
    @Setter
    private List<String> rips;
}

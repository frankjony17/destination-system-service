package br.com.company.fks.destinacao.dominio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Historico de um campo do DadosPessoaFisicaDTO contendo o valor do campo, a data em que esse valor foi criado e a fonte
 * de onde veio esse valor.
 */

public class DadosPessoaHistoricoCampoDTO implements Serializable {
    @Getter
    @Setter
    private Date data;
    @Getter
    @Setter
    private String fonte;
    @Getter
    @Setter
    private Object valor;

    public void setData(final Date data){
        if (data == null){
            this.data = null;
        }
        else{
            this.data = new Date(data.getTime());
        }
    }

    public Date getData(){
        if(this.data == null){
            return null;

        }
        return new Date(this.data.getTime());

    }


}

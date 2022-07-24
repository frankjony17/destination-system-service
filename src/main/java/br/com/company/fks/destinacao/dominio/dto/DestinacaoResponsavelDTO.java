package br.com.company.fks.destinacao.dominio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
public class DestinacaoResponsavelDTO implements Serializable {
    @Getter
    @Setter
    private String instrumento;

    private Date inicioVigencia;

    private Date fimVigencia;
    @Getter
    @Setter
    private String codigoUtilizacao;
    @Getter
    @Setter
    private String rip;
    @Getter
    @Setter
    private String parcela;

    public static DestinacaoResponsavelDTO toDto(Object... o){
        Objects.requireNonNull(o, "Não pode ser nulo");
        return new DestinacaoResponsavelDTO(((String)((Object[]) o[0])[0]), ((Date)((Object[]) o[0])[1]),
                ((Date)((Object[]) o[0])[2]) ,((String)((Object[]) o[0])[3]) ,((String)((Object[]) o[0])[4]),((String)((Object[]) o[0])[5]));
    }
    public static List<DestinacaoResponsavelDTO> toDto(List<Object> objs){
        Objects.requireNonNull(objs, "Não pode ser nulo");
        List<DestinacaoResponsavelDTO> dtos = new ArrayList<>();
        objs.forEach(o-> dtos.add(toDto(o)));
        return dtos;
    }

    /**
     * Informa a data de inicio da vigencia
     * @param inicioVigencia
     */
    public void setInicioVigencia(final Date inicioVigencia){
        if (inicioVigencia == null){
            this.inicioVigencia= null;
        }else{
            this.inicioVigencia = new Date(inicioVigencia.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getInicioVigencia() {
        if (this.inicioVigencia == null) {
            return null;
        }
        return new Date(this.inicioVigencia.getTime());
    }
    /**
     * Informa a data de inicio da vigencia
     * @param fimVigencia
     */
    public void setFimVigencia(final Date fimVigencia){
        if (fimVigencia == null){
            this.fimVigencia= null;
        }else{
            this.fimVigencia = new Date(fimVigencia.getTime());
        }
    }

    /**
     *
     * @return
     */
    public Date getFimVigencia() {
        if (this.fimVigencia == null) {
            return null;
        }
        return new Date(this.fimVigencia.getTime());
    }


}


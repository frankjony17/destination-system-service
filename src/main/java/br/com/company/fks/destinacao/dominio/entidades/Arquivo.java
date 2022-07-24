package br.com.company.fks.destinacao.dominio.entidades;

import br.com.company.fks.destinacao.utils.ArquivoUtils;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 04/10/2016.
 */
@Getter
@Setter
@Entity
@Table(name = "tb_arquivo", schema = "destinacao")
@Audited
@AuditTable(value = "tb_arquivo_aud", schema = "aud_destinacao")
public class Arquivo implements Serializable {
    @Id
    @Column(name = "id_arquivo")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "destinacao.tb_arquivo_seq", name = "tb_arquivo_seq")
    @GeneratedValue(generator = "tb_arquivo_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="ds_nome")
    private String nome;

    @Column(name="ds_nome_real")
    private String NomeReal;

    @Column(name="ds_extensao")
    private String extensao;

    @Column(name="ds_content_type")
    private String contentType;

    @Column(name="nu_tamanho")
    private Long tamanho;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_data")
    private Date data;

    @Column(name="dir_arquivo")
    private String diretorioArquivo;

    @NotAudited
    @Column(name = "ds_descricao")
    private String descricao;

    @Transient
    private byte[] imagem;

    /**
     * Set da data (Porque a data não pode ser mutavel)
     * @param data
     */
    public void setData(final Date data){
        if(data == null){
            this.data = null;
        }
        else{
            this.data = new Date(data.getTime());
        }
    }

    /**
     * Get da data sobrescrito
     * @return data
     */
    public Date getData(){
        if(this.data == null){
            return null;
        }
        return new Date(this.data.getTime());
    }

    @PostLoad
    public void setBytes() {
        if (!diretorioArquivo.isEmpty() && !nome.isEmpty()) {
            imagem = ArquivoUtils.lerArquivoBytes(diretorioArquivo + nome);
        }
    }

}
package br.com.company.fks.destinacao.dominio.dto;

import br.com.company.fks.destinacao.utils.ArquivoUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Basis Tecnologia on 10/10/2016.
 */

public class ArquivoDTO implements Serializable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String nomeReal;
    @Getter
    @Setter
    private String extensao;
    @Getter
    @Setter
    private String contentType;
    @Getter
    @Setter
    private Double tamanho;
    @Getter
    @Setter
    private Date data;
    @Getter
    @Setter
    private String diretorioArquivo;
    @Getter
    @Setter
    private String coordenadas;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private byte[] imagem;

    public ArquivoDTO() {}

    public ArquivoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * Informa a data do arquivo.
     * @param data
     */
    public void setData(final Date data){
        if (data == null) {
            this.data = null;
        } else {
            this.data = new Date(data.getTime());
        }
    }

    /**
     * Retorna a dada informada.
     * @return Date
     */
    public Date getData() {
        if (this.data == null) {
            return null;
        }
        return new Date(this.data.getTime());
    }

    public void setBytes() {
        if (!diretorioArquivo.isEmpty() && !nome.isEmpty()) {
            imagem = ArquivoUtils.lerArquivoBytes(diretorioArquivo + nome);
        }
    }

}
